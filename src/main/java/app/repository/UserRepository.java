package app.repository;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	User findByIdAndEnabledTrue(Long id);

	User findByUsernameAndEnabledTrue(String username);
	
	User findByUsername(String username);

	Collection<User> findByEnabledTrue();

	Page<User> findByEnabledTrue(Pageable pageable);
	
	@Transactional
	@Modifying
	@Query(value="delete from users where id = :id",nativeQuery=true)
	void deleteUserById(@Param("id") Long id);

}
