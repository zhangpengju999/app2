package app.service;

import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;

import app.entity.User;

public interface UserService {

	User findById(Long id);

	User findByUsername(String username);

	Iterable<User> findAll();
	
	Iterable<User> findAllOperator();
	
	Iterable<User> findAllChannel();

	Page<User> findAll(int page, int size);

	Page<User> findAll(PageQuery query);

	User create(User user, BindingResult bindingResult);

	User update(Long id, User user, BindingResult bindingResult);

	User delete(Long id);

}
