package app.repository;

import app.entity.Settle;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SettleRepository extends JpaRepository<Settle, Long>{
	
	Settle findById(Long id);
}
