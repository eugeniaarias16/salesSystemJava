package com.salesmanagement.system.repositories;

import com.salesmanagement.system.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt,Long> {

    List<Receipt> findByDate(LocalDate date);
    List<Receipt> findByClientId(Long clientId);
    List<Receipt> findByDateBetween(LocalDate startDate, LocalDate endDate);
    List<Receipt> findByDateAfter(LocalDate date);
    List<Receipt>findByDateBefore(LocalDate date);
    List<Receipt> findByTotalAmount(double totalAmount);
    List<Receipt> findByTotalAmountGreaterThan(double totalAmount);
    List<Receipt> findByTotalAmountLessThan(double totalAmount);
    List<Receipt> findByTotalAmountBetween(Double minTotalAmount, Double maxTotalAmount);



}
