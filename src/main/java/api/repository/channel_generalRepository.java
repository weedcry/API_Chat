package api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import api.entity.channel_general;

public interface channel_generalRepository extends JpaRepository<channel_general,Long> {
	channel_general findById(Long id);
}
