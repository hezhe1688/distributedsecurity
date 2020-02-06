package com.hezhe.security.distributed.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class TokenConfig {

    //采用对称加密方式
    private static final String SIGNING_KEY = "uaa123";

    /**
     * jwt解决了每次都要远程向认证服务校验令牌，在自己的资源服务就可以校验令牌
     *
     * @return
     */
    // jwt令牌存储策略
    @Bean
    public TokenStore tokenStore() {
        // Jwt令牌存储方案
        return new JwtTokenStore(assessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter assessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(SIGNING_KEY); //对称密钥，资源服务器使用改密钥来验证
        return converter;
    }

    /*// 普通令牌存储策略
    @Bean
    public TokenStore tokenStore()
    {
        // 内存方式，生成普通令牌
        return new InMemoryTokenStore();
    }*/

}
