package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.entity.user;

import java.util.Optional;

public interface userRepository extends JpaRepository<user, String>{
	user findById(String id);

	@Query(value = "select * from user where user.id = ?1", nativeQuery = true)
	Optional<user> findByUserName(String id);

	Boolean existsById(String id);

	
}
