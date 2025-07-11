package com.salesmanagement.system.DTO;

import com.salesmanagement.system.entities.ReceiptLine;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Line item representing a product within a receipt, including quantity and total")
public class LineItemDto {

    @Schema(description = "ID of the associated product", example = "101")
    private Long productId;

    @Schema(description = "Quantity of the product purchased", example = "2", minimum = "1")
    private int quantity;

    @Schema(description = "Total cost for this line (price × quantity)", example = "59.98", minimum = "0")
    private double lineTotal;

    public LineItemDto() {}

    public LineItemDto(ReceiptLine receiptLine) {
        this.productId = receiptLine.getProduct().getId();
        this.quantity = receiptLine.getQuantity();
        this.lineTotal = receiptLine.getLineTotal();
    }

    public ReceiptLine toEntity() {
        ReceiptLine line = new ReceiptLine();
        line.setQuantity(this.quantity);
        line.setLineTotal(this.lineTotal);
        return line;
    }
}
