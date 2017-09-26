package app.service;

import org.springframework.validation.BindingResult;

import app.entity.Seller;
import app.entity.Task;
public interface SellerService {

	Seller findById(Long id);
	
	Iterable<Seller> findAll();
	
	Seller create(Seller seller, BindingResult bindingResult);

	void delete(Long id);
}
