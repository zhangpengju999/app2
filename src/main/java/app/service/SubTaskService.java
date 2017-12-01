package app.service;

import java.util.List;

import org.springframework.validation.BindingResult;

import app.entity.SubTask;

public interface SubTaskService {

	Iterable<SubTask> findAll();
	
	Iterable<SubTask> findAllOn();
	
	Iterable<SubTask> findAllDown();
	
	Iterable<SubTask> findAll(PageQuery query);
	
	SubTask findById(Long id);
	
	Iterable<SubTask> findByTaskId(Long id);
	
	List<SubTask> findByName(String name);
		
	SubTask create(SubTask subTask, BindingResult bindingResult);
	SubTask update(Long id, SubTask subTask, BindingResult bindingResult);
	void delete(Long id);	
}
