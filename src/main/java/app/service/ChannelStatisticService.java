package app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.entity.ChannelStatistic;

public interface ChannelStatisticService {

	ChannelStatistic findById(Long id);
	
	ChannelStatistic create(ChannelStatistic statistic);
	
	void store(MultipartFile file) throws Exception;
	
	List<Object[]> findByUsername(Long id);
	
	List<Double> findTotalIncomeByUsername(Long id);
	
	List<Object[]> findAllTaskStatistics();
	
	List<Object[]> findAllValueItemStatistics();
	
	List<ChannelStatistic> findALl();
	
	void delete(Long id);
}
