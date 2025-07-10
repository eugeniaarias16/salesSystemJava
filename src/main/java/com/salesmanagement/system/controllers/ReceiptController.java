package com.salesmanagement.system.controllers;

import com.salesmanagement.system.DTO.ReceiptDto;
import com.salesmanagement.system.responses.ApiResponse;
import com.salesmanagement.system.services.ReceiptService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;


    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }
    /*---------------- GET METHODS --------------------*/
    @GetMapping
    public ResponseEntity<List<ReceiptDto>> getAllReceipts(){
       List<ReceiptDto> list=receiptService.getAllReceipts();
       return ResponseEntity.ok(list);
    }

    @GetMapping("/{receiptId}")
    public ResponseEntity<ReceiptDto> getReceiptById(@PathVariable Long receiptId){
        ReceiptDto receiptDto=receiptService.getReceiptById(receiptId);
        return  ResponseEntity.ok(receiptDto);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ReceiptDto>> getReceiptByClientId(@PathVariable Long clientId){
        List<ReceiptDto> list=receiptService.getReceiptsByClientId(clientId);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<ReceiptDto>> getReceiptByDate(@PathVariable LocalDate date){
        List<ReceiptDto>list=receiptService.getReceiptsByDate(date);
        return ResponseEntity.ok(list);

    }

    @GetMapping("/dates")
    public ResponseEntity<List<ReceiptDto>> getReceiptBetweenDates(@RequestParam LocalDate startDate,@RequestParam  LocalDate endDate){
        List<ReceiptDto>list=receiptService.getReceiptBetweenDates(startDate,endDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/date/after")
    public ResponseEntity<List<ReceiptDto>> getReceiptByDateAfter(@RequestParam LocalDate dateAfter){
        List<ReceiptDto>list=receiptService.getReceiptByDateAfter(dateAfter);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/date/before")
    public ResponseEntity<List<ReceiptDto>> getReceiptByDateBefore(@RequestParam LocalDate dateBefore){
        List<ReceiptDto>list=receiptService.getReceiptByDateBefore(dateBefore);
        return ResponseEntity.ok(list);

    }

    @GetMapping("/totalAmount")
    public ResponseEntity<List<ReceiptDto>> getByTotalAmount(@RequestParam Double totalAmount){
        List<ReceiptDto>list=receiptService.getByTotalAmount(totalAmount);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/totalAmount/greater")
    public ResponseEntity<List<ReceiptDto>> getByTotalAmountGreaterThan(@RequestParam Double amountGreater){
        List<ReceiptDto>list=receiptService.getByTotalAmountGreaterThan(amountGreater);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/totalAmount/less")
    public ResponseEntity<List<ReceiptDto>>getByTotalAmountLessThan(@RequestParam Double amountLess){
        List<ReceiptDto>list=receiptService.getByTotalAmountLessThan(amountLess);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/totalAmount/between")
    public ResponseEntity<List<ReceiptDto>> getByTotalAmountBetween(Double minAmount, Double maxAmount){
        List<ReceiptDto>list=receiptService.getByTotalAmountBetween(minAmount,maxAmount);
        return ResponseEntity.ok(list);
    }


    /*---------------- POST METHODS --------------------*/

    @PostMapping
    public ResponseEntity<ReceiptDto> createReceipt(ReceiptDto receiptDto){
        ReceiptDto createdReceipt= receiptService.createReceipt(receiptDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReceipt);
    }

    /*---------------- PUT METHODS --------------------*/
    @PutMapping("/{receiptId}")
    public ResponseEntity<ApiResponse> returnReceipt(@PathVariable Long receiptId){
       receiptService.returnReceipt(receiptId);
       return ResponseEntity.ok(new ApiResponse("Return successfully done."));
    }

}
