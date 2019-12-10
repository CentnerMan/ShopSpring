package ru.vlsv.ShopSpring.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.vlsv.ShopSpring.services.UserService;

/**
 * GeekBrains Java, ShopSpring.
 *
 * @author Anatoly Lebedev
 * @version 1.0.0 13.11.2019
 * @link https://github.com/Centnerman
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()

//                .antMatchers("/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/products/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/customers/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/purchases/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/profile/**").authenticated()

                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
//                .httpBasic()
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
//                .logoutUrl("/logout")
                .logoutSuccessUrl("/shop")
                .permitAll();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
