package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.ReceiptLine;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LineItemDto {

    private Long productId;
    private int quantity;
    private double lineTotal;

    // Empty constructor
    public LineItemDto() {}

    //Constructor that takes a ReceiptLine
    public LineItemDto(ReceiptLine receiptLine) {
        this.productId = receiptLine.getProduct().getId(); // retrieve the product ID
        this.quantity = receiptLine.getQuantity();
        this.lineTotal = receiptLine.getLineTotal();
    }

    // Method to convert back to ReceiptLine (useful in ReceiptDto.toEntity)
    public ReceiptLine toEntity() {
        ReceiptLine line = new ReceiptLine();
        line.setQuantity(this.quantity);
        line.setLineTotal(this.lineTotal);
        // Set the Product manually after searching for it by ID
        return line;
    }
}
