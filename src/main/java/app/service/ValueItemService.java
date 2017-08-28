package app.service;

import org.springframework.validation.BindingResult;
import app.entity.ValueItem;

public interface ValueItemService {

	Iterable<ValueItem> findAll();
	
	Iterable<ValueItem> findAll(PageQuery query);
	
	ValueItem findById(Long id);
	
	Iterable<ValueItem> findByTaskId(Long id);
	
	Iterable<ValueItem> findByChannelId(Long id);
	
	ValueItem create(ValueItem valueItem, BindingResult bindingResult);
	ValueItem update(Long id, ValueItem valueItem, BindingResult bindingResult);
	void delete(Long id);	
}
