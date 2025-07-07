package com.salesmanagement.system.responses;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter

public class ApiResponse {
    private String message;
    private LocalDate localDate;

    public ApiResponse(String message){
        this.localDate=LocalDate.now();
        this.message=message;
    }

}
