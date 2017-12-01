package app.service;

import java.util.Date;
import java.util.List;

import org.springframework.validation.BindingResult;

import app.entity.SubTask;
import app.entity.ValueItem;

public interface ValueItemService {

	Iterable<ValueItem> findAll();
	
	Iterable<ValueItem> findAllOn();
	
	Iterable<ValueItem> findAllDown();
	
	Iterable<ValueItem> findAll(PageQuery query);
	
	ValueItem findById(Long id);
	
	List<Object> findValueItemCountByChannelId(Long id);
	
	Iterable<ValueItem> findValueItemByChannelId(Long id);
	
	List<ValueItem> findCurrentValueItemBySubTaskId(Long id);
	
	ValueItem findCurrentValueItemBySubTaskIdAndDate(Long id, Date date);
	
	ValueItem findCurrentValueItemBySubTaskIdAndChannelName(Long id, String channelName);
	
	Iterable<ValueItem> findBySubTaskId(Long id);
	
	Iterable<ValueItem> findByChannelId(Long id);
	
	Iterable<ValueItem> findByChannelIdOn(Long id);
	
	Iterable<ValueItem> findByChannelIdDown(Long id);
	
	ValueItem create(ValueItem valueItem, BindingResult bindingResult);
	ValueItem update(Long id, ValueItem valueItem, BindingResult bindingResult);
	void delete(Long id);	
}
