package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ifrn.edu.jeferson.ecommerce.domain.ItemPedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.dtos.itemPedido.ItemPedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.itemPedido.ItemPedidoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.ResourceNotFoundException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ItemPedidoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.ItemPedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.PedidoRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ItemPedidoMapper itemPedidoMapper;

    @Transactional
    public ItemPedidoResponseDTO criarItemPedido(ItemPedidoRequestDTO itemPedidoDto) {
        Pedido pedido = pedidoRepository.findById(itemPedidoDto.getPedido())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(itemPedidoDto.getProduto())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if(produto.getEstoque() < itemPedidoDto.getQuantidade()) {
            throw new ResourceNotFoundException("Quantidade do produto insuficiente");
        }
        
        ItemPedido itemPedido = itemPedidoMapper.toEntity(itemPedidoDto);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        itemPedidoRepository.save(itemPedido);
        return itemPedidoMapper.toResponseDTO(itemPedido);
    }

    public List<ItemPedidoResponseDTO> listarItensPedido() {
        List<ItemPedido> itensPedido = itemPedidoRepository.findAll();
        return itemPedidoMapper.toDTOList(itensPedido);
    }

    public ItemPedidoResponseDTO buscarItemPedidoPorId(Long id) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item do pedido não encontrado"));
        return itemPedidoMapper.toResponseDTO(itemPedido);
    }

    @Transactional
    public ItemPedidoResponseDTO atualizarItemPedido(Long id, ItemPedidoRequestDTO itemPedidoDto) {
        ItemPedido itemPedido = itemPedidoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item do pedido não encontrado"));

        Pedido pedido = pedidoRepository.findById(itemPedidoDto.getPedido())
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado"));
        Produto produto = produtoRepository.findById(itemPedidoDto.getProduto())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        if(produto.getEstoque() < itemPedidoDto.getQuantidade()) {
            throw new ResourceNotFoundException("Quantidade do produto insuficiente");
        }
        
        itemPedidoMapper.updateEntityFromDTO(itemPedidoDto, itemPedido);
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        itemPedidoRepository.save(itemPedido);
        return itemPedidoMapper.toResponseDTO(itemPedido);
    }

    @Transactional
    public void deletarItemPedido(Long id) {
        if (!itemPedidoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Item do pedido não encontrado");
        }
        itemPedidoRepository.deleteById(id);
    }
}