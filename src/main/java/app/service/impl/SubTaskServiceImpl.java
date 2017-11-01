package app.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.google.common.collect.Lists;

import app.entity.SubTask;
import app.entity.ValueItem;
import app.repository.ChannelStatisticRepository;
import app.repository.SubTaskRepository;
import app.repository.ValueItemRepository;
import app.service.PageQuery;
import app.service.SubTaskService;
import app.service.ValueItemService;

@Service
public class SubTaskServiceImpl implements SubTaskService{
	
	@Inject SubTaskRepository subTaskRepository; 
	@Inject ChannelStatisticRepository statisticRepository;
	
	@Override
	public Iterable<SubTask> findAll(){
		return subTaskRepository.findAll();
	}
	
	@Override
	public Iterable<SubTask> findAll(PageQuery query){
		return subTaskRepository.findAll(query.toPageable());
	}
	
	@Override
	public SubTask findById(Long id){
		return subTaskRepository.findOne(id);
	}
	
	@Override
	public Iterable<SubTask> findByTaskId(Long id){
		return subTaskRepository.findByParentId(id);
	}
	
	@Override
	public List<SubTask> findByName(String name){
		Iterable<SubTask> subTasks = subTaskRepository.findByName(name);
		return Lists.newArrayList(subTasks);
	}
	
	@Override
	public SubTask create(SubTask subTask, BindingResult bindingResult){
		subTaskRepository.save(subTask);
		return subTaskRepository.findFirstByOrderByIdDesc();
	}
	
	@Override
	public SubTask update(Long id, SubTask subTask, BindingResult bindingResult){
		return subTaskRepository.save(subTask);
	}
	
	@Override
	public void delete(Long id){
		subTaskRepository.delete(id);
	}
}
