package com.security.services.config.oauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import com.security.services.config.exceptions.ExceptionTranslator;


@Configuration
@EnableAuthorizationServer
public class OAuth2ConfigAuthorizationServer extends AuthorizationServerConfigurerAdapter {


    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Value("${config.oauth2.validity.tokenTimeout}")
    private int expiration;

    @Value("${config.oauth2.validity.refresh-token}")
    private int refreshTokenValidity;

    @Value("${config.oauth2.clientID}")
    private String clientId;

    @Value("${config.oauth2.clientSecret}")
    private String clientSecret;

    @Value("${config.oauth2.resource.id}")
    private String resourceId;


    @Autowired
    private ExceptionTranslator customWebResponseExceptionTranslator;

    @Autowired
    private PasswordEncoder PasswordEncoder;


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        String secretEncoded = PasswordEncoder.encode(clientSecret);
        clients
                .inMemory()
                .withClient(clientId)
                .authorizedGrantTypes("client_credentials", "password", "refresh_token")
                .scopes("read", "write")
                .resourceIds(resourceId)
                .accessTokenValiditySeconds(expiration)
                .refreshTokenValiditySeconds(refreshTokenValidity)
                .secret(secretEncoded);

    }

    /**
     * Defines the authorization and token endpoints and the token services
     * 
     * @param endpoints
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        endpoints
                .userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .tokenStore(tokenStore)
                .exceptionTranslator(exception -> {
                    return customWebResponseExceptionTranslator.translate(exception);
                });;

    }

}
