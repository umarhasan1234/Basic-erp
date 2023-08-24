package com.nrt.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nrt.entity.Permission;
import com.nrt.entity.User;
import com.nrt.repository.UserPermissionRepository;
import com.nrt.repository.UserRepository;
import com.nrt.service.PermissionService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserPermissionRepository permissionRepository;

	@Override
	public Boolean assignPermissionsToUser(String userId, List<String> permissionNames) {
		// Step 1: Fetch the user
		log.info("add permission method called ");
		User user = userRepository.findByEmail(userId).orElse(null);
		System.out.println("user details"+user.toString());
		if (user == null) {
			log.info("user not found ");
			throw new EntityNotFoundException("User with ID " + userId + " not found.");
		}

		List<Permission> permissions = new ArrayList<>();
		for (String permissionName : permissionNames) {
			Permission permission = new Permission();
			permission.setRole(permissionName);
			permission.setUser(user);
			permissions.add(permission);
		}
		try {
			log.info("add permission method called try");
			List<Permission> plist = permissionRepository.saveAll(permissions);
			if (!plist.isEmpty()) {
				log.info("add permission method called ");
				return Boolean.TRUE;
			}
		} catch (Exception e) {
			log.info("add permission method called and exception occured");
			return Boolean.FALSE;
		}
		return Boolean.FALSE;
	}

}
