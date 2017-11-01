package app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.entity.ChannelStatistic;
import app.entity.SellerSettle;
import app.entity.SellerStatistic;

public interface SellerSettleService {

	SellerSettle findById(Long id);
	
	SellerSettle create(SellerSettle statistic);
	
	void store(MultipartFile file) throws Exception;
	
	List<Object[]> findByUsername(Long id);
	
	List<Double> findTotalIncomeByUsername(Long id);
	
	List<SellerSettle> findALl();
}
