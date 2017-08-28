package app.service.impl;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import app.entity.ValueItem;
import app.repository.StatisticRepository;
import app.repository.ValueItemRepository;
import app.service.PageQuery;
import app.service.ValueItemService;

@Service
public class ValueItemServiceImpl implements ValueItemService{
	
	@Inject ValueItemRepository valueItemRepository; 
	@Inject StatisticRepository statisticRepository;
	
	@Override
	public Iterable<ValueItem> findAll(){
		return valueItemRepository.findAll();
	}
	
	@Override
	public Iterable<ValueItem> findAll(PageQuery query){
		return valueItemRepository.findAll(query.toPageable());
	}
	
	@Override
	public ValueItem findById(Long id){
		return valueItemRepository.findOne(id);
	}
	
	@Override
	public Iterable<ValueItem> findByTaskId(Long id){
		return valueItemRepository.findByTaskId(id);
	}
	
	@Override
	public Iterable<ValueItem> findByChannelId(Long id){
		return valueItemRepository.findByChannelId(id);
	}
	
	
	@Override
	public ValueItem create(ValueItem valueItem, BindingResult bindingResult){
		valueItemRepository.save(valueItem);
		return valueItemRepository.findFirstByOrderByIdDesc();
	}
	
	@Override
	public ValueItem update(Long id, ValueItem valueItem, BindingResult bindingResult){
		return valueItemRepository.save(valueItem);
	}
	
	@Override
	public void delete(Long id){
		valueItemRepository.delete(id);
	}
}
