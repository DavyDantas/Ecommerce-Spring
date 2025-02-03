package br.ifrn.edu.jeferson.ecommerce.dtos.produto;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
@Schema(description = "DTO para requisição de produto")
public class ProdutoRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Schema(description = "Nome do produto", example = "Notebook")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Notebook Dell")
    private String descricao;

    @NotNull(message = "Preço é obrigatório")
    @Schema(description = "Preço do produto", example = "2500.00")
    @Positive(message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "Estoque é obrigatório")
    @Schema(description = "Estoque do produto", example = "10")
    private Integer estoque;

    @NotNull(message = "Categoria é obrigatória")
    @Schema(description = "ID da categoria do produto", example = "1")
    private Long categoriaId;
}