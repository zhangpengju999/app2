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

import app.entity.SubTask;
import app.entity.Task;
import app.entity.User;
import app.entity.ValueItem;
import app.entity.WebSite;
import app.service.PageQuery;
import app.service.SubTaskService;
import app.service.TaskService;
import app.service.UserService;
import app.service.ValueItemService;
import app.service.WebSiteService;

@Controller
public class SubTaskController {

	@Inject
	private TaskService taskService;
	@Inject SubTaskService subTaskService;
	@Inject UserService userService;
	@Inject WebSiteService webSiteService;
	@Inject ValueItemService valueItemService;

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
		List<User> all = (List<User>) userService.findAllOperator();
		return  all;
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}
	
	@GetMapping("/subTasks")
	public String list(PageQuery query, Model model) {
		Page<SubTask> subTasks = (Page<SubTask>) subTaskService.findAll(query);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		//Iterable<ValueItem> myValueItems = subTaskService.findByChannelId(currentUser.getId());
		for(SubTask subTask:subTasks){
			List<ValueItem> currentValueItems = valueItemService.findCurrentValueItemBySubTaskId(subTask.getId());
			if(currentValueItems!=null && currentValueItems.size()>0)
				subTask.setCurrentValueItem(currentValueItems.get(0));
		}
		model.addAttribute("subTasks", subTasks);
		//model.addAttribute("myValueItems",myValueItems);
		return "subtask/list";
	}

	@GetMapping("/subTasks/new")
	public String create(@ModelAttribute("subTask") SubTask subTask, Model model) {
		return "subtask/edit";
	}

	@PostMapping("/subTasks/new")
	public String create(@Valid @ModelAttribute("subTask") SubTask subTask, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			subTaskService.create(subTask, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/subTasks";
		}
		return create(subTask, model);
	}

	@GetMapping("/subTasks/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		SubTask subTask = subTaskService.findById(id);
		model.addAttribute("subTask", subTask);
		return "subtask/edit";
	}

	@PostMapping("/subTasks/{id}/edit")
	public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("subTask") SubTask subTask, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			subTaskService.update(id, subTask, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/subTasks";
		}
		return "subtask/edit";
	}

	@DeleteMapping("/subTasks/{id}")
	public String delete(@PathVariable("id") Long id) {
		subTaskService.delete(id);
		return "redirect:/subTasks";
	}
}
