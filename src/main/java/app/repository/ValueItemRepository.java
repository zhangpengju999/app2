package app.repository;

import app.entity.ValueItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ValueItemRepository extends JpaRepository<ValueItem, Long>{
    ValueItem findFirstByOrderByIdDesc();
    
    Iterable<ValueItem> findByTaskId(Long id);
    Iterable<ValueItem> findByChannelId(Long id);
}