package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.Statistic;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

	Statistic findById(Long id);
	
  @Query(value="select * from statistic where value_item_id = :long1 and date = :fromDate",nativeQuery=true)
  List<Object[]> findByvalueItemIdAndDate(@Param("long1") Long long1,@Param("fromDate") String fromDate);
  
  @Query(value="select value_item_id,task_id,income from statistic join value_item on statistic.value_item_id = value_item.id and value_item.channel_id = :channel_id",nativeQuery=true)
  List<Object[]> findByUsername(@Param("channel_id") Long channel_id);
  
  @Query(value="select sum(vincome) from (select value_item_id,channel_id,sum(income) vincome from statistic join value_item on statistic.value_item_id = value_item.id group by value_item_id) a where a.channel_id=:channel_id",nativeQuery=true)
  List<Double> findTotalIncomeByUsername(@Param("channel_id") Long channel_id);
  
  @Query(value="select a.task_id, b.task_name,sum(vincome) from ( select value_item_id,task_id,sum(income) as vincome from statistic join value_item on statistic.value_item_id = value_item.id group by value_item_id) a join task b on a.task_id = b.id group by b.id",nativeQuery=true)
  List<Object[]> findAllTaskStatistics();
  
  @Query(value="select value_item_id,task_id,income from statistic join value_item on statistic.value_item_id = value_item.id",nativeQuery=true)
  List<Object[]> findAllValueItemStatistics();
}
