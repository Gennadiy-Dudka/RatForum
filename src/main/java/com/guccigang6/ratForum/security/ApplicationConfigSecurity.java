package com.guccigang6.ratForum.security;

import com.guccigang6.ratForum.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.RequestDispatcher;

@Configuration
@EnableWebSecurity
public class ApplicationConfigSecurity extends WebSecurityConfigurerAdapter {

    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationConfigSecurity(UserAccountService userAccountService,
                                     PasswordEncoder passwordEncoder){
        this.userAccountService = userAccountService;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth){
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/topic/create").authenticated()
            .antMatchers(HttpMethod.POST, "/topic/**").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureHandler((req,resp,exc)->{
                    String err;
                    if(exc.getClass().isAssignableFrom(BadCredentialsException.class)){
                        err = "Invalid Username or Password";
                    } else {
                        err = "Unknown error";
                    }
                    req.setAttribute("error", err);
                    RequestDispatcher dis = req.getServletContext()
                            .getRequestDispatcher("/WEB-INF/views/loginView.jsp");
                    dis.forward(req, resp);
                });
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userAccountService);
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
