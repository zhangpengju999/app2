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
public class WebSiteController {

	@Inject WebSiteService WebSiteService;
	@Inject UserService userService;
	
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
	
	@GetMapping("/webSites")
	public String list(PageQuery query, Model model) {
		Page<WebSite> webSites = (Page<WebSite>) WebSiteService.findAll(query);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		Iterable<WebSite> myWebSites = WebSiteService.findByChannelId(currentUser.getId());
		model.addAttribute("webSites", webSites);
		model.addAttribute("myWebSites",myWebSites);
		return "website/list";
	}

	@GetMapping("/webSites/new")
	public String create(@ModelAttribute("webSite") WebSite webSite, Model model) {
		return "website/edit";
	}

	@PostMapping("/webSites/new")
	public String create(@Valid @ModelAttribute("webSite") WebSite webSite, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			webSite.setCreateTime(new Date());
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User currentUser = (User)auth.getPrincipal();
			webSite.setChannel(currentUser);
			WebSiteService.create(webSite, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/webSites";
		}
		return create(webSite, model);
	}

	@GetMapping("/webSites/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		WebSite webSite = WebSiteService.findById(id);
		model.addAttribute("webSite", webSite);
		return "website/edit";
	}

	@PostMapping("/webSites/{id}/edit")
	public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("webSite") WebSite webSite, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			WebSiteService.update(id, webSite, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/webSites";
		}
		return "website/edit";
	}

	@DeleteMapping("/webSites/{id}")
	public String delete(@PathVariable("id") Long id) {
		WebSiteService.delete(id);
		return "redirect:/websites";
	}
}
