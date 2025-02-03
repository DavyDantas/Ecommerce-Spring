package br.ifrn.edu.jeferson.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ifrn.edu.jeferson.ecommerce.dtos.endereco.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.endereco.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Controller
@Validated
@RequestMapping("/api/clientes/{clienteId}/enderecos")
@Tag(name = "Endereços", description = "API de gerenciamento de endereços do sistema")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping()
    @Operation(summary = "Cadastrar endereço para cliente")
    public ResponseEntity<EnderecoResponseDTO> salvar(@PathVariable Long clienteId, @RequestBody @Valid EnderecoRequestDTO enderecoDto) {
        return ResponseEntity.ok(enderecoService.salvar(clienteId, enderecoDto));
    }

    @GetMapping()
    @Operation(summary = "Buscar endereço do cliente")
    public ResponseEntity<EnderecoResponseDTO> buscarPorClienteId(@PathVariable Long clienteId) {
        return ResponseEntity.ok(enderecoService.buscarPorClienteId(clienteId));
    }

    @PutMapping()
    @Operation(summary = "Atualizar endereço do cliente")
    public ResponseEntity<EnderecoResponseDTO> atualizar(@PathVariable Long clienteId, @RequestBody EnderecoRequestDTO enderecoDto) {
        return ResponseEntity.ok(enderecoService.atualizar(clienteId, enderecoDto));
    }

    @DeleteMapping()
    @Operation(summary = "Remover endereço do cliente")
    public ResponseEntity<Void> deletar(@PathVariable Long clienteId) {
        enderecoService.deletar(clienteId);
        return ResponseEntity.ok().build();
    }
}