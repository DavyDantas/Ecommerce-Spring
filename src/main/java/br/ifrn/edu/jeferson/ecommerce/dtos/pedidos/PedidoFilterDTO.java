package br.ifrn.edu.jeferson.ecommerce.dtos.pedidos;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoFilterDTO {
    
    private String statusPedido;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}