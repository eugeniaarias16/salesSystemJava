package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.ReceiptLine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "DTO representing a line item in a receipt")
public class ReceiptLineDto {

    @Schema(description = "Unique identifier of the receipt line", example = "5001")
    private Long id;

    @Schema(description = "Product details of the item")
    private ProductDto product;

    @Schema(description = "Quantity of the product", example = "3")
    private Integer quantity;

    @Schema(description = "Total cost for this line (quantity × product price)", example = "59.97")
    private Double lineTotal;

    public ReceiptLineDto(ReceiptLine receiptLine) {
        if (receiptLine != null) {
            this.id = receiptLine.getId();
            this.product = new ProductDto(receiptLine.getProduct());
            this.quantity = receiptLine.getQuantity();
            this.lineTotal = receiptLine.getLineTotal();
        }
    }
}
