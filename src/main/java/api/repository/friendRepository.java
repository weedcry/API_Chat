package api.repository;

import api.entity.channel;
import org.springframework.data.jpa.repository.JpaRepository;
import api.entity.friend;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface friendRepository  extends JpaRepository<friend,String> {

    List<friend> findById(String id);



}
