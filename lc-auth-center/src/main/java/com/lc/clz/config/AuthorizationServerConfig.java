package com.lc.clz.config;

import com.lc.clz.service.impl.RedisAuthorizationCodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 授权服务器配置
 *
 */
@Configuration
@EnableAuthorizationServer //授权服务器
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {


    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;


    /**
     * 使用jwt或者redis
     * 默认redis
     */
    @Value("${access_token.store-jwt:false}")
    private boolean storeWithJwt;

    @Autowired
    private RedisAuthorizationCodeServiceImpl redisAuthorizationCodeServices;

    /**
     * 存储令牌（access_token）
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
       /* if (storeWithJwt) {
            return new JwtTokenStore(accessTokenConverter());  //Jwt存储
        }*/
        return new RedisTokenStore(redisConnectionFactory); //redis存储
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(this.authenticationManager);
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory));
        endpoints.authorizationCodeServices(redisAuthorizationCodeServices);

    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients().checkTokenAccess("permitAll()"); // 允许表单形式的认证
    }


    /**
     * 我们将client信息存储到oauth_client_details表里<br>
     * 并将数据缓存到redis
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("system")//客户端ID
                .authorizedGrantTypes("password", "refresh_token")//设置验证方式
                .scopes("app")
                .secret("system")
                .accessTokenValiditySeconds(10000) //token过期时间
                .refreshTokenValiditySeconds(10000); //refresh过期时间
    }

}
