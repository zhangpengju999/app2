package app.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.entity.TaskStatistic;
import app.entity.User;
import app.entity.ValueItemStatistic;
import app.service.PageQuery;
import app.service.StatisticService;
import app.service.UserService;
import app.service.ValueItemService;

@Controller
public class StatisticController {

	@Inject ValueItemService valueItemService;
	@Inject UserService userService;
	@Inject StatisticService statisticService;
	
	@PostMapping("/valueItems/{id}/upload")
	public String fileUpload(@PathVariable("id")Long id, @RequestParam("file") MultipartFile file) throws Exception{
		statisticService.store(id,file);
		return "redirect:/valueItems";
	}
	
	
	@GetMapping("/statistic")
	public String listAllTaskStatistic(PageQuery query, Model model) {
		List<Object[]> statistics = statisticService.findAllTaskStatistics();
		List<TaskStatistic> taskStatistics = statistics.stream().map(s ->{
				TaskStatistic ts = new TaskStatistic();
				ts.setTaskId((Integer)s[0]);
				ts.setTaskName((String)s[1]);
				ts.setTatalIncome((Double)s[2]);
				return ts;
			})
			.collect(Collectors.toList());
		model.addAttribute("taskStatistics",taskStatistics);
		return "statistic/list";
	}
	
	@GetMapping("/valueItemStatistic")
	public String listAllValueItemStatistic(PageQuery query, Model model) {
		List<Object[]> statistics = statisticService.findAllValueItemStatistics();
		List<ValueItemStatistic> valueItemStatistics = statistics.stream().map(s ->{
				ValueItemStatistic ts = new ValueItemStatistic();
				ts.setValueItemId((Integer)s[0]);
				ts.setTaskId((Integer)s[1]);
				ts.setTotalIncome((Double)s[2]);
				return ts;
			})
			.collect(Collectors.toList());
		model.addAttribute("valueItemStatistics",valueItemStatistics);
		return "statistic/valueItemList";
	}
	
	@GetMapping("/myincome")
	public String listmyincome(PageQuery query, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		List<Object[]> statistics = statisticService.findByUsername(currentUser.getId());
		List<ValueItemStatistic> valueItemStatistics = statistics.stream()
				.map(s -> {
					ValueItemStatistic vis = new ValueItemStatistic();
					vis.setValueItemId((Integer)s[0]);
					vis.setTaskId((Integer)s[1]);
					vis.setTotalIncome((Double)s[2]);
					return vis;
				})
				.collect(Collectors.toList());
		
		List<Double> total = statisticService.findTotalIncomeByUsername(currentUser.getId());
		double totalIncome=0;
		if(total!=null&&total.size()>0){
			if(total.get(0)!=null){
				totalIncome = (Double)total.get(0);
			}
		}
		
		model.addAttribute("valueItemStatistics",valueItemStatistics);
		model.addAttribute("totalIncome",totalIncome);
		return "myincome/list";
	}
	
}
