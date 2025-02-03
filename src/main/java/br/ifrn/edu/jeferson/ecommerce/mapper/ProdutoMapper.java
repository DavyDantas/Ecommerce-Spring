package br.ifrn.edu.jeferson.ecommerce.mapper;

import br.ifrn.edu.jeferson.ecommerce.domain.Produto;
import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.produto.ProdutoResponseDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    @Mapping(source = "categorias", target = "categorias")
    ProdutoResponseDTO toResponseDTO(Produto produto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    Produto toEntity(ProdutoRequestDTO dto);

    List<ProdutoResponseDTO> toDTOList(List<Produto> produtos);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "categorias", ignore = true)
    void updateEntityFromDTO(ProdutoRequestDTO dto, @MappingTarget Produto produto);
}