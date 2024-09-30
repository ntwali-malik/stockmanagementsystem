package ftl.stockmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftl.stockmanagementsystem.entity.StockTransaction;

public interface StockTransactionRepository extends JpaRepository<StockTransaction, Long>{

}
