package com.sb.forum.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtAccessTokenConverter tokenConverter;
    private final JwtTokenStore tokenStore;
    private final AuthenticationManager authenticationManager;
    @Value("${security.oauth2.client.client-id}")
    private String clientName;
    @Value("${jwt.vality-seconds}")
    private int jwtValitySeconds;
    @Value(("${security.oauth2.client.client-secret}"))
    private CharSequence clientPassword;

    public AuthorizationServerConfig(BCryptPasswordEncoder passwordEncoder, JwtAccessTokenConverter tokenConverter,
                                     JwtTokenStore tokenStore, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.tokenConverter = tokenConverter;
        this.tokenStore = tokenStore;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient(clientName)
                .secret(passwordEncoder.encode(clientPassword))
                .scopes("read", "write")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(jwtValitySeconds);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore)
                .accessTokenConverter(tokenConverter);
    }
}
