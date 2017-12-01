package app.controller;

import java.nio.channels.Channel;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import app.entity.ChannelStatistic;
import app.entity.Role;
import app.entity.TaskStatistic;
import app.entity.User;
import app.entity.ValueItem;
import app.entity.ValueItemStatistic;
import app.entity.ValueWay;
import app.service.PageQuery;
import app.service.ChannelStatisticService;
import app.service.UserService;
import app.service.ValueItemService;

@Controller
public class ChannelStatisticController {

	@Inject ValueItemService valueItemService;
	@Inject UserService userService;
	@Inject ChannelStatisticService channelStatisticService;
	
	@PostMapping("/channelstatistic/upload")
	public String fileUpload(@RequestParam("file") MultipartFile file) throws Exception{
		channelStatisticService.store(file);
		System.out.println("The file path name"+file.getName());
		return "redirect:/channelstatistic";
	}
	
	@PostMapping("/channelstatistic/uploadcpas")
	public String fileUploadCpas(@RequestParam("file") MultipartFile file) throws Exception{
		channelStatisticService.store(file);
		System.out.println("The file path name"+file.getName());
		return "redirect:/channelstatisticcpas";
	}
	
	
	@GetMapping("/channelstatistic")
	public String listAllChannelStatistic(Model model) {
		List<ChannelStatistic> allChannelStatistic = channelStatisticService.findALl();
		List<ChannelStatistic> cpcChannelStatistic = new ArrayList<>();
		for(ChannelStatistic cs:allChannelStatistic){
			ValueItem vi = cs.getValueItem();
			if(null!=vi ){
				ValueWay vw = vi.getValueWay();
				if(null!=vw){
					String vwName = vw.getValueWayName();
					if("cpc".equals(vwName))
						cpcChannelStatistic.add(cs);
				}
				
			}
		}
		
		List<ChannelStatistic> channelStatistics = new ArrayList<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		if(currentUser.hasRole(Role.CHANNEL)){
			for(ChannelStatistic cs:cpcChannelStatistic){
				ValueItem vi = cs.getValueItem();
				if(currentUser.getUsername().equals(vi.getChannel().getUsername()))
						channelStatistics.add(cs);
			}
			allChannelStatistic = channelStatistics;
		}else{
			allChannelStatistic = cpcChannelStatistic;
		}
		
		model.addAttribute("allChannelStatistic",allChannelStatistic);
		return "channel_statistic/list";
	}
	
	@GetMapping("/channelstatisticcpas")
	public String listAllChannelStatisticCPAS(Model model) {
		List<ChannelStatistic> allChannelStatistic = channelStatisticService.findALl();
		List<ChannelStatistic> cpaChannelStatistic = new ArrayList<>();
		for(ChannelStatistic cs:allChannelStatistic){
			ValueItem vi = cs.getValueItem();
			if(null!=vi ){
				ValueWay vw = vi.getValueWay();
				if(null!=vw){
					String vwName = vw.getValueWayName();
					if("cpa".equals(vwName)||"cps".equals(vwName))
						cpaChannelStatistic.add(cs);
				}
				
			}
		}
		
		List<ChannelStatistic> channelStatistics = new ArrayList<>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = (User)auth.getPrincipal();
		if(currentUser.hasRole(Role.CHANNEL)){
			for(ChannelStatistic cs:cpaChannelStatistic){
				ValueItem vi = cs.getValueItem();
				if(currentUser.getUsername().equals(vi.getChannel().getUsername()))
						channelStatistics.add(cs);
			}
			allChannelStatistic = channelStatistics;
		}else{
			allChannelStatistic = cpaChannelStatistic;
		}
		
		model.addAttribute("allChannelStatistic",allChannelStatistic);
		return "channel_statistic/listcpas";
	}
	
	@DeleteMapping("/channelstatistic/{id}")
	public String delete(@PathVariable("id") Long id) {
		channelStatisticService.delete(id);
		return "redirect:/channelstatistic";
	}
	
	@DeleteMapping("/channelstatisticcpas/{id}")
	public String deleteCpas(@PathVariable("id") Long id) {
		channelStatisticService.delete(id);
		return "redirect:/channelstatisticcpas";
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
