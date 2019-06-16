package com.lc.clz.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 安全配置项。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
@ConfigurationProperties(prefix = "jarvis.security")
@Data
@Component
public class SecurityProperties {
	
	/**
	 * 登录页面地址
	 *
	 */
	private String loginPage = "/login";
	
	/**
	 * Spring OAuth2 配置项
	 */
	private OAuth2Properties oauth2 = new OAuth2Properties();

	public OAuth2Properties getOauth2() {
		return oauth2;
	}

	public void setOauth2(OAuth2Properties oauth2) {
		this.oauth2 = oauth2;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}
}
