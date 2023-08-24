package com.nrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nrt.entity.Permission;

@Repository
public interface UserPermissionRepository extends JpaRepository<Permission, Long> {
}
