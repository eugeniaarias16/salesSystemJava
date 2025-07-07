package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.Client;
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
public class ClientDto {


    private Long id;
    @NotBlank
    @Size(min = 2, max = 70)
    private String firstName;

    @NotBlank
    @Size(min = 2, max = 70)
    private String lastName;
    private Integer dni;

    @Past( message = "The date of birth must be earlier than today.")
    @NotNull(message = "The date of birth is mandatory.")
    private LocalDate birthDate;

    //constructor to convert Client to ClientDto
    public ClientDto(Client client) {
        if (client != null) {
            this.id = client.getId();
            this.dni = client.getDni();
            this.birthDate = client.getBirthDate();
            this.firstName = client.getFirstName();
            this.lastName = client.getLastName();
        }
    }
     //method to convert ClientDto to Client
     public Client toEntity(){
         Client client= new Client();
         client.setId(this.id);
         client.setFirstName(this.firstName);
         client.setLastName(this.lastName);
         client.setDni(this.dni);
         client.setBirthDate(this.birthDate);

         return client;
     }


}


