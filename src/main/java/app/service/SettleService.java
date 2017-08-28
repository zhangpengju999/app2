package app.service;

import app.entity.Settle;

public interface SettleService {

	Settle findById(Long id);
	
	Iterable<Settle> findAll();
}
