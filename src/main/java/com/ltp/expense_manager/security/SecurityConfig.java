package com.ltp.expense_manager.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.ltp.expense_manager.security.filter.AuthenticationFilter;
import com.ltp.expense_manager.security.filter.ExceptionHandlerFilter;
import com.ltp.expense_manager.security.filter.JWTAuthorizationFilter;
import com.ltp.expense_manager.security.manager.CustomAuthenticationManager;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import lombok.AllArgsConstructor;


@Configuration
@AllArgsConstructor
public class SecurityConfig {
    
    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http        
            .headers().frameOptions().disable()
            .and()
            .csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers(antMatcher("/h2/**")).permitAll()
            .requestMatchers(antMatcher(HttpMethod.POST, SecurityConstants.REGISTER_PATH)).permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        return http.build();
    }
}
