package br.ifrn.edu.jeferson.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoFilterDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Controller
@Validated
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "API de gerenciamento de produtos do sistema")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping()
    @Operation(summary = "Cadastrar novo produto")
    public ResponseEntity<ProdutoResponseDTO> salvar(@RequestBody @Valid ProdutoRequestDTO produtoDto) {
        return ResponseEntity.ok(produtoService.salvar(produtoDto));
    }

    @GetMapping()
    @Operation(summary = "Listar produtos")
    public ResponseEntity<Page<ProdutoResponseDTO>> listar( ProdutoFilterDTO filter,
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size 
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(produtoService.listar(filter, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID")
    public ResponseEntity<ProdutoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProdutoRequestDTO produtoDto) {
        return ResponseEntity.ok(produtoService.atualizar(id, produtoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Remover produto")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/estoque")
    @Operation(summary = "Atualizar estoque do produto")
    public ResponseEntity<ProdutoResponseDTO> atualizarEstoque(@PathVariable Long id, @RequestBody Integer novoEstoque) {
        return ResponseEntity.ok(produtoService.atualizarEstoque(id, novoEstoque));
    }

    @GetMapping("/categoria/{categoriaId}")
    @Operation(summary = "Listar produtos por categoria")
    public ResponseEntity<List<ProdutoResponseDTO>> buscarPorCategoriaId(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(produtoService.buscarPorCategoriaId(categoriaId));
    }
}