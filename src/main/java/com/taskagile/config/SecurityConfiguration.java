package com.taskagile.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
     private static final String[] PUBLIC = new String[] { "/error", "/login", "/logout", "/register",
               "/api/registrations" };

     @Override
     public void configure(WebSecurity web) throws Exception {
          web.ignoring().antMatchers("/static/**", "/css/**", "/js/**"
                                        , "/images/**", "/favicon.ico");
     }

     @Override
     protected void configure(HttpSecurity http) throws Exception {
          http
               .authorizeRequests()
                    .antMatchers(PUBLIC).permitAll()
                    .anyRequest().authenticated()
                    .and()
               .formLogin()
                    .loginPage("/login")
                    .and()
               .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logged-out")
                    .and()
               .csrf().disable();
     }

     @Bean
     public PasswordEncoder passwordEncoder() {
          return new BCryptPasswordEncoder();
     }
}
