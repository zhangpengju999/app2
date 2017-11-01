package app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.entity.ChannelSettle;
import app.entity.ChannelStatistic;

public interface ChannelSettleService {

	ChannelSettle findById(Long id);
	
	ChannelSettle create(ChannelSettle statistic);
	
	void store(MultipartFile file) throws Exception;
	
	List<Object[]> findByUsername(Long id);
	
	List<Double> findTotalIncomeByUsername(Long id);
	
	List<ChannelSettle> findALl();
}
