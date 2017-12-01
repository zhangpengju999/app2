package app.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import app.entity.DivideRate;
import app.entity.ValueWay;
import app.repository.DivideRateRepository;
import app.repository.ValueWayRepository;
import app.service.DivideRateService;
import app.service.ValueWayService;

@Service
public class DivideRateServiceImpl implements DivideRateService{
	@Inject DivideRateRepository divideRateRepository;
	
	@Override
	public Iterable<DivideRate> getAll(){
		return divideRateRepository.findAll();
	}

}
