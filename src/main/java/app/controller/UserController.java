package app.controller;

import java.util.List;
import java.math.BigInteger;


import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import app.entity.SubTask;
import app.entity.User;
import app.service.PageQuery;
import app.service.SubTaskService;
import app.service.UserService;
import app.service.ValueItemService;

@Controller
public class UserController {

	@Inject
	private UserService userService;
	
	@Inject
	private ValueItemService ValueItemService;

	@GetMapping("/channels")
	public String listChannels(PageQuery query, Model model) {
		List<User> users = (List<User>) userService.findAllChannel();
		for(User user:users){
			List<Object> valueItemCount = ValueItemService.findValueItemCountByChannelId(user.getId());
			int num = ((BigInteger)valueItemCount.get(0)).intValue();
			user.setNum(num);
		}
		model.addAttribute("channels", users);
		return "user/listChannel";
	}
	
	@GetMapping("/users")
	public String list(PageQuery query, Model model) {
		Page<User> users = userService.findAll(query);
		model.addAttribute("users", users);
		return "user/list";
	}

	@GetMapping("/users/new")
	public String create(@ModelAttribute("user") User user, Model model) {
		return "user/edit";
	}

	@PostMapping("/users/new")
	public String create(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			userService.create(user, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/users";
		}
		return create(user, model);
	}

	@GetMapping("/users/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		User user = userService.findById(id);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@PostMapping("/users/{id}/edit")
	public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			userService.update(id, user, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/users";
		}
		return "user/edit";
	}

	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable("id") Long id) {
		userService.delete(id);
		return "redirect:/users";
	}
	
	// my profiles begin
	
	@GetMapping("/myprofile")
	public String listMyProfile(PageQuery query, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		model.addAttribute("currentUser", currentUser);
		return "user/listMyProfile";
	}
	
	@GetMapping("/myprofile/{id}/edit")
	public String editMyProfile(@PathVariable("id") Long id,PageQuery query, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		model.addAttribute("currentUser", currentUser);
		return "user/editMyProfile";
	}
	
	@PostMapping("/myprofile/{id}/edit")
	public String editMyProfile(@PathVariable("id") Long id,@Valid @ModelAttribute("currentUser") User user, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			userService.update(id, user, bindingResult);
		      if (!bindingResult.hasErrors())
		        return "redirect:/myprofile";
		    }
		    return "user/editMyProfile";
	}

}
