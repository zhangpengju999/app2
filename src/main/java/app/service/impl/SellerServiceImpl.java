package app.service.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import app.entity.Seller;
import app.entity.Settle;
import app.repository.SellerRepository;
import app.repository.SettleRepository;
import app.service.SellerService;
import app.service.SettleService;

@Service
public class SellerServiceImpl implements SellerService{
	
	@Inject
	SellerRepository sellerRepository;
	
	@Override
	public Seller findById(Long id){
		return sellerRepository.findById(id);
	}
	
	@Override
	public Iterable<Seller> findAll(){
		return sellerRepository.findAll();
	}
	
	@Override
	public Seller create(Seller seller, BindingResult bindingResult){
		sellerRepository.save(seller);
		return sellerRepository.findById(seller.getId());
	}

    public void delete(Long id){
		Seller seller = sellerRepository.findById(id);
		if(seller!=null){
			sellerRepository.delete(seller);
		}
	}

}
