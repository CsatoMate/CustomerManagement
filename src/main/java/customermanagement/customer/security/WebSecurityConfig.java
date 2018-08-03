package customermanagement.customer.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/show").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/customer").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/add").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/update/{pId}").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/deletebyid").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/delete").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/controller").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth
                .inMemoryAuthentication()
                    .withUser("Mate")
                    .password("eteo")
                    .roles("USER")
                    .and()
                    .withUser("eteo").password("eteo").roles("ADMIN");
    }

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder(){
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
