package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.Client;
import com.salesmanagement.system.entities.Receipt;
import com.salesmanagement.system.entities.ReceiptLine;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ReceiptDto {

    private Long id;
    private Long clientId;
    private double totalAmount;
    private LocalDate date;
    private List<LineItemDto> linesItems;

    public ReceiptDto(Receipt receipt){
        this.id= receipt.getId();
        this.totalAmount= receipt.getTotalAmount();
        this.linesItems=receipt.getLines()
                .stream().map(LineItemDto::new).collect(Collectors.toList());
        this.date=receipt.getDate();
        this.clientId=receipt.getClient()!=null?receipt.getClient().getId():null;

    }
    // The toEntity() method has no place in this dto since it is read-only.
    //The creation of the entity, as it contains multiple objects, is delegated to the service layer.

}
