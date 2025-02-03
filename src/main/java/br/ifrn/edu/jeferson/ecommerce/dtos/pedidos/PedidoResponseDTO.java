package br.ifrn.edu.jeferson.ecommerce.dtos.pedidos;

import java.math.BigDecimal;
import java.util.List;

import br.ifrn.edu.jeferson.ecommerce.dtos.itemPedido.ItemPedidoResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de pedido")
public class PedidoResponseDTO {

    @Schema(description = "Id do pedido", example = "1")
    private Long id;

    @Schema(description = "Id do cliente", example = "1")
    private Long clienteId;

    @Schema(description = "Status do pedido", example = "AGUARDANDO")
    private String statusPedido;

    @Schema(description = "Data do pedido", example = "2021-10-10T10:00:00")
    private String dataPedido;

    @Schema(description = "Valor total do pedido", example = "100.00")
    private BigDecimal valorTotal;

    @Schema(description = "Itens do pedido")
    private List<ItemPedidoResponseDTO> itens;
}
