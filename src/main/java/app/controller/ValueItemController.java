package app.controller;

import java.util.Date;
import java.util.HashMap;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.entity.Role;
import app.entity.Seller;
import app.entity.SubTask;
import app.entity.Task;
import app.entity.User;
import app.entity.ValueItem;
import app.entity.ValueWay;
import app.entity.WebSite;
import app.service.PageQuery;
import app.service.SellerService;
import app.service.SubTaskService;
import app.service.TaskService;
import app.service.UserService;
import app.service.ValueItemService;
import app.service.ValueWayService;
import app.service.WebSiteService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ValueItemController {

	@Inject
	private TaskService taskService;
	@Inject ValueItemService valueItemService;
	@Inject UserService userService;
	@Inject WebSiteService webSiteService;
	@Inject SellerService sellerService;
	@Inject SubTaskService subTaskService;
	@Inject ValueWayService valueWayService;

	@ModelAttribute("allTasks")
    public List<Task> populateTasks() {
		return (List<Task>) taskService.findAll();
    }
	
	@ModelAttribute("allSellers")
	public List<Seller> populateSeller(){
		return (List<Seller>)sellerService.findAll();
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
	
	@ModelAttribute("allChannels")
	public List<User> populateChannels(){
		List<User> allChannels = (List<User>) userService.findAllChannel();
		return  allChannels;
	}
	
	@ModelAttribute("allValueWays")
    public List<ValueWay> populateValueWays() {
		return (List<ValueWay>) valueWayService.getAll();
    }
	
	@GetMapping("/valueItems/task/seller")
	public @ResponseBody
	Map<String, String> getSellerTask(@RequestParam("seller") Long seller) {
		Map<String, String> sellerTasks = new HashMap<>();
		Iterable<Task> taskEntities =  taskService.findBySellerId(seller);
		for(Task task:taskEntities){
			sellerTasks.put(String.valueOf(task.getId()), task.getTaskName());
		}
		return sellerTasks;
	}
	
	@GetMapping("/valueItems/subTask/task")
	public @ResponseBody
	Map<String, String> getTaskSubTask(@RequestParam("task") Long task) {
		Map<String, String> subTasksMap = new HashMap<>();
		Iterable<SubTask> subTaskEntities =  subTaskService.findByTaskId(task);
		for(SubTask subTask:subTaskEntities){
			subTasksMap.put(String.valueOf(subTask.getId()), subTask.getName());
		}
		return subTasksMap;
	}
	
	@InitBinder     
	public void initBinder(WebDataBinder binder){
	     binder.registerCustomEditor(       Date.class,     
	                         new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));   
	}
	
	@GetMapping("/valueItems")
	public String list(PageQuery query, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		Iterable<ValueItem> valueItems;
		if(currentUser.hasRole(Role.CHANNEL)){
			valueItems = valueItemService.findByChannelId(currentUser.getId());
		}else{
			valueItems = valueItemService.findAll();
		}
		ValueItem one = valueItemService.findById(new Long(12));
		
		model.addAttribute("valueItems",valueItems);
		return "valueItem/list";
	}
	
	@GetMapping("/valueItems/{id}")
	public String listForChannel(@PathVariable("id") Long id, Model model) {
		List<ValueItem> valueItems = (List<ValueItem>) valueItemService.findByChannelId(id);
		model.addAttribute("valueItems", valueItems);
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
		}else{
			System.out.println(bindingResult.getAllErrors().toString());
		}
		return create(valueItem, model);
	}

	@GetMapping("/valueItems/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		ValueItem valueItem = valueItemService.findById(id);
		Iterable<ValueItem> allValueItem = valueItemService.findAll();
		//ValueItem one = valueItemService.findById(new Long(12));
		model.addAttribute("valueItem", valueItem);
		return "valueItem/edit";
	}

	@PostMapping("/valueItems/{id}/edit")
	public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("valueItem") ValueItem valueItem, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			valueItemService.update(id, valueItem, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/valueItems";
			else
				System.out.println(bindingResult.getAllErrors().toString());
		}else{
			System.out.println(bindingResult.getAllErrors().toString());
		}
		return "valueItem/edit";
	}

	@DeleteMapping("/valueItems/{id}")
	public String delete(@PathVariable("id") Long id) {
		valueItemService.delete(id);
		return "redirect:/valueItems";
	}
}
