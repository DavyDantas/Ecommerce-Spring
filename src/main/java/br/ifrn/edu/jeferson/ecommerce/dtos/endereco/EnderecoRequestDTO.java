package br.ifrn.edu.jeferson.ecommerce.dtos.endereco;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(description = "DTO para requisição de endereço")
public class EnderecoRequestDTO {

    @NotBlank(message = "Rua é obrigatória")
    @Schema(description = "Rua do endereço", example = "Rua A")
    private String rua;

    @NotBlank(message = "Número é obrigatório")
    @Schema(description = "Número do endereço", example = "123")
    private String numero;

    @NotBlank(message = "Bairro é obrigatório")
    @Schema(description = "Bairro do endereço", example = "Centro")
    private String bairro;

    @NotBlank(message = "Cidade é obrigatória")
    @Schema(description = "Cidade do endereço", example = "Pau dos Ferros")
    private String cidade;

    @NotBlank(message = "Estado é obrigatório")
    @Schema(description = "Estado do endereço", example = "RN")
    private String estado;

    @NotBlank(message = "CEP é obrigatório")
    @Schema(description = "CEP do endereço", example = "59900000")
    private String cep;
}