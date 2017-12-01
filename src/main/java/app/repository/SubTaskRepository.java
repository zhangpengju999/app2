package app.repository;

import app.entity.SubTask;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubTaskRepository extends JpaRepository<SubTask, Long>{
    SubTask findFirstByOrderByIdDesc();
    
    Iterable<SubTask> findByParentId(Long id);
    
    Iterable<SubTask> findByName(String name);
    
    @Query(value = "select * from sub_task where date(down_line_time)>=curdate() or down_line_time is null", nativeQuery = true)
    Iterable<SubTask> findAllOn();
    
    @Query(value = "select * from sub_task where date(down_line_time)<curdate()", nativeQuery = true)
    Iterable<SubTask> findAllDown();
    
}