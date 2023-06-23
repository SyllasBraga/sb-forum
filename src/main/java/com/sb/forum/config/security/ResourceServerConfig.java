package com.sb.forum.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final JwtTokenStore tokenStore;
    private static final String[] PUBLIC = {"/oauth/token", "/", "/swagger-ui.html/**", "/webjars/**",
            "/swagger-resources/**", "/v2/**", "/csrf/**"};
    private static final String[] USUARIO_OU_MODERADOR = {"topicos/**"};
    private static final String[] MODERADOR = {"usuarios/**"};

    public ResourceServerConfig(JwtTokenStore tokenStore) {
        this.tokenStore = tokenStore;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();

        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll()
                .antMatchers(HttpMethod.GET, USUARIO_OU_MODERADOR).permitAll()
                .antMatchers(USUARIO_OU_MODERADOR).hasAnyRole("user","moderator")
                .antMatchers(MODERADOR).hasRole("moderator")
                .anyRequest().authenticated();
    }
}
