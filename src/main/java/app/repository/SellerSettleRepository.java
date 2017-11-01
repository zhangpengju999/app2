package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import app.entity.ChannelStatistic;
import app.entity.SellerSettle;
import app.entity.SellerStatistic;

public interface SellerSettleRepository extends JpaRepository<SellerSettle, Long> {

	SellerSettle findById(Long id);
	
  @Query(value="select * from seller_settle where sub_task_id = :long1 and year = :year and month = :month",nativeQuery=true)
  List<Object[]> findBySubTaskIdAndDate(@Param("long1") Long long1,@Param("year") Long year,@Param("month") Long month);
  
  @Query(value="select value_item_id,task_id,income from channel_statistic join value_item on channel_statistic.value_item_id = value_item.id and value_item.channel_id = :channel_id",nativeQuery=true)
  List<Object[]> findByUsername(@Param("channel_id") Long channel_id);
  
  @Query(value="select sum(vincome) from (select value_item_id,channel_id,sum(income) vincome from channel_statistic join value_item on statistic.value_item_id = value_item.id group by value_item_id) a where a.channel_id=:channel_id",nativeQuery=true)
  List<Double> findTotalIncomeByUsername(@Param("channel_id") Long channel_id);
  
}
