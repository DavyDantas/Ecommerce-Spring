package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ifrn.edu.jeferson.ecommerce.domain.Cliente;
import br.ifrn.edu.jeferson.ecommerce.dtos.cliente.ClienteRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.cliente.ClienteResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ClienteMapper;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteMapper clienteMapper;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Transactional
    public ClienteResponseDTO salvar(ClienteRequestDTO clienteDto) {
        var cliente = clienteMapper.toEntity(clienteDto);

        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new BusinessException("Já existe um cliente com esse email " + cliente.getEmail());
        }

        if(clienteRepository.existsByCpf(cliente.getCpf())){
            throw new BusinessException("Já existe um cliente com esse CPF");
        }     

        clienteRepository.save(cliente);
        return clienteMapper.toResponseDTO(cliente);
    }

    @Cacheable(value = "clientesPage", key = "#pageable.pageNumber")
    public Page<ClienteResponseDTO> listar(Pageable pageable){
        return clienteRepository.findAll(pageable).map(clienteMapper::toResponseDTO);
    }

    public ClienteResponseDTO buscarPorId(Long id){
        var cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
        return clienteMapper.toResponseDTO(cliente);
    }

    @Transactional
    @CacheEvict(value = "clientesPage", allEntries = true)
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO clienteDto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));

        if (!cliente.getEmail().equals(clienteDto.getEmail()) && clienteRepository.existsByEmail(clienteDto.getEmail())) {
            throw new BusinessException("Já existe um cliente com esse email");
        }

        if (!cliente.getCpf().equals(clienteDto.getCpf()) && clienteRepository.existsByCpf(clienteDto.getCpf())) {
            throw new BusinessException("Já existe um cliente com esse CPF");
        }

        clienteMapper.updateEntityFromDTO(clienteDto, cliente);
        var clienteAlterado = clienteRepository.save(cliente);

        return clienteMapper.toResponseDTO(clienteAlterado);
    }

    @Transactional
    @CacheEvict(value = "clientesPage", allEntries = true)
    public void deletar(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new BusinessException("Cliente não encontrado");
        }
        clienteRepository.deleteById(id);
    }

    public List<PedidoResponseDTO> ListarPedidos(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado"));
        
        return pedidoMapper.toDTOList(cliente.getPedidos());
    }
}