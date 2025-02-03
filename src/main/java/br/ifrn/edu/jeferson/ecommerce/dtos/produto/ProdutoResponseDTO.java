package br.ifrn.edu.jeferson.ecommerce.dtos.produto;

import java.math.BigDecimal;
import java.util.List;

import br.ifrn.edu.jeferson.ecommerce.dtos.categoria.CategoriaResponseDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de produto")
public class ProdutoResponseDTO {

    @Schema(description = "ID do produto", example = "1")
    private Long id;

    @Schema(description = "Nome do produto", example = "Notebook")
    private String nome;

    @Schema(description = "Descrição do produto", example = "Notebook Dell")
    private String descricao;

    @Schema(description = "Preço do produto", example = "2500.00")
    private BigDecimal preco;

    @Schema(description = "Estoque do produto", example = "10")
    private Integer estoque;

    @Schema(description = "categorias do produto", example = "1")
    private List<CategoriaResponseDTO> categorias;
}