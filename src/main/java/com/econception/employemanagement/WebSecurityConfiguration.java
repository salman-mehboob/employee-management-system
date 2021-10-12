package com.econception.employemanagement;


import com.econception.employemanagement.service.MyUserDetailsService;
import com.econception.employemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    // User user=new User();

    @Autowired
    MyUserDetailsService userDetailService;
    @Autowired
    UserService userService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http

                .authorizeRequests()
                .antMatchers("/user/registration").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/user/newEmployee").permitAll()
                .antMatchers("/user/addNewEmployee").permitAll()
                .antMatchers("/templates/**").permitAll()
                .anyRequest().authenticated().and().csrf().disable()

                .formLogin().loginPage("/login")
                .permitAll()
                .and()
                .logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll();
//                .and()

        ;

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/css/**", "/js/**", "/img/**","/fragments/footer","/fragments/header","/templates/**",
                        "/fonts/**", "/webjars/**", "/vendor/**", "/upload-photos/**", "/css/bootstrap.min.css.map",
                        "/js/main.js");
    }

}

