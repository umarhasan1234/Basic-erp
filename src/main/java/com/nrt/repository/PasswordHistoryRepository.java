package com.nrt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrt.entity.PasswordHistory;

@Repository
public interface PasswordHistoryRepository extends JpaRepository<PasswordHistory, Long> {
	List<PasswordHistory> findByUserIdOrderByCreatedAtDesc(Long userId);
}
