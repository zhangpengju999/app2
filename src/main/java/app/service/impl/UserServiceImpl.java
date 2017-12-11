package app.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import app.entity.Authority;
import app.entity.Role;
import app.entity.User;
import app.repository.UserRepository;
import app.service.PageQuery;
import app.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	@Inject
	private UserRepository userRepository;

	@Inject
	private PasswordEncoder passwordEncoder;

	@Override
	public User findById(Long id) {
		return userRepository.findByIdAndEnabledTrue(id);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsernameAndEnabledTrue(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = findByUsername(username);
		if (user == null)
			throw new UsernameNotFoundException(username);
		return user;
	}

	@Override
	public Iterable<User> findAll() {
		return userRepository.findByEnabledTrue();
	}

	@Override
	public Page<User> findAll(int page, int size) {
		return userRepository.findByEnabledTrue(new PageRequest(page - 1, size));
	}

	@Override
	public Page<User> findAll(PageQuery query) {
		return userRepository.findByEnabledTrue(query.toPageable());
	}
	
	@Override
	public Iterable<User> findAllOperator(){
		Iterable<User> allUser = userRepository.findByEnabledTrue();
		List<User> res = new ArrayList<>();
		for(User user:allUser){
			if(user.hasRole(Role.OPERATOR)){
				res.add(user);
			}
		}
		return res;
	}
	
	@Override
	public Iterable<User> findAllChannel(){
		Iterable<User> allUser = userRepository.findByEnabledTrue();
		List<User> res = new ArrayList<>();
		for(User user:allUser){
			if(user.hasRole(Role.CHANNEL)){
				res.add(user);
			}
		}
		return res;
	}

	@Override
	public User create(User user, BindingResult bindingResult) {
		// TODO 检查用户名
		User newUser = userRepository.findByUsername(user.getUsername());
		if(newUser == null){
			newUser = new User();
		}
		newUser.setUsername(user.getUsername());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		newUser.setRealname(user.getRealname());
		newUser.setPassed(true);
		newUser.setBank(user.getBank());
		newUser.setBankCard(user.getBankCard());
		newUser.setEmail(user.getEmail());
		newUser.setIsPublic(user.getIsPublic());
		newUser.setPhoneNum(user.getPhoneNum());
		newUser.setEnabled(true);
		List<String> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			for (String r : roles) {
				Role role;
				try {
					role = Role.parse(r);
				} catch (IllegalArgumentException e) {
					continue;
				}
				newUser.addRole(role);
			}
		} else {
			//newUser.addRole(Role.CHANNEL);
		}
		return userRepository.save(newUser);
	}
	
	public User createLocal(User user, BindingResult bindingResult) {
		// TODO 检查用户名
		User newUser = userRepository.findByUsername(user.getUsername());
		if(newUser == null){
			newUser = new User();
		}
		newUser.setUsername(user.getUsername());
		newUser.setPassword(user.getPassword());
		newUser.setRealname(user.getRealname());
		newUser.setPassed(true);
		newUser.setBank(user.getBank());
		newUser.setBankCard(user.getBankCard());
		newUser.setEmail(user.getEmail());
		newUser.setIsPublic(user.getIsPublic());
		newUser.setPhoneNum(user.getPhoneNum());
		newUser.setEnabled(true);
		List<String> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			for (String r : roles) {
				Role role;
				try {
					role = Role.parse(r);
				} catch (IllegalArgumentException e) {
					continue;
				}
				newUser.addRole(role);
			}
		} else {
			//newUser.addRole(Role.CHANNEL);
		}
		return userRepository.save(newUser);
	}

	@Transactional
	@Override
	public User update(Long id, User user, BindingResult bindingResult) {
		// TODO 检查用户名
		User existedUser = findById(id);
		if (user.getPassword() != null&&user.getPassword().length()>0)
			{existedUser.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setPassword(passwordEncoder.encode(user.getPassword()));}
		else
			user.setPassword(existedUser.getPassword());
		
		if (user.getUsername() != null)
			existedUser.setUsername(user.getUsername());
		else
			user.setUsername(existedUser.getUsername());
		
		if (user.getRealname() != null)
			existedUser.setRealname(user.getRealname());
		else
			user.setRealname(existedUser.getRealname());
		
		if(user.getBank() != null)
			existedUser.setBank(user.getBank());
		else
			user.setBank(existedUser.getBank());
		
		if(user.getBankCard() != null)
			existedUser.setBankCard(user.getBankCard());
		else
			user.setBankCard(existedUser.getBankCard());
		
		if(user.getEmail() != null)
			existedUser.setEmail(user.getEmail());
		else
			user.setEmail(existedUser.getEmail());
		
		existedUser.setIsPublic(user.getIsPublic());
		
		if(user.getPhoneNum() != null)
			existedUser.setPhoneNum(user.getPhoneNum());
		else
			user.setPhoneNum(existedUser.getPhoneNum());
		
		List<String> roles = user.getRoles();
		if (roles != null && roles.size() > 0) {
			delete(id);
			return createLocal(user, bindingResult);
		}else{
			return userRepository.save(existedUser);
		}
	}

	@Override
	public void delete(Long id) {
		User user = findById(id);
		if (user != null) {
			userRepository.deleteUserById(id);
		}
	}

}
