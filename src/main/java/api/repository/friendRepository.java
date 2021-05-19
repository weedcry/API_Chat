package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import api.entity.friend;

import java.util.List;

public interface friendRepository  extends JpaRepository<friend,String> {

    List<friend> findById(String id);

}
