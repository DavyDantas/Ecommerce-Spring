package br.ifrn.edu.jeferson.ecommerce.dtos.produto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoFilterDTO {
    private String nome;
    private BigDecimal precoMin;
    private BigDecimal precoMax;
    private Long categoriaId;
}
