package br.ifrn.edu.jeferson.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoFilterDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@Validated
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "API de gerenciamento de pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @Operation(summary = "Criar novo pedido")
    public ResponseEntity<PedidoResponseDTO> criarPedido(@RequestBody @Valid PedidoRequestDTO pedidoDto) {
        return ResponseEntity.ok(pedidoService.criarPedido(pedidoDto));
    }

    @GetMapping
    @Operation(summary = "Listar todos os pedidos")
    public ResponseEntity<Page<PedidoResponseDTO>> listarPedidos(
            PedidoFilterDTO filter,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(pedidoService.listarPedidos(filter, pageable));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar pedido por ID")
    public ResponseEntity<PedidoResponseDTO> buscarPedidoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pedidoService.buscarPedidoPorId(id));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Atualizar status do pedido")
    public ResponseEntity<PedidoResponseDTO> atualizarStatusPedido(@PathVariable Long id, @RequestParam StatusPedido status) {
        return ResponseEntity.ok(pedidoService.atualizarStatusPedido(id, status));
    }

    @GetMapping("/cliente/{clienteId}")
    @Operation(summary = "Listar pedidos por cliente")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidosPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(pedidoService.buscarPedidosPorClienteId(clienteId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar pedido")
    public ResponseEntity<Void> deletarPedido(@PathVariable Long id) {
        pedidoService.deletarPedido(id);
        return ResponseEntity.ok().build();
    }
}