package api.repository;

import api.entity.channel;
import org.springframework.data.jpa.repository.JpaRepository;
import api.entity.friend;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface friendRepository  extends JpaRepository<friend,String> {

    @Query(value = "SELECT DISTINCT * FROM friend WHERE id = ?1",nativeQuery = true)
    List<friend> findById(String id);

    @Query(value = "SELECT * FROM friend WHERE id = ?1 and friend_id = ?2",nativeQuery = true)
    friend findfriend(String id, String userid);
}
