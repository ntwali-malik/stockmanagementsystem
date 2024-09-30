package ftl.stockmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftl.stockmanagementsystem.entity.Product;
import ftl.stockmanagementsystem.entity.StockTransaction;
import ftl.stockmanagementsystem.repository.ProductRepository;
import ftl.stockmanagementsystem.repository.StockTransactionRepository;

@RestController
@RequestMapping("/transaction")
public class StockTransactionController {

	@Autowired
    private StockTransactionRepository transactionRepo;
	
	@Autowired
    private ProductRepository productRepo;
	
	// Get all transactions
    @GetMapping("/all")
    public List<StockTransaction> getAllTransactions() {
        return transactionRepo.findAll();
    }
    
 // Get transaction by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getTransaction(@PathVariable Long id) {
        StockTransaction transaction = transactionRepo.findById(id).orElse(null);
        if (transaction == null) {
            return new ResponseEntity<>("Transaction with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(transaction);
    }
    
 // Add a new transaction
    @PostMapping("/addTransaction")
    public ResponseEntity<Object> createTransaction(@Validated @RequestBody StockTransaction transaction) {
        // Check if the product exists
        Product product = productRepo.findById(transaction.getProduct().getId()).orElse(null);
        if (product == null) {
            return new ResponseEntity<>("Product with ID " + transaction.getProduct().getId() + " not found", HttpStatus.NOT_FOUND);
        }

        transaction.setProduct(product); // Set the product reference
        transactionRepo.save(transaction);
        return ResponseEntity.ok(transaction);
    }
    
 // Update an existing transaction by ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTransaction(@PathVariable Long id, @Validated @RequestBody StockTransaction updatedTransaction) {
        StockTransaction transaction = transactionRepo.findById(id).orElse(null);
        if (transaction == null) {
            return new ResponseEntity<>("Transaction with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        
        // Update transaction details
        transaction.setQuantity(updatedTransaction.getQuantity());
        transaction.setTransactionType(updatedTransaction.getTransactionType());
        transaction.setTransactionDate(updatedTransaction.getTransactionDate());
        transaction.setDescription(updatedTransaction.getDescription());

        transactionRepo.save(transaction);
        return ResponseEntity.ok(transaction);
    }
    
    // Delete transaction by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTransaction(@PathVariable Long id) {
        StockTransaction transaction = transactionRepo.findById(id).orElse(null);
        if (transaction == null) {
            return new ResponseEntity<>("Transaction with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        transactionRepo.delete(transaction);
        return ResponseEntity.status(HttpStatus.OK).body("Transaction with ID " + id + " deleted successfully");
    }


}
