package com.salesmanagement.system.responses;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter

public class CustomApiResponse {
    private String message;
    private LocalDate localDate;

    public CustomApiResponse(String message){
        this.localDate=LocalDate.now();
        this.message=message;
    }

}
