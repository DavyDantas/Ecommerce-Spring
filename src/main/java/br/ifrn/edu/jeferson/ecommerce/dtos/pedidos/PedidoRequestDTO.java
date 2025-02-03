package br.ifrn.edu.jeferson.ecommerce.dtos.pedidos;

import java.time.LocalDateTime;

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
@Schema(description = "DTO para requisição de pedido")
public class PedidoRequestDTO {
    
    @Schema(description = "Id do cliente", example = "1")
    private Long cliente;

    @Schema(description = "Status do pedido", example = "AGUARDANDO")
    private String statusPedido;

    @Schema(description = "Data do pedido", example = "2021-10-10T10:00:00")
    private LocalDateTime dataPedido;
}
