package app.controller;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import app.entity.Task;
import app.entity.User;
import app.entity.ValueItem;
import app.entity.WebSite;
import app.service.PageQuery;
import app.service.TaskService;
import app.service.UserService;
import app.service.ValueItemService;
import app.service.WebSiteService;

@Controller
public class ValueItemController {

	@Inject
	private TaskService taskService;
	@Inject ValueItemService valueItemService;
	@Inject UserService userService;
	@Inject WebSiteService webSiteService;

	@ModelAttribute("allTasks")
    public List<Task> populateValueWays() {
		return (List<Task>) taskService.findAll();
    }
	
	@ModelAttribute("allWebSites")
    public List<WebSite> populateWebSites() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		return (List<WebSite>) webSiteService.findByChannelId(currentUser.getId());
    }
	
	@ModelAttribute("allUsers")
	public List<User> populateUsers(){
		List<User> all = (List<User>)userService.findAll();
		return  all;
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}
	
	@GetMapping("/valueItems")
	public String list(PageQuery query, Model model) {
		Page<ValueItem> valueItems = (Page<ValueItem>) valueItemService.findAll(query);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		Iterable<ValueItem> myValueItems = valueItemService.findByChannelId(currentUser.getId());
		model.addAttribute("valueItems", valueItems);
		model.addAttribute("myValueItems",myValueItems);
		return "valueItem/list";
	}

	@GetMapping("/valueItems/new")
	public String create(@ModelAttribute("valueItem") ValueItem valueItem, Model model) {
		return "valueItem/edit";
	}

	@PostMapping("/valueItems/new")
	public String create(@Valid @ModelAttribute("valueItem") ValueItem valueItem, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			if(valueItem.getChannel()==null){
				Authentication auth = SecurityContextHolder.getContext().getAuthentication();
				User currentUser = (User)auth.getPrincipal();
				valueItem.setChannel(currentUser);
			}
			valueItemService.create(valueItem, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/valueItems";
		}
		return create(valueItem, model);
	}

	@GetMapping("/valueItems/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		ValueItem valueItem = valueItemService.findById(id);
		model.addAttribute("valueItem", valueItem);
		return "valueItem/edit";
	}

	@PostMapping("/valueItems/{id}/edit")
	public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("valueItem") ValueItem valueItem, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			valueItemService.update(id, valueItem, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/valueItems";
		}
		return "valueItem/edit";
	}

	@DeleteMapping("/valueItems/{id}")
	public String delete(@PathVariable("id") Long id) {
		valueItemService.delete(id);
		return "redirect:/valueItems";
	}
}
