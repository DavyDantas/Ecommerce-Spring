package br.ifrn.edu.jeferson.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.dtos.endereco.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.endereco.EnderecoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.mapper.EnderecoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.EnderecoRepository;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public EnderecoResponseDTO salvar(Long clienteId, EnderecoRequestDTO enderecoDto) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        Endereco endereco = enderecoMapper.toEntity(enderecoDto);
        endereco.setCliente(cliente);

        enderecoRepository.save(endereco);
        return enderecoMapper.toResponseDTO(endereco);
    }

    public EnderecoResponseDTO atualizar(Long clienteId, EnderecoRequestDTO enderecoDto) {

        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        Endereco endereco = enderecoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new BusinessException("Endereço não encontrado para o cliente"));

        enderecoMapper.updateEntityFromDTO(enderecoDto, endereco);
        enderecoRepository.save(endereco);

        return enderecoMapper.toResponseDTO(endereco);
    }

    public void deletar(Long clienteId) {

        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        Endereco endereco = enderecoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new BusinessException("Endereço não encontrado para o cliente"));

        enderecoRepository.delete(endereco);
    }

    public EnderecoResponseDTO buscarPorClienteId(Long clienteId) {

        clienteRepository.findById(clienteId)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        Endereco endereco = enderecoRepository.findByClienteId(clienteId)
                .orElseThrow(() -> new BusinessException("Endereço não encontrado para o cliente"));

        return enderecoMapper.toResponseDTO(endereco);
    }

}