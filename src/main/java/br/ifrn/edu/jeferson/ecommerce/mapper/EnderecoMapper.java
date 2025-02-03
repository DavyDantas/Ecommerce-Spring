package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.ifrn.edu.jeferson.ecommerce.domain.Endereco;
import br.ifrn.edu.jeferson.ecommerce.dtos.endereco.EnderecoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.endereco.EnderecoResponseDTO;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    EnderecoResponseDTO toResponseDTO(Endereco endereco);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Endereco toEntity(EnderecoRequestDTO enderecoDto);

    List<EnderecoResponseDTO> toDTOList(List<Endereco> enderecos);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = org.mapstruct.NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDTO(EnderecoRequestDTO dto, @MappingTarget Endereco endereco);
}