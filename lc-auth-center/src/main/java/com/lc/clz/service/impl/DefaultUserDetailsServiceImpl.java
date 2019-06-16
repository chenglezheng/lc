package com.lc.clz.service.impl;

import java.util.Collection;

import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 默认的用户接口实现。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
public class DefaultUserDetailsServiceImpl implements UserDetailsService {
	
	private static final String PREFIX = "spring.security.user";
	
	private UserDetails user;
	
	public DefaultUserDetailsServiceImpl(Environment env, PasswordEncoder passwordEncoder) {
		user = new UserDetails() {
			
			private static final long serialVersionUID = -6023234551685987654L;

			@Override
			public boolean isEnabled() {
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				return true;
			}
			
			@Override
			public String getUsername() {
				return env.getProperty(PREFIX + "name", "user");
			}
			
			@Override
			public String getPassword() {
				return passwordEncoder.encode(env.getProperty(PREFIX + "password", "123456"));
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
			}
		};
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return user;
	}

}
