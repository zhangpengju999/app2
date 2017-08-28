package app.repository;

import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import app.entity.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String> {

	User findByIdAndEnabledTrue(Long id);

	User findByUsernameAndEnabledTrue(String username);

	Collection<User> findByEnabledTrue();

	Page<User> findByEnabledTrue(Pageable pageable);

}
