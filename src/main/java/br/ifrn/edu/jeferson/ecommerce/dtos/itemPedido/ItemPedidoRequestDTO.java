package br.ifrn.edu.jeferson.ecommerce.dtos.itemPedido;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para requisição de item do pedido")
public class ItemPedidoRequestDTO {
    
    @Schema(description = "Quantidade do produto", example = "2")
    private Integer quantidade;
    
    @Schema(description = "Valor unitário do produto", example = "50.00")
    private BigDecimal valorUnitario;

    @Schema(description = "Id do produto", example = "1")
    private Long produto;

    @Schema(description = "Id do pedido", example = "1")
    private Long pedido;
}
