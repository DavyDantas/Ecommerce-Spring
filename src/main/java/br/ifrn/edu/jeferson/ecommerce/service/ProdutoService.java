package br.ifrn.edu.jeferson.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoFilterDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoResponseDTO;
import br.ifrn.edu.jeferson.ecommerce.exception.BusinessException;
import br.ifrn.edu.jeferson.ecommerce.mapper.ProdutoMapper;
import br.ifrn.edu.jeferson.ecommerce.repository.CategoriaRepository;
import br.ifrn.edu.jeferson.ecommerce.repository.ProdutoRepository;
import br.ifrn.edu.jeferson.ecommerce.specifications.ProdutoSpecification;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Transactional
    public ProdutoResponseDTO salvar(ProdutoRequestDTO produtoDto) {
        Categoria categoria = categoriaRepository.findById(produtoDto.getCategoriaId())
                .orElseThrow(() -> new BusinessException("Categoria não encontrada"));

        Produto produto = produtoMapper.toEntity(produtoDto);

        categoria.getProdutos().add(produto);
        produto.getCategorias().add(categoria);
        produtoRepository.save(produto);
        return produtoMapper.toResponseDTO(produto);
    }

    public Page<ProdutoResponseDTO> listar(ProdutoFilterDTO filtro, Pageable pageable) {
        Specification<Produto> filter = ProdutoSpecification.filtrar(filtro);
        return produtoRepository.findAll(filter, pageable).map(produtoMapper::toResponseDTO);
    }

    public ProdutoResponseDTO buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));
        return produtoMapper.toResponseDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO produtoDto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));

        Categoria categoria = categoriaRepository.findById(produtoDto.getCategoriaId())
                .orElseThrow(() -> new BusinessException("Categoria não encontrada"));

        if (!produto.getCategorias().contains(categoria)) {
            produto.getCategorias().add(categoria);
            categoria.getProdutos().add(produto);
        }
        
        produtoMapper.updateEntityFromDTO(produtoDto, produto);
        produtoRepository.save(produto);

        return produtoMapper.toResponseDTO(produto);
    }

    @Transactional
    public void deletar(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new BusinessException("Produto não encontrado");
        }
        produtoRepository.deleteById(id);
    }

    @Transactional
    public ProdutoResponseDTO atualizarEstoque(Long id, Integer novoEstoque) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));

        produto.setEstoque(novoEstoque);
        produtoRepository.save(produto);

        return produtoMapper.toResponseDTO(produto);
    }

    public List<ProdutoResponseDTO> buscarPorCategoriaId(Long categoriaId) {
        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new BusinessException("Categoria não encontrada"));

        List<Produto> produtos = produtoRepository.findByCategorias(categoria);
        return produtoMapper.toDTOList(produtos);
    }
}