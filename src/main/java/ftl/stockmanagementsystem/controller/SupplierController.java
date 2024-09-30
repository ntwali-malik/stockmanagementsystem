package ftl.stockmanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftl.stockmanagementsystem.entity.Supplier;
import ftl.stockmanagementsystem.repository.SupplierRepository;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

	 @Autowired
	    private SupplierRepository supplierRepository;
	 
	// Get all suppliers
	    @GetMapping
	    public List<Supplier> getAllSuppliers() {
	        return supplierRepository.findAll();
	    }
	    
	 // Get a supplier by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Supplier> getSupplierById(@PathVariable Long id) {
	        Optional<Supplier> supplier = supplierRepository.findById(id);
	        if (supplier.isPresent()) {
	            return ResponseEntity.ok(supplier.get());
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	 // Create a new supplier
	    @PostMapping("/add")
	    public Supplier createSupplier(@RequestBody Supplier supplier) {
	        return supplierRepository.save(supplier);
	    }
	    
	 // Update an existing supplier
	    @PutMapping("/{id}")
	    public ResponseEntity<Supplier> updateSupplier(@PathVariable Long id, @RequestBody Supplier updatedSupplier) {
	        Optional<Supplier> existingSupplier = supplierRepository.findById(id);
	        if (existingSupplier.isPresent()) {
	            Supplier supplier = existingSupplier.get();
	            supplier.setName(updatedSupplier.getName());
	            supplier.setContactNumber(updatedSupplier.getContactNumber());
	            supplier.setEmail(updatedSupplier.getEmail());
	            supplier.setAddress(updatedSupplier.getAddress());
	            return ResponseEntity.ok(supplierRepository.save(supplier));
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	    
	 // Delete a supplier by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id) {
	        Optional<Supplier> supplier = supplierRepository.findById(id);
	        if (supplier.isPresent()) {
	            supplierRepository.deleteById(id);
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
}
