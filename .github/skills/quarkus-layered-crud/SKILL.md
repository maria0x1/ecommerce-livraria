---
name: quarkus-layered-crud
description: Build layered Quarkus REST CRUD features with Panache repositories, validated DTOs, JPA relationships, and secure responses.
---

# Layered CRUD in Quarkus

Use this skill when implementing or extending CRUD resources in a Quarkus application that uses Jakarta REST, Hibernate ORM with Panache, and Bean Validation.

## Implementation workflow

1. Inspect the existing entities, DTOs, repositories, services, resources, exception mappers, and test conventions. Check the worktree first and preserve unrelated or pre-existing changes.
2. Translate the requested scope into a checklist of all required CRUDs and relationships. Distinguish new resources from existing ones that need extension; a requirement for several CRUDs may include resources already present in the repository.
3. Follow the project's existing layer and naming conventions:
   - JPA entities inherit the shared mapped superclass when required.
   - Repositories are `@ApplicationScoped` and implement `PanacheRepository<Entity>`.
   - Services have an interface and an `@ApplicationScoped` implementation.
   - Put `@Transactional` on write operations.
   - Resources inject services and do not contain persistence logic.
4. Define request and response records. Put Jakarta Bean Validation constraints on input fields, use `@Valid` for nested DTOs, and do not expose persistence entities from REST endpoints.
5. Implement each operation with explicit missing-resource behavior. Throw `NotFoundException` when an entity or referenced ID does not exist if the API contract requires 404; do not return `null` or silently ignore unsuccessful deletes.
6. Keep success status semantics consistent: create returns 201, update returns the updated representation, delete returns 204, and GET returns the requested representation or collection.
7. For relationship IDs in a request, resolve each ID through the corresponding repository, reject missing references, and set the associations before persist/update. On update, define and implement whether the submitted collection replaces or merges existing relationships; document the chosen behavior. De-duplicate repeated IDs while preserving a predictable order if duplicates are accepted.
8. For composed one-to-one entities, configure cascade and orphan removal only when the child is truly owned by the parent. Construct the child from its nested DTO and attach it before persisting the parent.
9. Protect sensitive fields. Store passwords as salted, slow password hashes; never return a stored hash in response DTOs. A masked response value is acceptable only if required by the established API contract; otherwise omit the password field from responses entirely.
10. Add tests for create/read/list/update/delete, invalid DTOs, missing entities and references, relationship persistence and replacement, nested validation, and sensitive-field redaction.
11. Run the smallest relevant tests, then a Quarkus package/build to exercise augmentation and ORM metadata. If a clean Quarkus build fails during augmentation with missing-class or archive-discovery errors, inspect the first root cause and retry a normal package build before concluding the source changes are broken.
12. Document endpoint paths, request relationship semantics, status codes, and any security-sensitive behavior.

## Example: resolving a many-to-many request

For a request such as `{"titulo":"Example","idsCategorias":[2,5]}`, resolve each category ID and assign the resulting managed entities before persisting:

```java
List<Categoria> categorias = new ArrayList<>();
for (Long id : new LinkedHashSet<>(idsCategorias)) {
    Categoria categoria = categoriaRepository.findById(id);
    if (categoria == null) {
        throw new NotFoundException();
    }
    categorias.add(categoria);
}
livro.setCategorias(categorias);
```

Avoid assigning only the raw IDs to an entity relationship or ignoring IDs that cannot be resolved.

## Example: nested composition

```java
PerfilAcesso perfil = new PerfilAcesso();
perfil.setNivel(dto.perfilAcesso().nivel());
perfil.setPermissoesDetalhadas(dto.perfilAcesso().permissoesDetalhadas());
usuario.setPerfilAcesso(perfil);
usuarioRepository.persist(usuario);
```

The parent response should use dedicated response DTOs for the child and must not serialize sensitive persistence fields.
