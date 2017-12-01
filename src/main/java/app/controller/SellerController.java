package app.controller;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import app.entity.Seller;
import app.entity.Settle;
import app.entity.Task;
import app.entity.ValueWay;
import app.service.PageQuery;
import app.service.SellerService;
import app.service.SettleService;
import app.service.TaskService;
import app.service.ValueWayService;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SellerController {

	@Inject
	private TaskService taskService;
	@Inject
	private ValueWayService valueWayService;
	@Inject SettleService settleService;
	@Inject SellerService sellerService;

	@ModelAttribute("allValueWays")
    public List<ValueWay> populateValueWays() {
		return (List<ValueWay>) valueWayService.getAll();
    }
	
	@ModelAttribute("allSettles")
	public List<Settle> populateSettles(){
		return (List<Settle>) settleService.findAll();
	}
	
	@GetMapping("/sellers")
	public String list(Model model) {
		Iterable<Seller> sellers = sellerService.findAll();
		for(Seller seller:sellers){
			List<Object> countList = taskService.findTaskCountBySellerId(seller.getId());
			seller.setNum(((BigInteger)countList.get(0)).intValue());
		}
		model.addAttribute("sellers", sellers);
		return "seller/list";
	}

	@GetMapping("/sellers/new")
	public String create(@ModelAttribute("seller") Seller seller, Model model) {
		return "seller/edit";
	}

	@PostMapping("/sellers/new")
	public String create(@Valid @ModelAttribute("seller") Seller seller, BindingResult bindingResult, Model model) {
		if (!bindingResult.hasErrors()) {
			sellerService.create(seller, bindingResult);
			if (!bindingResult.hasErrors())
				return "redirect:/sellers";
		}
		return create(seller, model);
	}

	@DeleteMapping("/sellers/{id}")
	public String delete(@PathVariable("id") Long id) {
		sellerService.delete(id);
		return "redirect:/sellers";
	}

}
