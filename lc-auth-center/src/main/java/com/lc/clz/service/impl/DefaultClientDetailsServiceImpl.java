package com.lc.clz.service.impl;

import com.lc.clz.service.ClientDetailsService;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 默认的客户端接口实现。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
@Data
@NoArgsConstructor
public class DefaultClientDetailsServiceImpl implements ClientDetailsService {
	
	private static final String PREFIX = "security.oauth2.client.";
	
	private ClientDetails client;
	
	public DefaultClientDetailsServiceImpl(Environment env, PasswordEncoder passwordEncoder) {
		client = new ClientDetails() {
			
			private static final long serialVersionUID = -4205332618503230407L;

			@Override
			public boolean isSecretRequired() {
				return true;
			}
			
			@Override
			public boolean isScoped() {
				return false;
			}
			
			@Override
			public boolean isAutoApprove(String scope) {
				return false;
			}
			
			@Override
			public Set<String> getScope() {
				return StringUtils.commaDelimitedListToSet(env.getProperty(PREFIX + "scope", "all"));
			}
			
			@Override
			public Set<String> getResourceIds() {
				return null;
			}
			
			@Override
			public Set<String> getRegisteredRedirectUri() {
				return StringUtils.commaDelimitedListToSet(env.getProperty(PREFIX + "registered-redirect-uri", "http://localhost:8080/code"));
			}
			
			@Override
			public Integer getRefreshTokenValiditySeconds() {
				return env.getProperty(PREFIX + "refresh-token-validity-seconds", Integer.class ,3600 * 24 * 30);
			}
			
			@Override
			public String getClientSecret() {
				return passwordEncoder.encode(env.getProperty(PREFIX + "client-secret", "eastsoft.cn"));
			}
			
			@Override
			public String getClientId() {
				return env.getProperty(PREFIX + "client-id", "eastsoft");
			}
			
			@Override
			public Set<String> getAuthorizedGrantTypes() {
				return StringUtils.commaDelimitedListToSet(env.getProperty(PREFIX + "authorized-grant-types", "authorization_code,password,refresh_token"));
			}
			
			@Override
			public Collection<GrantedAuthority> getAuthorities() {
				return null;
			}
			
			@Override
			public Map<String, Object> getAdditionalInformation() {
				return null;
			}
			
			@Override
			public Integer getAccessTokenValiditySeconds() {
				return env.getProperty(PREFIX + "access-token-validity-seconds", Integer.class, 3600 * 2);
			}
		};
	}
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		return client;
	}

	@Override
	public List<ClientDetails> getClients() {
		return Collections.singletonList(client);
	}

}
