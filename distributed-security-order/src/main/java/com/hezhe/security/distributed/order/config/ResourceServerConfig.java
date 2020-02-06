package com.hezhe.security.distributed.order.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    //采用对称加密方式
    public static final String RESOURCE_ID = "res1";

    //这边把认证服务中的TonkenStore复制过来了
    @Autowired
    TokenStore tokenStore;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**").access("#oauth2.hasScope('all')")
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        super.configure(http);
    }

    // 资源服务令牌解析服务

    /**
     * 使用远程服务请求授权服务器校验的token，必须制定校验token 的url， client_id,client_secret
     *  远程的不再需要，这边使用jwt方式
     * @return
     */
    /*@Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices service = new RemoteTokenServices();
        service.setCheckTokenEndpointUrl("http://localhost:53020/uaa/oauth/check_token");
        service.setClientId("c1");
        service.setClientSecret("secret");
        return service;
    }*/

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID) // 资源id
                .tokenStore(tokenStore) //采用jwt令牌校验
//                .tokenServices(tokenServices()) // 验证令牌的服务
                .stateless(true);
    }
}
