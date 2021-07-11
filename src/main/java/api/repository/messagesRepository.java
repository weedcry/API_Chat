package api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import api.entity.messages;

public interface messagesRepository extends JpaRepository<messages,Long> {
	@Query(value = "SELECT m.* FROM messages as m WHERE m.id = ?1 and m.channel_id = ?2",nativeQuery = true)
	messages findById(Long id,Long channel_id);
	
	@Query(value = "SELECT m.* FROM messages as m WHERE m.channel_id = ?1 and status != 0",nativeQuery = true)
	List<messages> findByChannel_General(Long channel_general);

	@Query(value = "SELECT m.* FROM messages as m WHERE m.channel_id = ?1 and m.id = (SELECT MAX(m.id) FROM messages as m where m.channel_id = ?1)",nativeQuery = true)
	messages findMessageByChannel_General(Long channel_general);

	@Query(value = "select * from messages where id in(SELECT max(m.id) " +
			"FROM (select * from messages where channel_id in (select id from channel where author_id = ?1  and exits = 1) and status != 0  ) as m  " +
			"group by m.channel_id) ORDER BY channel_id;",nativeQuery = true)
	List<messages> findLastMessageByUserid(String userid);

	@Query(value = "UPDATE messages set status = 1 where channel_id = ?1 and status = 2",nativeQuery = true)
	int updateStatusMessages(Long channel_id);
	
}
