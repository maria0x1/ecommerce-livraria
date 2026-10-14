# livraria

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./mvnw quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./mvnw package
```

It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Error responses

API errors use the `application/problem+json` media type and follow RFC 9457. The response includes `type`, `title`, `status`, `detail`, and `instance`. Validation failures also include a `violations` array with the invalid `field` and its `message`, for example:

```json
{
  "type": "about:blank",
  "title": "Erro de validação",
  "status": 400,
  "detail": "Um ou mais campos da requisição são inválidos.",
  "instance": "/livros/digitais",
  "violations": [
    {
      "field": "inserir.dto.titulo",
      "message": "O campo titulo é obrigatório"
    }
  ]
}
```

Validation errors, malformed requests (`BadRequestException`), and missing resources (`NotFoundException`) are converted centrally by JAX-RS exception mappers. Missing book IDs in read, update, and delete operations are reported as `404 Not Found`.

## Administrative resources

The administrative CRUD endpoints are:

- `/editoras`
- `/categorias`
- `/usuarios-admin`
- `/livros/fisicos`
- `/livros/digitais`

Each supports collection `GET`, item `GET /{id}`, `POST` (201 Created), `PUT /{id}`, and `DELETE /{id}` (204 No Content). Physical and digital book request bodies require a non-empty `idsCategorias` array to match the UML cardinality; category IDs that do not exist are rejected with `404 Not Found`. When updating, the array replaces the book's existing category associations.

Administrator creation and updates accept a nested `perfilAcesso` object. `nivel` uses the enum names `ROOT`, `GERENTE`, or `OPERACIONAL` in JSON. Each enum also has a stable numeric ID and label for internal use; JPA persists the enum name rather than its ordinal or ID.

## Swagger, passwords, and profiles

In development, open Swagger UI at <http://localhost:8080/q/swagger-ui>; the OpenAPI document is at <http://localhost:8080/q/openapi>. Set `DB_PASSWORD` in the shell before starting Quarkus. For example, in PowerShell:

```powershell
$env:DB_PASSWORD = '<senha-do-postgres-local>'
.\mvnw.cmd quarkus:dev
```

The development-only seed creates `root` with the password `LivrariaDev#2026`. Use it only with a local development database: `%dev` uses `drop-and-create` and reloads the seed, so its data is erased when the schema is recreated. Production must use a different credential and must not load this seed.

To test user creation, call `POST /usuarios-admin` with a nested profile:

```json
{
  "nomeCompleto": "Administrador de Teste",
  "login": "admin-teste",
  "senha": "uma-senha-de-teste",
  "perfilAcesso": {
    "nivel": "ROOT",
    "permissoesDetalhadas": "Acesso de desenvolvimento"
  }
}
```

Passwords are stored as PBKDF2-HMAC-SHA256 hashes with an independent random salt. `GET /usuarios-admin` and `GET /usuarios-admin/{id}` return neither the open password nor its hash. Password verification is a domain service operation and should be tested with automated tests; there is deliberately no debug endpoint that reveals hashes. The profile is currently persisted metadata, not an authorization mechanism: routes are not protected by `ROOT`, `GERENTE`, or `OPERACIONAL`, and no login/JWT endpoint is provided yet. Do not expose this API to untrusted networks until authentication and route-level authorization are implemented.

Publisher CNPJ values are normalized to uppercase alphanumeric characters, validated with check digits, and unique. This accepts both numeric CNPJs and the current alphanumeric format.

Book payload fields follow the UML names: `isbn`, `preco`, `dataPublicacao`, `pesoKg`, `quantidadeEstoque`, `tipoCapa`, `formatoArquivo`, `tamanhoMb`, and `licencaDrm`. This changes the previous JSON property names. Stock reduction and price discount are available at:

- `POST /livros/fisicos/{id}/baixas-estoque` with `{"quantidade": 1}`
- `POST /livros/fisicos/{id}/descontos` or `POST /livros/digitais/{id}/descontos` with `{"percentual": 10}`

Concrete shipping rates and secure temporary download links require a configured provider/storage and business rules; these are not fabricated by the API.

## Database and migrations

`src/main/resources/import.sql` contains local demonstration data, including the development-only ROOT account. Keep real production credentials out of this file and out of source-controlled configuration. Set `DB_USERNAME`, `DB_PASSWORD`, and optionally `DB_JDBC_URL` through the deployment environment. For Swagger testing, point `DB_JDBC_URL` to a disposable development database before starting the app because `%dev` recreates its schema.

The `%prod` profile applies versioned Flyway migrations and validates the Hibernate schema. Existing non-empty databases are baselined at migration version 1 before the UML alignment migration is applied. Review backups and resolve duplicate CNPJs, null CNPJs, and books without a publisher before rollout; the migration adds a unique CNPJ index and requires each book to reference an editor. Books that have no existing category are linked to an inactive `Sem categoria` migration category. The migration adds ISBN, price, and publication date as nullable for pre-existing books because reliable values cannot be inferred; new API writes validate these fields.

SQL statement logging is enabled only for development. Bind-parameter logging is disabled to avoid writing personal data or password hashes to logs.

If you want to build an _über-jar_, execute the following command:

```shell script
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/livraria-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/maven-tooling>.

## Related Guides

- REST ([guide](https://quarkus.io/guides/rest)): Build RESTful web services and APIs using Jakarta REST (formerly JAX-RS)
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Generate OpenAPI schemas and serve Swagger UI for REST API documentation
- REST Jackson ([guide](https://quarkus.io/guides/rest#json-serialisation)): Jackson serialization support for Quarkus REST. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplified JPA/Hibernate data access layer with active record and repository patterns
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)


[Related Hibernate with Panache section...](https://quarkus.io/guides/hibernate-orm-panache)


### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
