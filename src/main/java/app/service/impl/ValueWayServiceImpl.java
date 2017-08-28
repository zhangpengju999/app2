package app.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import app.entity.ValueWay;
import app.repository.ValueWayRepository;
import app.service.ValueWayService;

@Service
public class ValueWayServiceImpl implements ValueWayService{
	@Inject ValueWayRepository valueWayRepository;
	
	@Override
	public Iterable<ValueWay> getAll(){
		return valueWayRepository.findAll();
	}

}
