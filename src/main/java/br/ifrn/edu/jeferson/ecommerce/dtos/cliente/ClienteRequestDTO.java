package br.ifrn.edu.jeferson.ecommerce.dtos.cliente;

import org.hibernate.validator.constraints.br.CPF;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@Schema(description = "DTO para requisição de clientes")
public class ClienteRequestDTO {

    @NotNull
    @NotBlank(message = "Nome é obrigatório")
    @Schema(description ="Nome do cliente", example="Jeferson")
    private String nome;

    @NotNull
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Schema(description ="Email do cliente", example="email@gmail.com")
    private String email;

    @NotNull
    @NotBlank(message = "CPF é obrigatório")
    @Schema(description ="CPF do cliente", example="12345678901")
    @CPF(message = "CPF inválido")
    private String cpf;

    @Schema(description ="Telefone do cliente", example="84999999999")
    private String telefone;
}