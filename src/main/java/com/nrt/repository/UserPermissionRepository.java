package com.nrt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.stereotype.Repository;

import com.nrt.entity.Permission;
import com.nrt.entity.User;
@Repository
	public interface UserPermissionRepository extends JpaRepository<Permission, Long> {

	//	public Optional<User> findByEmail(String username);

	}

