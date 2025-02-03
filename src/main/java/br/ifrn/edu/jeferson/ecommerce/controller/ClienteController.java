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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ifrn.edu.jeferson.ecommerce.dtos.cliente.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.cliente.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;


@Validated
@Controller
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "API de gerenciamento de clientes do sistema")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @PostMapping()
    @Operation(summary = "Cadastrar um novo cliente")
    public ResponseEntity<ClienteResponseDTO> salvar(@RequestBody @Valid ClienteRequestDTO clienteDto) {
        System.out.println(clienteDto);
        return ResponseEntity.ok(clienteService.salvar(clienteDto));
    }

    @GetMapping()
    @Operation(summary = "Listar todos os clientes")
    public ResponseEntity<Page<ClienteResponseDTO>> listar(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(clienteService.listar(pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um cliente")
    public ResponseEntity<Void> deletar(Long id){
        clienteService.deletar(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}")
    @Operation(summary = "Atualizar um cliente")
    public ResponseEntity<ClienteResponseDTO> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDTO clienteDto){
        return ResponseEntity.ok(clienteService.atualizar(id, clienteDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar um cliente por Id")
    public ResponseEntity<ClienteResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.buscarPorId(id));
    }

    @GetMapping("/{id}/pedidos")
    @Operation(summary = "Listar pedidos de um cliente pelo Id dele")
    public ResponseEntity<List<PedidoResponseDTO>> listarPedidos(@PathVariable Long id){
        return ResponseEntity.ok(clienteService.ListarPedidos(id));
    }
    
}
