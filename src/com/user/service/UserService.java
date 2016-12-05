package com.user.service;

import com.user.vo.User;

import java.util.List;
import java.util.Set;

public interface UserService {
	/**
	 * @param username
	 * @return User
	 */
	User getUserByUsername(String username);

    List getUserList();

    Set getUserRolesSet(String username);

	Set getRolePermissionsSet(String role_name);

	User getUserAnthenticaition(String username, String password);

    void add(User user);
}
