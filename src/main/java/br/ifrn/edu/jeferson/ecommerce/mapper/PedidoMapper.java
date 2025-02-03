package br.ifrn.edu.jeferson.ecommerce.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.ifrn.edu.jeferson.ecommerce.domain.Pedido;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoRequestDTO;
import br.ifrn.edu.jeferson.ecommerce.dtos.pedidos.PedidoResponseDTO;

@Mapper(componentModel = "spring", uses = {ItemPedidoMapper.class})
public abstract class PedidoMapper {

    @Mapping(source = "cliente.id", target = "clienteId")
    @Mapping(source = "itens", target = "itens")
    public abstract PedidoResponseDTO toResponseDTO(Pedido pedido);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    public abstract Pedido toEntity(PedidoRequestDTO pedidoDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "itens", ignore = true)
    @Mapping(target = "valorTotal", ignore = true)
    public abstract void updateEntityFromDTO(PedidoRequestDTO dto, @MappingTarget Pedido pedido);

    public abstract List<PedidoResponseDTO> toDTOList(List<Pedido> pedidos);

    @AfterMapping
    protected void calculateValorTotal(@MappingTarget PedidoResponseDTO pedidoResponseDTO) {
        BigDecimal valorTotal = pedidoResponseDTO.getItens().stream()
                .map(item -> item.getValorUnitario().multiply(BigDecimal.valueOf(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        pedidoResponseDTO.setValorTotal(valorTotal);
    }
}