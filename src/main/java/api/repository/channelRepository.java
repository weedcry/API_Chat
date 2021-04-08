package api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.entity.channel;

public interface channelRepository extends JpaRepository<channel,Long> {
	@Query(value = "SELECT c.* FROM channel as c where c.id = ?1", nativeQuery = true)
	List<channel> findById(Long id);
	
	// hiển thị tất cả channel user tham gia 
	@Query(value = "SELECT c.* FROM channel as c where c.user_id = ?1", nativeQuery = true)
	List<channel> findByUser(String user_id);
	
	// hiển thị channel của user chỉ định
	@Query(value = "SELECT c.* FROM channel as c where c.id = ?1 and c.user_id = ?2", nativeQuery = true)
	channel findByUserId(Long id ,String user_id);
	
	
}
