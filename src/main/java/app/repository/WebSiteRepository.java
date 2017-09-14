package app.repository;

import app.entity.WebSite;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WebSiteRepository extends JpaRepository<WebSite, Long>{
	WebSite findFirstByOrderByIdDesc();
    
    Iterable<WebSite> findByChannelId(Long id);
}