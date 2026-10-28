package br.unitins.tp1.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;

import br.unitins.tp1.dto.CategoriaDTO;
import br.unitins.tp1.dto.CategoriaResponseDTO;
import br.unitins.tp1.dto.BaixaEstoqueDTO;
import br.unitins.tp1.dto.DescontoDTO;
import br.unitins.tp1.dto.EditoraDTO;
import br.unitins.tp1.dto.EditoraResponseDTO;
import br.unitins.tp1.dto.LivroDigitalDTO;
import br.unitins.tp1.dto.LivroDigitalResponseDTO;
import br.unitins.tp1.dto.LivroFisicoDTO;
import br.unitins.tp1.dto.LivroFisicoResponseDTO;
import br.unitins.tp1.dto.LivroResponseDTO;
import br.unitins.tp1.dto.UsuarioAdminDTO;
import br.unitins.tp1.dto.UsuarioAdminResponseDTO;
import br.unitins.tp1.service.CategoriaService;
import br.unitins.tp1.service.EditoraService;
import br.unitins.tp1.service.LivroDigitalService;
import br.unitins.tp1.service.LivroFisicoService;
import br.unitins.tp1.service.LivroService;
import br.unitins.tp1.service.UsuarioAdminService;
import jakarta.ws.rs.core.Response;

import static org.mockito.Mockito.mock;

class ResourceTest {

    @Test
    void livroResourceDelegatesQueriesAndReturnsHttp200OnDelete() {
        LivroService service = mock(LivroService.class);
        LivroResource resource = new LivroResource();
        resource.livroService = service;
        List<LivroResponseDTO> livros = List.of();
        when(service.findAll()).thenReturn(livros);

        assertSame(livros, resource.listarTudo());
        resource.buscarPorId(1L);
        resource.buscarPorTitulo("java");
        resource.buscarPorAutor("autor");
        resource.buscarPorEditora("editora");
        Response response = resource.excluir(1L);

        assertEquals(200, response.getStatus());
        verify(service).findByID(1L);
        verify(service).findByTitulo("java");
        verify(service).findByAutor("autor");
        verify(service).findByEditora("editora");
        verify(service).delete(1L);
    }

    @Test
    void livroFisicoResourceDelegatesOperationsAndReturnsExpectedStatuses() {
        LivroFisicoService service = mock(LivroFisicoService.class);
        LivroFisicoResource resource = new LivroFisicoResource();
        resource.livroFisicoService = service;
        LivroFisicoDTO dto = null;
        BaixaEstoqueDTO baixa = new BaixaEstoqueDTO(2);
        DescontoDTO desconto = new DescontoDTO(10.0);
        when(service.findAll()).thenReturn(List.of());

        assertEquals(200, Response.ok(resource.listarTodos()).build().getStatus());
        assertEquals(201, resource.inserir(dto).getStatus());
        resource.buscarPorId(1L);
        resource.atualizar(1L, dto);
        resource.baixarEstoque(1L, baixa);
        resource.aplicarDesconto(1L, desconto);
        assertEquals(204, resource.excluir(1L).getStatus());

        verify(service).create(dto);
        verify(service).findById(1L);
        verify(service).update(1L, dto);
        verify(service).baixarEstoque(1L, 2);
        verify(service).aplicarDesconto(1L, 10.0);
        verify(service).delete(1L);
    }

    @Test
    void livroDigitalResourceDelegatesOperationsAndReturnsExpectedStatuses() {
        LivroDigitalService service = mock(LivroDigitalService.class);
        LivroDigitalResource resource = new LivroDigitalResource();
        resource.livroDigitalService = service;
        LivroDigitalDTO dto = null;
        DescontoDTO desconto = new DescontoDTO(10.0);
        when(service.findAll()).thenReturn(List.of());

        assertEquals(200, Response.ok(resource.listarTodos()).build().getStatus());
        assertEquals(201, resource.inserir(dto).getStatus());
        resource.buscarPorId(1L);
        resource.atualizar(1L, dto);
        resource.aplicarDesconto(1L, desconto);
        assertEquals(204, resource.excluir(1L).getStatus());

        verify(service).create(dto);
        verify(service).findById(1L);
        verify(service).update(1L, dto);
        verify(service).aplicarDesconto(1L, 10.0);
        verify(service).delete(1L);
    }

    @Test
    void categoriaResourceDelegatesOperationsAndReturnsExpectedStatuses() {
        CategoriaService service = mock(CategoriaService.class);
        CategoriaResource resource = new CategoriaResource();
        resource.categoriaService = service;
        CategoriaDTO dto = null;
        when(service.findAll()).thenReturn(List.of());

        assertEquals(201, resource.create(dto).getStatus());
        assertEquals(200, resource.findAll().getStatus());
        resource.findById(1L);
        resource.update(1L, dto);
        assertEquals(204, resource.delete(1L).getStatus());

        verify(service).create(dto);
        verify(service).findAll();
        verify(service).findById(1L);
        verify(service).update(1L, dto);
        verify(service).delete(1L);
    }

    @Test
    void editoraResourceDelegatesOperationsAndReturnsExpectedStatuses() {
        EditoraService service = mock(EditoraService.class);
        EditoraResource resource = new EditoraResource();
        resource.editoraService = service;
        EditoraDTO dto = null;
        when(service.findAll()).thenReturn(List.of());

        assertEquals(201, resource.create(dto).getStatus());
        assertEquals(200, resource.findAll().getStatus());
        resource.findById(1L);
        resource.update(1L, dto);
        assertEquals(204, resource.delete(1L).getStatus());

        verify(service).create(dto);
        verify(service).findAll();
        verify(service).findById(1L);
        verify(service).update(1L, dto);
        verify(service).delete(1L);
    }

    @Test
    void usuarioAdminResourceDelegatesOperationsAndReturnsExpectedStatuses() {
        UsuarioAdminService service = mock(UsuarioAdminService.class);
        UsuarioAdminResource resource = new UsuarioAdminResource();
        resource.usuarioAdminService = service;
        UsuarioAdminDTO dto = null;
        when(service.findAll()).thenReturn(List.of());

        assertEquals(201, resource.create(dto).getStatus());
        assertEquals(200, resource.findAll().getStatus());
        resource.findById(1L);
        resource.update(1L, dto);
        assertEquals(204, resource.delete(1L).getStatus());

        verify(service).create(dto);
        verify(service).findAll();
        verify(service).findById(1L);
        verify(service).update(1L, dto);
        verify(service).delete(1L);
    }

}
