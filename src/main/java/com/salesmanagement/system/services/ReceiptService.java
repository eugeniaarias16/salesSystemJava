package com.salesmanagement.system.services;

import com.salesmanagement.system.DTO.LineItemDto;
import com.salesmanagement.system.DTO.ReceiptDto;
import com.salesmanagement.system.entities.Client;
import com.salesmanagement.system.entities.Product;
import com.salesmanagement.system.entities.Receipt;
import com.salesmanagement.system.entities.ReceiptLine;
import com.salesmanagement.system.exceptions.InsufficientStockException;
import com.salesmanagement.system.exceptions.ResourceNotFoundException;
import com.salesmanagement.system.repositories.ClientRepository;
import com.salesmanagement.system.repositories.ProductRepository;
import com.salesmanagement.system.repositories.ReceiptRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService implements IReceiptService {

    private final ReceiptRepository receiptRepository;
    private  final ClientRepository clientRepository;
    private final ProductRepository productRepository;

    public ReceiptService(ReceiptRepository receiptRepository, ClientRepository clientRepository, ProductRepository productRepository) {
        this.receiptRepository = receiptRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }


    @Override
    public List<ReceiptDto> getAllReceipts() {
        return receiptRepository.findAll()
                .stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());

    }

    @Override
    public ReceiptDto getReceiptById(Long id) {
        Receipt existingReceipt=receiptRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Receipt with the id "+id+" was not found"));
        return new ReceiptDto(existingReceipt);
    }

    @Override
    public List<ReceiptDto> getReceiptsByClientId(Long clientId) {
        if(!clientRepository.existsById(clientId)){
            throw new ResourceNotFoundException("Client wit id "+clientId+" was not found.");
        }
        List<Receipt> receiptList=receiptRepository.findByClientId(clientId);

        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());

    }

    @Override
    public List<ReceiptDto> getReceiptsByDate(LocalDate date) {
        List<Receipt> receiptList=receiptRepository.findByDate(date);
        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDto> getReceiptBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Receipt> receiptList=receiptRepository.findByDateBetween(startDate,endDate);
        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDto> getReceiptByDateAfter(LocalDate date) {
        List<Receipt> receiptList=receiptRepository.findByDateAfter(date);
        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDto> getReceiptByDateBefore(LocalDate date) {
        List<Receipt> receiptList=receiptRepository.findByDateBefore(date);

        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDto> getByTotalAmount(double totalAmount) {
        List<Receipt> receiptList=receiptRepository.findByTotalAmount(totalAmount);

        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());
    }


    @Override
    public List<ReceiptDto> getByTotalAmountGreaterThan(double totalAmount) {
        List<Receipt> receiptList=receiptRepository.findByTotalAmountGreaterThan(totalAmount);
        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDto> getByTotalAmountLessThan(double totalAmount) {
        List<Receipt> receiptList=receiptRepository.findByTotalAmountLessThan(totalAmount);
        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceiptDto> getByTotalAmountBetween(Double minAmount, Double maxAmount) {
        List<Receipt> receiptList=receiptRepository.findByTotalAmountBetween(minAmount,maxAmount);
        return receiptList.stream()
                .map(ReceiptDto::new)
                .collect(Collectors.toList());
    }

    @Override
    public ReceiptDto createReceipt(ReceiptDto receiptDto) {
        //Validate customer existence
        Client client= clientRepository.findById(receiptDto.getClientId())
                .orElseThrow(()->new ResourceNotFoundException("Client was not found."));

        //Validate items existence
        List<LineItemDto> items=receiptDto.getLinesItems();
        if(items==null || items.isEmpty()){
            throw new IllegalArgumentException("The product list cannot be empty");
        }

       double totalAmount=0.00;
        List<ReceiptLine> receiptLines=new ArrayList<>();

        //Create each receiptLine
        for(LineItemDto item: items){
            Product product=productRepository.findById(item.getProductId())
                    .orElseThrow(()-> new ResourceNotFoundException("Product not found."));
            //We verify that there is sufficient stock.
            if(product.getStock()<item.getQuantity()){
                throw  new InsufficientStockException("Insufficient stock for the product "+product.getName());
            }

            product.decreaseStock(item.getQuantity());

            double lineTotal= product.getPrice()* item.getQuantity();
            totalAmount+=lineTotal;

            ReceiptLine line=new ReceiptLine();
            line.setProduct(product);
            line.setQuantity(item.getQuantity());
            line.setLineTotal(lineTotal);

            receiptLines.add(line);

        }

        //Prepare the receipt
        Receipt receipt= new Receipt();
        receipt.setClient(client);
        receipt.setDate(LocalDate.now());
        receipt.setTotalAmount(totalAmount);
        receipt.setLines(receiptLines);

        //Save
        Receipt savedReceipt=receiptRepository.save(receipt);
        productRepository.saveAll(receiptLines.stream()
                .map(ReceiptLine::getProduct)
                .toList()); //Save the updated products with new stock.

        return new ReceiptDto(savedReceipt);
    }

    @Override
    public void returnReceipt(Long receiptId) {
        //Verify receipt existence
        Receipt receipt=receiptRepository.findById(receiptId)
                .orElseThrow(()->new ResourceNotFoundException("Receipt was not found."));

        for(ReceiptLine line:receipt.getLines()){
            line.getProduct().increaseStock(line.getQuantity());
        }
        List<Product> productsToUpdate=receipt.getLines()
                .stream()
                .map(ReceiptLine::getProduct)
                .collect(Collectors.toList());
        productRepository.saveAll(productsToUpdate);
    }
}
