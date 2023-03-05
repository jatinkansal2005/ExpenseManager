package com.ltp.expense_manager.security.manager;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.ltp.expense_manager.entity.Person;
import com.ltp.expense_manager.service.PersonService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.*;


@Component
@AllArgsConstructor
public class CustomAuthenticationManager implements AuthenticationManager{
    private PersonService personServiceImpl;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Person person = personServiceImpl.getPerson(authentication.getName());
        if (!bCryptPasswordEncoder.matches(authentication.getCredentials().toString(), person.getPassword())) {
            throw new BadCredentialsException("You provided an incorrect password.");
        }

        return new UsernamePasswordAuthenticationToken(authentication.getName(), person.getPassword());
    }
}   
