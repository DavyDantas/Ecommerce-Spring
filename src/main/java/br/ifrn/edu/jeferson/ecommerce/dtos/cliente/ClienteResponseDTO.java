package br.ifrn.edu.jeferson.ecommerce.dtos.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "DTO para resposta de cliente")
public class ClienteResponseDTO {

    @Schema(description = "ID do cliente", example = "1")
    private Long id;

    @Schema(description = "Nome do cliente", example = "Jeferson")
    private String nome;

    @Schema(description = "Email do cliente", example = "email@gmail.com")
    private String email;

    @Schema(description = "Telefone do cliente", example = "84999999999")
    private String telefone;

    @Schema(description = "CPF do cliente", example = "12345678901")
    private String cpf;

}
