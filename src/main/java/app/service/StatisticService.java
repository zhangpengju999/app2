package app.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import app.entity.Statistic;

public interface StatisticService {

	Statistic findById(Long id);
	
	Statistic create(Statistic statistic);
	
	void store(Long id,MultipartFile file) throws Exception;
	
	List<Object[]> findByUsername(Long id);
	
	List<Double> findTotalIncomeByUsername(Long id);
	
	List<Object[]> findAllTaskStatistics();
	
	List<Object[]> findAllValueItemStatistics();
}
