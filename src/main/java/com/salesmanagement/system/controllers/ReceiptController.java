package com.salesmanagement.system.controllers;

import com.salesmanagement.system.DTO.ReceiptDto;
import com.salesmanagement.system.responses.CustomApiResponse;
import com.salesmanagement.system.services.ReceiptService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/receipts")
@Tag(name = "Receipt Controller", description = "Operations related to receipts (sales records)")
public class ReceiptController {

    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @Operation(summary = "Get all receipts", description = "Returns a list of all registered receipts.")
    @ApiResponse(responseCode = "200", description = "List retrieved successfully")
    @GetMapping
    public ResponseEntity<List<ReceiptDto>> getAllReceipts() {
        List<ReceiptDto> list = receiptService.getAllReceipts();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipt by ID", description = "Returns the receipt corresponding to the given ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Receipt found"),
            @ApiResponse(responseCode = "404", description = "Receipt not found", content = @Content)
    })
    @GetMapping("/{receiptId}")
    public ResponseEntity<ReceiptDto> getReceiptById(
            @Parameter(description = "ID of the receipt to retrieve") @PathVariable Long receiptId) {
        ReceiptDto receiptDto = receiptService.getReceiptById(receiptId);
        return ResponseEntity.ok(receiptDto);
    }

    @Operation(summary = "Get receipts by client ID", description = "Returns all receipts linked to a specific client.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<ReceiptDto>> getReceiptByClientId(
            @Parameter(description = "Client ID") @PathVariable Long clientId) {
        List<ReceiptDto> list = receiptService.getReceiptsByClientId(clientId);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipts by date", description = "Returns all receipts for a specific date.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/date/{date}")
    public ResponseEntity<List<ReceiptDto>> getReceiptByDate(
            @Parameter(description = "Date in format YYYY-MM-DD") @PathVariable LocalDate date) {
        List<ReceiptDto> list = receiptService.getReceiptsByDate(date);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipts between two dates", description = "Returns receipts issued between two dates.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/dates")
    public ResponseEntity<List<ReceiptDto>> getReceiptBetweenDates(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate) {
        List<ReceiptDto> list = receiptService.getReceiptBetweenDates(startDate, endDate);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipts after a date", description = "Returns receipts issued after a given date.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/date/after")
    public ResponseEntity<List<ReceiptDto>> getReceiptByDateAfter(
            @RequestParam LocalDate dateAfter) {
        List<ReceiptDto> list = receiptService.getReceiptByDateAfter(dateAfter);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipts before a date", description = "Returns receipts issued before a given date.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/date/before")
    public ResponseEntity<List<ReceiptDto>> getReceiptByDateBefore(
            @RequestParam LocalDate dateBefore) {
        List<ReceiptDto> list = receiptService.getReceiptByDateBefore(dateBefore);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipts with specific total amount", description = "Returns receipts with exact total amount.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/totalAmount")
    public ResponseEntity<List<ReceiptDto>> getByTotalAmount(
            @RequestParam Double totalAmount) {
        List<ReceiptDto> list = receiptService.getByTotalAmount(totalAmount);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipts with total amount greater than a value", description = "Returns receipts where total is greater than the provided amount.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/totalAmount/greater")
    public ResponseEntity<List<ReceiptDto>> getByTotalAmountGreaterThan(
            @RequestParam Double amountGreater) {
        List<ReceiptDto> list = receiptService.getByTotalAmountGreaterThan(amountGreater);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipts with total amount less than a value", description = "Returns receipts where total is less than the provided amount.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/totalAmount/less")
    public ResponseEntity<List<ReceiptDto>> getByTotalAmountLessThan(
            @RequestParam Double amountLess) {
        List<ReceiptDto> list = receiptService.getByTotalAmountLessThan(amountLess);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get receipts by total amount range", description = "Returns receipts where total amount is between two values.")
    @ApiResponse(responseCode = "200", description = "Receipts retrieved successfully")
    @GetMapping("/totalAmount/between")
    public ResponseEntity<List<ReceiptDto>> getByTotalAmountBetween(
            @RequestParam Double minAmount,
            @RequestParam Double maxAmount) {
        List<ReceiptDto> list = receiptService.getByTotalAmountBetween(minAmount, maxAmount);
        return ResponseEntity.ok(list);
    }

    @Operation(
            summary = "Create a new receipt",
            description = "Creates a new sales receipt with items and total.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Receipt data to be created",
                    required = true,
                    content = @Content(schema = @Schema(implementation = ReceiptDto.class))
            )
    )
    @ApiResponse(responseCode = "201", description = "Receipt created successfully")
    @PostMapping
    public ResponseEntity<ReceiptDto> createReceipt(
            @Valid @RequestBody ReceiptDto receiptDto) {
        ReceiptDto createdReceipt = receiptService.createReceipt(receiptDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReceipt);
    }

    @Operation(summary = "Mark receipt as returned", description = "Flags a receipt as returned (cancellation logic).")
    @ApiResponse(responseCode = "200", description = "Receipt marked as returned")
    @PutMapping("/{receiptId}")
    public ResponseEntity<CustomApiResponse> returnReceipt(
            @Parameter(description = "Receipt ID to mark as returned") @PathVariable Long receiptId) {
        receiptService.returnReceipt(receiptId);
        return ResponseEntity.ok(new CustomApiResponse("Return successfully done."));
    }
}
