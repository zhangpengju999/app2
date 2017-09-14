package app.service.impl;

import javax.inject.Inject;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import app.entity.ValueItem;
import app.entity.WebSite;
import app.repository.StatisticRepository;
import app.repository.ValueItemRepository;
import app.repository.WebSiteRepository;
import app.service.PageQuery;
import app.service.ValueItemService;
import app.service.WebSiteService;

@Service
public class WebSiteServiceImpl implements WebSiteService{
	
	@Inject WebSiteRepository webSiteRepository; 
	@Inject StatisticRepository statisticRepository;
	
	@Override
	public Iterable<WebSite> findAll(){
		return webSiteRepository.findAll();
	}
	
	@Override
	public Iterable<WebSite> findAll(PageQuery query){
		return webSiteRepository.findAll(query.toPageable());
	}
	
	@Override
	public WebSite findById(Long id){
		return webSiteRepository.findOne(id);
	}
	
	@Override
	public Iterable<WebSite> findByChannelId(Long id){
		return webSiteRepository.findByChannelId(id);
	}
	
	
	@Override
	public WebSite create(WebSite webSite, BindingResult bindingResult){
		webSiteRepository.save(webSite);
		return webSiteRepository.findFirstByOrderByIdDesc();
	}
	
	@Override
	public WebSite update(Long id, WebSite webSite, BindingResult bindingResult){
		return webSiteRepository.save(webSite);
	}
	
	@Override
	public void delete(Long id){
		webSiteRepository.delete(id);
	}
}
