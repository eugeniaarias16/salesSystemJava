package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "DTO representing a client")
public class ClientDto {

    @Schema(description = "Unique identifier of the client", example = "101")
    private Long id;

    @NotBlank
    @Size(min = 2, max = 70)
    @Schema(description = "Client's first name", example = "John")
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 70)
    @Schema(description = "Client's last name", example = "Doe")
    private String lastName;

    @Schema(description = "Client's national ID number", example = "40123456")
    private Long dni;

    @Past(message = "The date of birth must be earlier than today.")
    @NotNull(message = "The date of birth is mandatory.")
    @Schema(description = "Client's date of birth", example = "1990-05-20")
    private LocalDate birthDate;

    // Constructor to convert Client to ClientDto
    public ClientDto(Client client) {
        if (client != null) {
            this.id = client.getId();
            this.dni = client.getDni();
            this.birthDate = client.getBirthDate();
            this.firstName = client.getFirstName();
            this.lastName = client.getLastName();
        }
    }

    // Method to convert ClientDto to Client
    public Client toEntity() {
        Client client = new Client();
        client.setId(this.id);
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setDni(this.dni);
        client.setBirthDate(this.birthDate);
        return client;
    }

    // Convert to Client without ID
    public Client toEntityWithoutId() {
        Client client = new Client();
        client.setFirstName(this.firstName);
        client.setLastName(this.lastName);
        client.setDni(this.dni);
        client.setBirthDate(this.birthDate);
        return client;
    }
}
