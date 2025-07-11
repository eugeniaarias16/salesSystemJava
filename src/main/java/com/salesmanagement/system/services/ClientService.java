package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.ClientDto;
import com.salesmanagement.system.entities.Client;
import com.salesmanagement.system.exceptions.BadRequestException;
import com.salesmanagement.system.exceptions.ResourceNotFoundException;
import com.salesmanagement.system.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(ClientDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto getClientById(Long id) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + id));

        return new ClientDto(client);
    }

    @Override
    public ClientDto getClientByDni(Long dni) {
        Client client=clientRepository.findClientByDni(dni)
                .orElseThrow(()->new ResourceNotFoundException("Client not found with dni "+dni));

        return new ClientDto(client);

    }
    @Override
    public ClientDto createClient(ClientDto clientDto) {
        Client client= clientDto.toEntityWithoutId();

        //check if the customer with that dni number does not exist
        Long clientDni=client.getDni();
        if(clientRepository.findClientByDni(clientDni).isPresent()){
            throw new BadRequestException("Client with DNI " + clientDni + " already exists.");
        }

        Client clientSaved=clientRepository.save(client);
        return new ClientDto(clientSaved); //client with id
    }

    @Override
    public void deleteClientById(Long id) {
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Client with the id "+id+" was not found");
        }
        clientRepository.deleteById(id);

    }

    @Override
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Client existingClient= clientRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Client with the id "+ id+ " was not found"));
        existingClient.setFirstName(clientDto.getFirstName());
        existingClient.setLastName(clientDto.getLastName());
        existingClient.setDni(clientDto.getDni());
        existingClient.setBirthDate(clientDto.getBirthDate());
        Client savedClient= clientRepository.save(existingClient);
        return  new ClientDto(savedClient);
    }


}
