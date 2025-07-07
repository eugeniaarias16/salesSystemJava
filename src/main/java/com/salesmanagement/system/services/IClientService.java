package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.ClientDto;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IClientService {
    List<ClientDto> getAllClients();
    ClientDto getClientById(Long id);
    ClientDto createClient(ClientDto clientDto);
    void deleteClientById(Long id);
    ClientDto updateClient(Long id, ClientDto clientDto);
    //SaleDTO getPurchaseOfProduct(Long clientId, Long productId);
}
