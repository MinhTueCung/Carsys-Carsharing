package com.carsys.carsharing.basicauth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.Arrays;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors(withDefaults())
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/v1/member").authenticated()
                    .antMatchers(HttpMethod.PATCH, "/v1/member").authenticated()
                    .antMatchers(HttpMethod.POST, "/v1/bookings/else").authenticated()
                    .antMatchers(HttpMethod.GET, "/v1/bookings/else").authenticated()
                    .antMatchers(HttpMethod.GET, "/v1/bookings").authenticated()
                    .antMatchers(HttpMethod.DELETE, "/v1/bookings/{bookingID}").authenticated()
                    .antMatchers(HttpMethod.PATCH, "/v1/bookings/{bookingID}").authenticated()
                    .antMatchers(HttpMethod.GET, "/v1/bookings/{bookingID}").authenticated()
                    .antMatchers(HttpMethod.PATCH, "/v1/bookings/").authenticated()
                    .antMatchers(HttpMethod.GET, "/v1/cars").permitAll()
                    .antMatchers(HttpMethod.POST, "/login").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/member").permitAll()
                    .antMatchers(HttpMethod.POST, "/v1/login").permitAll()
                    .antMatchers(HttpMethod.GET, "/v1/login").permitAll()
                    .antMatchers(HttpMethod.GET, "/v1/carstation").permitAll()
                    .antMatchers(HttpMethod.GET, "/v1/logout").permitAll()
                    .antMatchers(HttpMethod.GET, "/v1/tariff").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .addLogoutHandler((request, response, auth) -> {
                            for (Cookie cookie : request.getCookies()) {
                                String cookieName = cookie.getName();
                                Cookie cookieToDelete = new Cookie(cookieName, null);
                                cookieToDelete.setMaxAge(0);
                                response.addCookie(cookieToDelete);
                            }
                        })
                )
                .httpBasic().authenticationEntryPoint(new NoPopupBasicAuthenticationEntryPoint());
    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("localhost:3000","*"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        configuration.setMaxAge((long)3600);
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "xsrf-token","Accept",
                                "Content-Type","Cookie","Accept-Encoding","Connection", "JSESSIONID"));
        configuration.setExposedHeaders(Arrays.asList("xsrf-token", "JSESSIONID","Cookie"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.authenticationProvider(customAuthenticationProvider);
    }
}