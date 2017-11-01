package app.repository;

import app.entity.Task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaskRepository extends JpaRepository<Task, Long>{

    Task findFirstByOrderByIdDesc();
    
    Task findByCreater(Long id);
    
    @Query(value = "select count(1) from task where seller = :seller", nativeQuery = true)
    List<Object> findTaskCountBySellerId(@Param("seller")Long sellerId);
    
    @Query(value = "select id from task where seller = :seller", nativeQuery = true)
    Iterable<Object> findTaskBySellerId(@Param("seller")Long sellerId);
}
