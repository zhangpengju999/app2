package app.repository;

import app.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SellerRepository extends JpaRepository<Seller, Long>{
	
	Seller findById(Long id);
}
