package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.ifrn.edu.jeferson.ecommerce.domain.Categoria;
import br.ifrn.edu.jeferson.ecommerce.dtos.categoria.CategoriaRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.categoria.CategoriaResponseDTO;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaResponseDTO toResponseDTO(Categoria categoria);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    Categoria toEntity(CategoriaRequestDTO dto);

    List<CategoriaResponseDTO> toDTOList(List<Categoria> categorias);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "produtos", ignore = true)
    void updateEntityFromDTO(CategoriaRequestDTO dto, @MappingTarget Categoria categoria);
}
