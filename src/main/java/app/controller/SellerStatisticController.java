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

import app.entity.ChannelStatistic;
import app.entity.SellerStatistic;
import app.entity.TaskStatistic;
import app.entity.User;
import app.entity.ValueItemStatistic;
import app.service.PageQuery;
import app.service.SellerStatisticService;
import app.service.ChannelStatisticService;
import app.service.UserService;
import app.service.ValueItemService;

@Controller
public class SellerStatisticController {

	@Inject ValueItemService valueItemService;
	@Inject UserService userService;
	@Inject SellerStatisticService sellerStatisticService;
	
	@PostMapping("/sellerstatistic/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file) throws Exception{
		sellerStatisticService.store(file);
		System.out.println("The file path name"+file.getName());
		return "redirect:/sellerstatistic";
	}
	
	
	@GetMapping("/sellerstatistic")
	public String listAllSellerStatistic(PageQuery query, Model model) {
		List<SellerStatistic> allSellerStatistic = sellerStatisticService.findALl();
		model.addAttribute("allSellerStatistic",allSellerStatistic);
		return "seller_statistic/list";
	}
	
	
//	@GetMapping("/statistic")
//	public String listAllTaskStatistic(PageQuery query, Model model) {
//		List<Object[]> statistics = channelStatisticService.findAllTaskStatistics();
//		List<TaskStatistic> taskStatistics = statistics.stream().map(s ->{
//				TaskStatistic ts = new TaskStatistic();
//				ts.setTaskId((Integer)s[0]);
//				ts.setTaskName((String)s[1]);
//				ts.setTatalIncome((Double)s[2]);
//				return ts;
//			})
//			.collect(Collectors.toList());
//		model.addAttribute("taskStatistics",taskStatistics);
//		return "statistic/list";
//	}
//	
//	@GetMapping("/valueItemStatistic")
//	public String listAllValueItemStatistic(PageQuery query, Model model) {
//		List<Object[]> statistics = channelStatisticService.findAllValueItemStatistics();
//		List<ValueItemStatistic> valueItemStatistics = statistics.stream().map(s ->{
//				ValueItemStatistic ts = new ValueItemStatistic();
//				ts.setValueItemId((Integer)s[0]);
//				ts.setTaskId((Integer)s[1]);
//				ts.setTotalIncome((Double)s[2]);
//				return ts;
//			})
//			.collect(Collectors.toList());
//		model.addAttribute("valueItemStatistics",valueItemStatistics);
//		return "statistic/valueItemList";
//	}
//	
//	@GetMapping("/myincome")
//	public String listmyincome(PageQuery query, Model model) {
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		User currentUser = (User)auth.getPrincipal();
//		List<Object[]> statistics = channelStatisticService.findByUsername(currentUser.getId());
//		List<ValueItemStatistic> valueItemStatistics = statistics.stream()
//				.map(s -> {
//					ValueItemStatistic vis = new ValueItemStatistic();
//					vis.setValueItemId((Integer)s[0]);
//					vis.setTaskId((Integer)s[1]);
//					vis.setTotalIncome((Double)s[2]);
//					return vis;
//				})
//				.collect(Collectors.toList());
//		
//		List<Double> total = channelStatisticService.findTotalIncomeByUsername(currentUser.getId());
//		double totalIncome=0;
//		if(total!=null&&total.size()>0){
//			if(total.get(0)!=null){
//				totalIncome = (Double)total.get(0);
//			}
//		}
//		
//		model.addAttribute("valueItemStatistics",valueItemStatistics);
//		model.addAttribute("totalIncome",totalIncome);
//		return "myincome/list";
//	}
	
}
