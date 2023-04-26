package com.ltp.expense_manager.security.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.expense_manager.entity.Person;
import com.ltp.expense_manager.security.SecurityConstants;
import com.ltp.expense_manager.security.manager.CustomAuthenticationManager;
import com.ltp.expense_manager.service.PersonService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private CustomAuthenticationManager authenticationManager;
    private PersonService personService;
    private Environment env;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            Person person = new ObjectMapper().readValue(request.getInputStream(), Person.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(person.getEmailId(), person.getPassword());
            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException();
        } 
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        Person person = personService.getPerson(authResult.getName());
        String token = JWT.create()
            .withSubject(authResult.getName())
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.TOKEN_EXPIRATION))
            .sign(Algorithm.HMAC512(env.getProperty("SECRET_KEY")));
        response.addHeader(SecurityConstants.AUTHORIZATION, SecurityConstants.BEARER + token);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("expiresIn", SecurityConstants.TOKEN_EXPIRATION);
        responseBody.put("personId", person.getId());
        new ObjectMapper().writeValue(response.getWriter(), responseBody);
    }


}
