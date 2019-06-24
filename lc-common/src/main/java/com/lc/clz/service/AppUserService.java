package com.lc.clz.service;

import com.lc.clz.entities.AppUser;
import com.lc.clz.entities.LoginAppUser;
import com.lc.clz.entities.Page;
import com.lc.clz.entities.SysRole;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@FeignClient(name = "lc-user-center",fallback = AppUserService.AppUserServiceCallBack.class)
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

	@Component
	class AppUserServiceCallBack implements AppUserService{
		@Override
		public void addAppUser(AppUser appUser) {

		}

		@Override
		public void updateAppUser(AppUser appUser) {

		}

		@Override
		public LoginAppUser findByUsername(String username) {
			return null;
		}

		@Override
		public AppUser findById(Long id) {
			return null;
		}

		@Override
		public void setRoleToUser(Long id, Set<Long> roleIds) {

		}

		@Override
		public void updatePassword(Long id, String oldPassword, String newPassword) {

		}

		@Override
		public Page<AppUser> findUsers(Map<String, Object> params) {
			return null;
		}

		@Override
		public Set<SysRole> findRolesByUserId(Long userId) {
			return null;
		}

		@Override
		public void bindingPhone(Long userId, String phone) {

		}
	}
}
