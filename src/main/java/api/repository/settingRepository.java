package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.entity.setting;
import org.springframework.data.jpa.repository.Query;

public interface settingRepository extends JpaRepository<setting, String>{

	@Query(value = "select * from setting where id = ?1", nativeQuery = true)
	setting findById(String id);
}
