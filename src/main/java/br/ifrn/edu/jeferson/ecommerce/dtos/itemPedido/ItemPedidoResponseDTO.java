package br.ifrn.edu.jeferson.ecommerce.dtos.itemPedido;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de item do pedido")
public class ItemPedidoResponseDTO {

    @Schema(description = "Id do item do pedido", example = "1")
    private Long id;

    @Schema(description = "Id do produto", example = "1")
    private Long produtoId;

    @Schema(description = "Quantidade do produto", example = "2")
    private Integer quantidade;

    @Schema(description = "Valor unitário do produto", example = "50.00")
    private BigDecimal valorUnitario;

}
