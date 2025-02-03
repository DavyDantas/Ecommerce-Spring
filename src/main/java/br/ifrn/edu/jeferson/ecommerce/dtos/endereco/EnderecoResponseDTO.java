package br.ifrn.edu.jeferson.ecommerce.dtos.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de endereço")
public class EnderecoResponseDTO {

    @Schema(description = "ID do endereço", example = "1")
    private Long id;

    @Schema(description = "Rua do endereço", example = "Rua A")
    private String rua;

    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @Schema(description = "Cidade do endereço", example = "Pau dos Ferros")
    private String cidade;

    @Schema(description = "Estado do endereço", example = "RN")
    private String estado;

    @Schema(description = "CEP do endereço", example = "59900000")
    private String cep;
}