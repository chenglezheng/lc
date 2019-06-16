package com.lc.clz.properties;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * jwt 配置项。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
@Data
public class JwtProperties {
	
	/**
	 * 签名秘钥
	 */
	private String signingKey = "jarvis";
	
	/**
	 * 附加信息
	 */
	private Map<String, Object> additionalInformation = new HashMap<>();

	public String getSigningKey() {
		return signingKey;
	}

	public void setSigningKey(String signingKey) {
		this.signingKey = signingKey;
	}

	public Map<String, Object> getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(Map<String, Object> additionalInformation) {
		this.additionalInformation = additionalInformation;
	}
}
