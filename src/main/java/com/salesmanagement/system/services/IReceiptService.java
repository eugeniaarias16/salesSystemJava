package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.ReceiptDto;

import java.time.LocalDate;
import java.util.List;

public interface IReceiptService  {
    List<ReceiptDto> getAllReceipts();
    ReceiptDto getReceiptById(Long id);
    List<ReceiptDto> getReceiptsByClientId(Long clientId);
    List<ReceiptDto> getReceiptsByDate(LocalDate date);
    List<ReceiptDto> getReceiptBetweenDates(LocalDate startDate, LocalDate endDate);
    List<ReceiptDto> getReceiptByDateAfter(LocalDate date);
    List<ReceiptDto> getReceiptByDateBefore(LocalDate date);
    List<ReceiptDto> getByTotalAmount(double totalAmount);
    List<ReceiptDto> getByTotalAmountGreaterThan(double totalAmount);
    List<ReceiptDto> getByTotalAmountLessThan(double totalAmount);
    List<ReceiptDto> getByTotalAmountBetween(Double minAmount,Double maxAmount);
    ReceiptDto createReceipt(ReceiptDto receiptDto);
    void returnReceipt(Long id);




}
