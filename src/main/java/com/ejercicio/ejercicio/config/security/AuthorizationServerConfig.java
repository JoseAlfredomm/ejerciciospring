package com.ejercicio.ejercicio.config.security;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import javax.ws.rs.BeanParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.JdbcClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	private DataSource dataSource;
	
	@Autowired
	private EntityManager entityManager;
	
	
	@Autowired
	public  void setDataSource(DataSource dataSource) {	
		this.dataSource = dataSource;
	}
	
	@Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
	
	private boolean recreateDatabace = true;
	
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()")
				.checkTokenAccess("isAuthenticated()");
	}
	
	@Bean
	public TokenStore getTokenStore() {
		
		return new JdbcTokenStore(dataSource);
	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints)throws Exception {
	        endpoints
	        	.tokenStore(getTokenStore())
	        	.authenticationManager(authenticationManager);
	        
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		JdbcClientDetailsServiceBuilder detailsServiceBuilder = clients.jdbc(dataSource);
		if(recreateDatabace) {
			detailsServiceBuilder
				.withClient("simpleClient")
				.authorizedGrantTypes("implicit")
				.scopes("read")
				.autoApprove(true)
				.and()
				.withClient("clientAny")
				.redirectUris("^/login")
				.secret(passwordEncoder.encode("secret"))
				.authorities("password", "authorization_code", "refresh_token")
				.scopes("read");
		}
		
//		Local client
//		clients
//		.inMemory()
//		.withClient("ClientIdLocal")
//			.secret(passwordEncoder.encode("secret"))
//			.authorizedGrantTypes("implicit")
//			.scopes("read").autoApprove(true)
//		.and()
//			.withClient("ClientId")
//			.secret(passwordEncoder.encode("secret"))
//			.authorizedGrantTypes("password").scopes("read");
	}
	
	
	
}
