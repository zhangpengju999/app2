package app.repository;

import app.entity.ValueItem;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ValueItemRepository extends JpaRepository<ValueItem, Long>{
    ValueItem findFirstByOrderByIdDesc();
    
    Iterable<ValueItem> findBySubTaskId(Long id);
    Iterable<ValueItem> findByChannelId(Long id);
    
    @Query(value = "select count(1) from value_item where channel_id = :id", nativeQuery = true)
    List<Object> findCountByChannelId(@Param("id") Long id);
    
    @Query(value = "select id from value_item where channel_id = :id", nativeQuery = true)
    Iterable<Object> findIdsByChannelId(@Param("id") Long id);
    
    @Query(value = "select id from value_item where sub_task_id = :id and date(on_line_time)<=curdate() and (date(down_line_time)>=curdate() or down_line_time is null)", nativeQuery = true)
    Iterable<Object> findCurrentValueItemBySubTaskId(@Param("id")Long id);
    
    @Query(value = "select id from value_item where sub_task_id = :id and date(on_line_time)<=:date and (date(down_line_time)>=:date or down_line_time is null)", nativeQuery = true)
    Iterable<Object> findCurrentValueItemIdBySubTaskIdAndDate(@Param("id")Long id, @Param("date")Date date);
    
    @Query(value = "select id from value_item where sub_task_id = :id and channel_id = :channelId", nativeQuery = true)
    Iterable<Object> findCurrentValueItemIdBySubTaskIdAndChannelName(@Param("id")Long id, @Param("channelId")Long channelId);
}