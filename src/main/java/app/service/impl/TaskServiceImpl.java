package app.service.impl;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import app.entity.Task;
import app.entity.User;
import app.repository.TaskRepository;
import app.repository.UserRepository;
import app.service.PageQuery;
import app.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Inject TaskRepository taskRepository;
	@Inject SettleServiceImpl settleServiceImpl;
	@Inject UserRepository userRepository;
	
	@Override
	public Task findById(Long id) {
		return taskRepository.findOne(id);
	}
	
	//find by create user
	@Override
	public Task findByCreaterId(Long id) {
		return taskRepository.findByCreater(id);
	}

	@Override
	public Iterable<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Page<Task> findAll(int page, int size) {
		return taskRepository.findAll(new PageRequest(page - 1, size));
	}

	@Override
	public Page<Task> findAll(PageQuery query) {
		return taskRepository.findAll(query.toPageable());
	}
	
	@Override
	public List<Object> findTaskCountBySellerId(Long sellerId){
		return taskRepository.findTaskCountBySellerId(sellerId);
	}
	
	@Override
	public Iterable<Task> findBySellerId(Long sellerId){
		List<Task> taskList = new ArrayList<>();
		Iterable<Object> ids = taskRepository.findTaskBySellerId(sellerId);
		for(Object id:ids){
			Long idNum = ((Integer)id).longValue();
			taskList.add(taskRepository.findOne(idNum));
		}
		return taskList;
	}

	@Override
	public Task create(Task task, BindingResult bindingResult) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userRepository.findByUsernameAndEnabledTrue(userDetails.getUsername());
		task.setCreater(user);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		task.setCreateTime(time);
		taskRepository.save(task);
		return taskRepository.findFirstByOrderByIdDesc();
	}

	@Override
	public Task update(Long id, Task task, BindingResult bindingResult) {
		Task taskExist = taskRepository.findOne(id);
		task.setCreater(taskExist.getCreater());
		task.setCreateTime(taskExist.getCreateTime());
		return taskRepository.save(task);
	}

	@Override
	public void delete(Long id) {
		Task task = findById(id);
		if (task != null) {
			taskRepository.delete(id);
		}
	}
}
