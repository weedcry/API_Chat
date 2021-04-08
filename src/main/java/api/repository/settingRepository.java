package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.entity.setting;

public interface settingRepository extends JpaRepository<setting, String>{
	setting findById(String id);
}
