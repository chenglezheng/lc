package com.lc.clz.service;

import com.lc.clz.entities.Page;
import com.lc.clz.entities.user.AppUser;
import com.lc.clz.entities.user.LoginAppUser;
import com.lc.clz.entities.user.SysRole;

import java.util.Map;
import java.util.Set;



public interface AppUserService {

	void addAppUser(AppUser appUser);

	void updateAppUser(AppUser appUser);

	LoginAppUser findByUsername(String username);

	AppUser findById(Long id);

	void setRoleToUser(Long id, Set<Long> roleIds);

	void updatePassword(Long id, String oldPassword, String newPassword);

	Page<AppUser> findUsers(Map<String, Object> params);

	Set<SysRole> findRolesByUserId(Long userId);

	void bindingPhone(Long userId, String phone);
}
