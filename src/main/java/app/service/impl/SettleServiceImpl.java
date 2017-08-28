package app.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import app.entity.Settle;
import app.repository.SettleRepository;
import app.service.SettleService;

@Service
public class SettleServiceImpl implements SettleService{
	
	@Inject
	SettleRepository settleRepository;
	
	@Override
	public Settle findById(Long id){
		return settleRepository.findById(id);
	}
	
	@Override
	public Iterable<Settle> findAll(){
		return settleRepository.findAll();
	}

}
