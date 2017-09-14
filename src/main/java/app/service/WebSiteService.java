package app.service;

import org.springframework.validation.BindingResult;
import app.entity.ValueItem;
import app.entity.WebSite;

public interface WebSiteService {

	Iterable<WebSite> findAll();
	
	Iterable<WebSite> findAll(PageQuery query);
	
	WebSite findById(Long id);
		
	Iterable<WebSite> findByChannelId(Long id);
	
	WebSite create(WebSite website, BindingResult bindingResult);
	WebSite update(Long id, WebSite webSite, BindingResult bindingResult);
	void delete(Long id);	
}
