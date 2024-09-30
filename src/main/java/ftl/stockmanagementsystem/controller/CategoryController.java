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

import ftl.stockmanagementsystem.entity.Category;
import ftl.stockmanagementsystem.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
    private CategoryRepository categoryRepo;
	
	// Get all categories
    @GetMapping("/all")
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }
    
 // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getCategory(@PathVariable Long id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category == null) {
            return new ResponseEntity<>("Category with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(category);
    }
    
 // Add a new category
    @PostMapping("/addCategory")
    public ResponseEntity<Object> createCategory(@Validated @RequestBody Category category) {
        categoryRepo.save(category);
        return ResponseEntity.ok(category);
    }
    
 // Update category by ID
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategory(@PathVariable Long id, @Validated @RequestBody Category updatedCategory) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category == null) {
            return new ResponseEntity<>("Category with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        category.setName(updatedCategory.getName());
        category.setDescription(updatedCategory.getDescription());
        categoryRepo.save(category);
        return ResponseEntity.ok(category);
    }
    
 // Delete category by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id) {
        Category category = categoryRepo.findById(id).orElse(null);
        if (category == null) {
            return new ResponseEntity<>("Category with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
        categoryRepo.delete(category);
        return ResponseEntity.status(HttpStatus.OK).body("Category with ID " + id + " deleted successfully");
    }
}
