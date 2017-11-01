package app.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import app.entity.SubTask;
import app.entity.User;
import app.entity.ValueItem;
import app.repository.ChannelStatisticRepository;
import app.repository.*;
import app.service.PageQuery;
import app.service.ValueItemService;

@Service
public class ValueItemServiceImpl implements ValueItemService{
	
	@Inject ValueItemRepository valueItemRepository; 
	@Inject ChannelStatisticRepository statisticRepository;
	@Inject UserRepository userRepository;  
	
	@Override
	public Iterable<ValueItem> findAll(){
		return valueItemRepository.findAll();
	}
	
	@Override
	public Iterable<ValueItem> findAll(PageQuery query){
		return valueItemRepository.findAll(query.toPageable());
	}
	
	@Override
	public  List<Object> findValueItemCountByChannelId(Long id){
		return valueItemRepository.findCountByChannelId(id);
	}
	
	@Override
	public Iterable<ValueItem> findValueItemByChannelId(Long id){
		List<ValueItem> valueItemList = new ArrayList<>();
		Iterable<Object> ids = valueItemRepository.findIdsByChannelId(id);
		for(Object i:ids){
			Long idNum = ((Integer)i).longValue();
			valueItemList.add(valueItemRepository.findOne(idNum));
		}
		return valueItemList;
	} 
	
	@Override
	public ValueItem findById(Long id){
		return valueItemRepository.getOne(id);
	}
	
	@Override
	public Iterable<ValueItem> findBySubTaskId(Long id){
		return valueItemRepository.findBySubTaskId(id);
	}
	
	@Override
	public ValueItem findCurrentValueItemBySubTaskIdAndDate(Long id, Date date){
		ValueItem currentValueItem=null;
		Iterable<Object> ids = valueItemRepository.findCurrentValueItemIdBySubTaskIdAndDate(id, date);
		Iterable<ValueItem> valueItems = valueItemRepository.findAll();
		List<ValueItem> valueItemList = new ArrayList<>();
		for(Object i:ids){
			Long idNum = ((Integer)i).longValue();
			valueItemList.add(valueItemRepository.findOne(idNum));
		}
		if(valueItemList.size()>0){
			currentValueItem = valueItemList.get(0);
		}
		return currentValueItem;
	}
	
	@Override
	public ValueItem findCurrentValueItemBySubTaskIdAndChannelName(Long id, String channelName){
		ValueItem currentValueItem=null;
		User channel = userRepository.findByUsername(channelName);
		Iterable<Object> ids = valueItemRepository.findCurrentValueItemIdBySubTaskIdAndChannelName(id, channel.getId());
		Iterable<ValueItem> valueItems = valueItemRepository.findAll();
		List<ValueItem> valueItemList = new ArrayList<>();
		for(Object i:ids){
			Long idNum = ((Integer)i).longValue();
			valueItemList.add(valueItemRepository.findOne(idNum));
		}
		if(valueItemList.size()>0){
			currentValueItem = valueItemList.get(0);
		}
		return currentValueItem;
	}
	
	@Override
	public List<ValueItem> findCurrentValueItemBySubTaskId(Long id){
		List<ValueItem> valueItemList = new ArrayList<>();
		Iterable<Object> ids = valueItemRepository.findCurrentValueItemBySubTaskId(id);
		Iterable<ValueItem> valueItems = valueItemRepository.findAll();
		for(Object i:ids){
			Long idNum = ((Integer)i).longValue();
			valueItemList.add(valueItemRepository.findOne(idNum));
		}
		return valueItemList;
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
