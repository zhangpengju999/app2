package app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import app.entity.Task;

public interface TaskService {

	Task findById(Long id);

	Task findByCreaterId(Long userId);

	Iterable<Task> findAll();

	Page<Task> findAll(int page, int size);

	Page<Task> findAll(PageQuery query);

	Task create(Task task, BindingResult bindingResult);

	Task update(Long id, Task user, BindingResult bindingResult);
	
	List<Object> findTaskCountBySellerId(Long sellerId);
	
	Iterable<Task> findBySellerId(Long sellerId);

	void delete(Long id);

}
