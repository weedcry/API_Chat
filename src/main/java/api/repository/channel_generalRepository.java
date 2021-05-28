package api.repository;

import api.entity.channel;
import org.springframework.data.jpa.repository.JpaRepository;

import api.entity.channel_general;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface channel_generalRepository extends JpaRepository<channel_general,Long> {

	channel_general findById(Long id);

	// hiển thị tất cả channel general user tham gia
	@Query(value = "select * from channel_general where id IN (select id from channel where author_id = ?1);", nativeQuery = true)
	List<channel_general> findByUserid(String userid);


}
