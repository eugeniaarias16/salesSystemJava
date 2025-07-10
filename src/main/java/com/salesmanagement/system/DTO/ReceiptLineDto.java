package com.salesmanagement.system.DTO;
import com.salesmanagement.system.entities.ReceiptLine;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReceiptLineDto {

    private Long id;
    private ProductDto product;
    private Integer quantity;
    private Double lineTotal;

    public ReceiptLineDto(ReceiptLine receiptLine){
        if(receiptLine!=null){
            this.id=receiptLine.getId();
            this.product=new ProductDto(receiptLine.getProduct());
            this.quantity=receiptLine.getQuantity();
            this.lineTotal=receiptLine.getLineTotal();
        }
    }


}
