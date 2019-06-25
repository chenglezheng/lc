package com.lc.clz.service;

import com.lc.clz.entities.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;


@FeignClient(name = "lc-user-center",fallback = SysPermissionService.SysPermissionServiceCallBack.class)
public interface SysPermissionService {

	/**
	 * 根绝角色ids获取权限集合
	 * 
	 * @param roleIds
	 * @return
	 */
	Set<SysPermission> findByRoleIds(Set<Long> roleIds);

	void save(SysPermission sysPermission);

	void update(SysPermission sysPermission);

	void delete(Long id);

	Page<SysPermission> findPermissions(Map<String, Object> params);

	@Component
	class SysPermissionServiceCallBack implements SysPermissionService{
		@Override
		public Set<SysPermission> findByRoleIds(Set<Long> roleIds) {
			return null;
		}

		@Override
		public void save(SysPermission sysPermission) {

		}

		@Override
		public void update(SysPermission sysPermission) {

		}

		@Override
		public void delete(Long id) {

		}

		@Override
		public Page<SysPermission> findPermissions(Map<String, Object> params) {
			return null;
		}
	}
}
