package com.nrt.service;

import java.util.List;

public interface PermissionService {

	public Boolean assignPermissionsToUser(String userId, List<String> permissionList);
}
