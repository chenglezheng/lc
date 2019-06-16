package com.lc.clz.service;

import java.util.List;

import org.springframework.security.oauth2.provider.ClientDetails;

/**
 * 客户端接口。
 * @author Ben
 * @since 1.0.0
 * @version 1.0.0
 */
public interface ClientDetailsService extends org.springframework.security.oauth2.provider.ClientDetailsService {
	
	/**
	 * 获取所有客户端。
	 * @return 客户端列表。
	 */
	List<ClientDetails> getClients();

}
