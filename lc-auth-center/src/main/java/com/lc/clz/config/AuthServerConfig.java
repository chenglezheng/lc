package com.lc.clz.config;

import com.lc.clz.properties.SecurityProperties;
import com.lc.clz.service.ClientDetailsService;
import com.lc.clz.service.impl.DefaultClientDetailsServiceImpl;
import com.lc.clz.service.impl.DefaultUserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private Environment env;
	@Autowired
	private ClientDetailsService clientDetailsService;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	@Value("${security.oauth2.client.registered-redirect-uri}")
	private String registeredRedirectUri;
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
		clientDetailsService.getClients().forEach(client -> {
			builder.withClient(client.getClientId())
				.secret(client.getClientSecret())
				.authorizedGrantTypes(securityProperties.getOauth2().getAuthorizedGrantTypes().split(","))
				.accessTokenValiditySeconds(securityProperties.getOauth2().getAccessTokenValiditySeconds())
				.refreshTokenValiditySeconds(securityProperties.getOauth2().getRefreshTokenValiditySeconds())
				.scopes("all");
		});
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
		List<TokenEnhancer> enhancers = new ArrayList<>();
		enhancers.add(jwtTokenEnhancer());
		enhancers.add(jwtAccessTokenConverter());
		enhancerChain.setTokenEnhancers(enhancers);
		
		endpoints.tokenStore(jwtTokenStore())
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService)
			.tokenEnhancer(enhancerChain).accessTokenConverter(jwtAccessTokenConverter());
	}
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("isAuthenticated()");
	}
	
	@Bean
	@ConditionalOnMissingBean(ClientDetailsService.class)
	public ClientDetailsService defaultClientDetailsService() {
		return new DefaultClientDetailsServiceImpl(env, passwordEncoder());
	}
	
	@Bean
	@ConditionalOnMissingBean(UserDetailsService.class)
	public UserDetailsService defaultUserDetailsService() {
		return new DefaultUserDetailsServiceImpl(env, passwordEncoder());
	}
	
	@Bean
	@ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public TokenStore jwtTokenStore() {
		return new JwtTokenStore(jwtAccessTokenConverter());
	}
	
	@Bean
	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        /*KeyPair keyPair = new KeyStoreKeyFactory(
				new ClassPathResource("keystore.jks"), "foobar".toCharArray())
				.getKeyPair("test");
		converter.setKeyPair(keyPair);*/
		converter.setSigningKey(securityProperties.getOauth2().getJwt().getSigningKey());
        return converter;
	}
	
	@Bean
	public TokenEnhancer jwtTokenEnhancer() {
		return new TokenEnhancer() {
			
			@Override
			public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
				((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(securityProperties.getOauth2().getJwt().getAdditionalInformation());
				return accessToken;
			}
		};
	}
	
}
