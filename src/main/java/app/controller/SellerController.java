//package app.controller;
//
//import java.util.List;
//
//import javax.inject.Inject;
//import javax.validation.Valid;
//
//import org.springframework.data.domain.Page;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import app.entity.Settle;
//import app.entity.Task;
//import app.entity.ValueWay;
//import app.service.PageQuery;
//import app.service.SellerService;
//import app.service.SettleService;
//import app.service.TaskService;
//import app.service.ValueWayService;
//
//@Controller
//public class SellerController {
//
//	@Inject
//	private TaskService taskService;
//	@Inject
//	private ValueWayService valueWayService;
//	@Inject SettleService settleService;
//	@Inject SellerService sellerService;
//
//	@ModelAttribute("allValueWays")
//    public List<ValueWay> populateValueWays() {
//		return (List<ValueWay>) valueWayService.getAll();
//    }
//	
//	@ModelAttribute("allSettles")
//	public List<Settle> populateSettles(){
//		return (List<Settle>) settleService.findAll();
//	}
//	
//	@GetMapping("/tasks")
//	public String list(PageQuery query, Model model) {
//		Page<Task> tasks = taskService.findAll(query);
//		model.addAttribute("tasks", tasks);
//		return "task/list";
//	}
//
//	@GetMapping("/tasks/new")
//	public String create(@ModelAttribute("task") Task task, Model model) {
//		return "task/edit";
//	}
//
//	@PostMapping("/tasks/new")
//	public String create(@Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model) {
//		if (!bindingResult.hasErrors()) {
//			taskService.create(task, bindingResult);
//			if (!bindingResult.hasErrors())
//				return "redirect:/tasks";
//		}
//		return create(task, model);
//	}
//
//	@GetMapping("/tasks/{id}/edit")
//	public String edit(@PathVariable("id") Long id, Model model) {
//		Task task = taskService.findById(id);
//		model.addAttribute("task", task);
//		return "task/edit";
//	}
//
//	@PostMapping("/tasks/{id}/edit")
//	public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("task") Task task, BindingResult bindingResult, Model model) {
//		if (!bindingResult.hasErrors()) {
//			taskService.update(id, task, bindingResult);
//			if (!bindingResult.hasErrors())
//				return "redirect:/tasks";
//		}
//		return "task/edit";
//	}
//
//	@DeleteMapping("/tasks/{id}")
//	public String delete(@PathVariable("id") Long id) {
//		taskService.delete(id);
//		return "redirect:/tasks";
//	}
//
//}
