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
    
}