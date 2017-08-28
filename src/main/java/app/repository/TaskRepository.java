package app.repository;

import app.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long>{

    Task findFirstByOrderByIdDesc();
    
    Task findByCreater(Long id);
}
