package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.entity.user;

public interface userRepository extends JpaRepository<user, String>{
	user findById(String id);
	
	@Query(value = "select * from user where user.id = ?1", nativeQuery = true)
	user findByQ(String id);
	
}
