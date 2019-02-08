package com.example.demo.config;

import com.example.demo.security.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@Order(value = 1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    public void setUserDetailsService(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/login", "/registration", "/css/**", "/js/**", "/images/**","/error/**").permitAll();
        http.authorizeRequests().antMatchers("/todo/**").access("hasRole('ROLE_USER')");
        http.authorizeRequests().antMatchers("/admin/todo/users").access("hasRole('ROLE_ADMIN')");
        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin().loginPage("/login")
                .loginProcessingUrl("/login")
                .failureUrl("/login?loginFailed=true");

        http.rememberMe().userDetailsService(myUserDetailsService); // Remember-me aktif olur.
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }
}
