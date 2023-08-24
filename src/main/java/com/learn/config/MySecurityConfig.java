package com.learn.config;

import com.learn.services.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
@Autowired
private CustomUserDetailService customUserDetailService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/public/**").hasRole("NORMAL")
                .antMatchers("/users/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                 .and()
                .formLogin()
                .loginPage("/signin")
                .loginProcessingUrl("/doLogin")
                .defaultSuccessUrl("/users/");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       // auth.inMemoryAuthentication().withUser("Ansh").password(this.passwordEncoder().encode("Ansh@123")).roles("NORMAL");
        //auth.inMemoryAuthentication().withUser("Manish").password(this.passwordEncoder().encode("Manish@123")).roles("ADMIN");
  auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }
    @Bean
   public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(10);
   }


}
