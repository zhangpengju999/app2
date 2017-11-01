package app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.entity.ChannelStatistic;
import app.entity.SellerStatistic;

public interface SellerStatisticService {

	SellerStatistic findById(Long id);
	
	SellerStatistic create(SellerStatistic statistic);
	
	void store(MultipartFile file) throws Exception;
	
	List<Object[]> findByUsername(Long id);
	
	List<Double> findTotalIncomeByUsername(Long id);
	
	List<SellerStatistic> findALl();
}
