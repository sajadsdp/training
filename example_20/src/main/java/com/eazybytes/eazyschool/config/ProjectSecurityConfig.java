package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class ProjectSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure (HttpSecurity http)throws Exception{
        http.csrf().ignoringAntMatchers("/saveMsg").and()
                .authorizeRequests()
                .mvcMatchers("/dashboard").authenticated()
                .mvcMatchers("/displayMessages").hasRole("ADMIN")
                .mvcMatchers("/home").permitAll()
                .mvcMatchers("/holiday/**").permitAll()
                .mvcMatchers("/contact").permitAll()
                .mvcMatchers("/saveMsg").permitAll()
                .mvcMatchers("/courses").permitAll()
//                .mvcMatchers("/courses").authenticated()
                .mvcMatchers("/about").permitAll()
                .mvcMatchers("/login").permitAll()
                .and().formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
                .and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
                .and().httpBasic();

    }
//**   all page is open by any requests
//    @Override
//    protected void configure (HttpSecurity http)throws Exception{
//        http.authorizeRequests()
//                .anyRequest().permitAll()   .denyAll() **dont open by any request
//                .and().formLogin().and().httpBasic();
//
//    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user").password("12345").roles("USER")
                .and()
                .withUser("admin").password("12345").roles("ADMIN")
                .and().passwordEncoder( NoOpPasswordEncoder.getInstance());
    }
}
