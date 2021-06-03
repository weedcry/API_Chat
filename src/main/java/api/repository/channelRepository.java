package api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.entity.channel;

public interface channelRepository extends JpaRepository<channel,Long> {
	
	// hiển thị tất cả channel user tham gia 
	@Query(value = "SELECT c.* FROM channel as c where c.author_id = ?1", nativeQuery = true)
	List<channel> findByauthorid(String author_id);

	// hiển thị channel của user chỉ định
	@Query(value = "SELECT c.* FROM channel as c where c.id = ?1 and c.author_id = ?2", nativeQuery = true)
	channel findOneByAuthorid(Long id ,String author_id);

	@Query(value = "select * from channel where author_id = ?1 and id in (select id from channel where author_id = ?2);", nativeQuery = true)
	List<channel> findchannelbyfriend(String username,String friendid);

}
