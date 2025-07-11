package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.Receipt;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Schema(description = "DTO representing a sales receipt with line items")
public class ReceiptDto {

    @Schema(description = "Unique identifier of the receipt", example = "2001")
    private Long id;

    @Schema(description = "Client ID associated with the receipt", example = "101")
    private Long clientId;

    @Schema(description = "Total amount of the receipt", example = "149.99")
    private double totalAmount;

    @Schema(description = "Date of the receipt", example = "2025-07-11")
    private LocalDate date;

    @Schema(description = "List of items in the receipt")
    private List<LineItemDto> linesItems;

    public ReceiptDto(Receipt receipt) {
        this.id = receipt.getId();
        this.totalAmount = receipt.getTotalAmount();
        this.linesItems = receipt.getLines()
                .stream().map(LineItemDto::new).collect(Collectors.toList());
        this.date = receipt.getDate();
        this.clientId = receipt.getClient() != null ? receipt.getClient().getId() : null;
    }

    // Note: No toEntity() method, as entity creation is handled in the service layer.
}
