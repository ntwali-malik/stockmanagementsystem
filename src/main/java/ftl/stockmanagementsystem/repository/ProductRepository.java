package ftl.stockmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftl.stockmanagementsystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
