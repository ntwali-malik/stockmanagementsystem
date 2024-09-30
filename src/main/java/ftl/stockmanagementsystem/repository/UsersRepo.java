package ftl.stockmanagementsystem.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ftl.stockmanagementsystem.entity.OurUsers;

import java.util.Optional;

public interface UsersRepo extends JpaRepository<OurUsers, Integer> {

    Optional<OurUsers> findByEmail(String email);
}
