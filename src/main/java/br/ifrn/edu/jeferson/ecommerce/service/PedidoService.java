package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.enums.StatusPedido;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoFilterDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.PedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ClienteRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.specifications.PedidoSpecification;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO pedidoDto) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDto);
        pedido.setCliente(clienteRepository.findById(pedidoDto.getCliente())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado")));

        pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(pedido);
    }

    public Page<PedidoResponseDTO> listarPedidos(PedidoFilterDTO filtro, Pageable pageable) {
        Specification<Pedido> filter = PedidoSpecification.filtrar(filtro);
        return pedidoRepository.findAll(filter, pageable).map(pedidoMapper::toResponseDTO);
    }

    public PedidoResponseDTO buscarPedidoPorId(Long id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        return pedidoMapper.toResponseDTO(pedido);
    }

    @Transactional
    public PedidoResponseDTO atualizarStatusPedido(Long id, StatusPedido status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        pedido.setStatusPedido(status);
        pedidoRepository.save(pedido);
        return pedidoMapper.toResponseDTO(pedido);
    }

    public List<PedidoResponseDTO> buscarPedidosPorClienteId(Long clienteId) {
        clienteRepository.findById(clienteId).orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        List<Pedido> pedidos = pedidoRepository.findByClienteId(clienteId);
        return pedidoMapper.toDTOList(pedidos);
    }
}