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

import ftl.stockmanagementsystem.dto.ProductDto;
import ftl.stockmanagementsystem.entity.Product;
import ftl.stockmanagementsystem.repository.ProductRepository;

@RestController
@RequestMapping("/Product")
public class ProductController {
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping("/all")
	public List<Product> getAllProduct(){
		return repo.findAll();
	}
	//Display Product Based on ID
		@GetMapping("/{id}")
		public ResponseEntity<Object> getProduct(@PathVariable Long id) {
			   Product product = repo.findById(id).orElse(null);
			    if (id == null) {
			        return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
			    } else {
			        return ResponseEntity.ok(product);
			    }
			}
		
		//Saving A Product
				@PostMapping
				@RequestMapping("/addProduct")
				public ResponseEntity<Object> createProduct(@Validated @RequestBody ProductDto productDto){
					
					Product product = new Product();
					product.setName(productDto.getName());
					product.setDescription(productDto.getDescription());
					product.setPrice(productDto.getPrice());
					product.setQuantity(productDto.getQuantity());
					product.setCategory(productDto.getCategory());
					
					repo.save(product);
					
					return ResponseEntity.ok(product);
					
				}
				
				// UPDATING product INFO
				
				@PutMapping("/{id}")
				public ResponseEntity<Object> updateProduct(@PathVariable Long id, @Validated @RequestBody ProductDto productDto) {
				    // Retrieve the existing student from the repository
				    Product product = repo.findById(id).orElse(null);
				    if (product == null) {
				        return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
				    }
				    product.setName(productDto.getName());
				    product.setDescription(productDto.getDescription());
				    product.setPrice(productDto.getPrice());
				    product.setQuantity(productDto.getQuantity());
				    product.setCategory(productDto.getCategory());
				    repo.save(product);
				    return ResponseEntity.ok(product);
				}
				
				//DELETING PRODUCT INFO
				
				@DeleteMapping("/{id}")
				public ResponseEntity<Object> deleteProduct (@PathVariable Long id){
					Product product = repo.findById(id).orElse(null);
				    if (product == null) {
				        return new ResponseEntity<>("Product with ID " + id + " not found", HttpStatus.NOT_FOUND);
				    }
				    
				    repo.delete(product);
				    return ResponseEntity.status(HttpStatus.OK).body("Product with id " + id + " deleted successfully");
					
				}
				

}
