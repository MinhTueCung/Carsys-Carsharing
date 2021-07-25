package com.carsys.carsharing.basicauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private CustomUserDetailService userDetailsService;


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException
    {
        String username = auth.getName();
        String pwd = auth.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (user.getUsername().equals(username) && BCrypt.checkpw(pwd,user.getPassword())) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.getAuthorities().toString()));
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            SecurityContextHolder.setContext(sc);
            return new UsernamePasswordAuthenticationToken(username, pwd,user.getAuthorities());
        } else {
            throw new BadCredentialsException("User authentication failed!");
        }
    }

    public static String getNameOfTheMemberThatIsLoggedIn(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String getRoleOfTheMemberThatIsLoggedIn(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();
    }

    public static Authentication getAuthenticationOfTheMemberThatIsLoggedIn(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public boolean supports(Class<?>auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }

}
