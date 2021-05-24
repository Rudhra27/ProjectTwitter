package br.com.Joetwitter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.Joetwitter.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserService userService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/notification", "/feed")
        .hasRole("NORMAL")
        .antMatchers(HttpMethod.POST, "/follow", "/unfollow", "/tweet")
        .hasRole("NORMAL").antMatchers("/**").permitAll().anyRequest()
        .authenticated().and().formLogin().loginPage("/login").permitAll().and()
        .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {

    auth.userDetailsService(userService);

  }

}