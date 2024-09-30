package ftl.stockmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftl.stockmanagementsystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
