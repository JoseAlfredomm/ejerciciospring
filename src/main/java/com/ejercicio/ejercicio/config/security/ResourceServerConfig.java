package com.ejercicio.ejercicio.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

import com.ejercicio.ejercicio.service.UserService;
import com.ejercicio.ejercicio.utils.uri.SaleUri;

@Configuration
public class ResourceServerConfig  {
		
	@EnableResourceServer
    public class PaymentMethodConfig extends ResourceServerConfigurerAdapter {

        private static final String RESOURCE_ID = "my_rest_api";

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            resources.resourceId(RESOURCE_ID).stateless(false);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http
				.authorizeRequests()
					.antMatchers("/" + SaleUri.SALES + "**").authenticated()
				.and()
					.exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
        }

    }
		
		
}
