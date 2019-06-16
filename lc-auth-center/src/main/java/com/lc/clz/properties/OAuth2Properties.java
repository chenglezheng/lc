package com.lc.clz.properties;

import lombok.Data;

/**
 * Spring OAuth2 配置项。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
@Data
public class OAuth2Properties {
	
	/**
	 * 允许的授权类型
	 */
	private String authorizedGrantTypes = "authorization_code,refresh_token";
	
	/**
	 * token 有效时间，单位秒，默认 2 小时
	 */
	private int accessTokenValiditySeconds = 3600 * 2;
	
	/**
	 * refresh_token 有效时间，单位秒，默认 1 个月
	 */
	private int refreshTokenValiditySeconds = 3600 * 24 * 30;
	
	/**
	 * jwt 签名秘钥
	 */
	private JwtProperties jwt = new JwtProperties();

	public String getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public int getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	public int getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	public JwtProperties getJwt() {
		return jwt;
	}

	public void setJwt(JwtProperties jwt) {
		this.jwt = jwt;
	}
}
