package com.isakayabasi.crudapplicationspringboot.config;


import com.isakayabasi.crudapplicationspringboot.service.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private EmployeeServiceImpl employeeServiceimpl;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(employeeServiceimpl);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }





    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(authenticationProvider());
        auth.inMemoryAuthentication()
                .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN")
                .and()
                .withUser("Reader1").password(passwordEncoder().encode("1234")).roles("READER")
                .and()
                .withUser("Reader2").password(passwordEncoder().encode("1234")).roles("READER")
                .and()
                .withUser("Reader3").password(passwordEncoder().encode("1234")).roles("READER")
                .and()
                .withUser("Reader4").password(passwordEncoder().encode("1234")).roles("READER")
                .and()
                .withUser("Reader5").password(passwordEncoder().encode("1234")).roles("READER")
                .and()
                .withUser("Reader6").password(passwordEncoder().encode("1234")).roles("READER")
                .and()
                .withUser("Reader7").password(passwordEncoder().encode("1234")).roles("READER")
                .and()
                .withUser("Reader8").password(passwordEncoder().encode("1234")).roles("READER")
                .and()
                .withUser("Reader9").password(passwordEncoder().encode("1234")).roles("READER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.httpBasic();
        http.formLogin();
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("ADMIN","READER")
                .antMatchers("/shownewEmployeeForm").hasRole("ADMIN")
                .antMatchers("/saveEmployee").hasRole("ADMIN")
                .antMatchers("/showFormForUpdate/{id}").hasRole("ADMIN")
                .antMatchers("/deleteEmployee/{id}").hasRole("ADMIN")

                .antMatchers("/css/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .permitAll();




    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }




























}
