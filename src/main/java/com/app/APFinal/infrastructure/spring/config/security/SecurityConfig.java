package com.app.APFinal.infrastructure.spring.config.security;

import com.app.APFinal.infrastructure.spring.config.security.filer.CustomAccessDeniedHandler;
import com.app.APFinal.infrastructure.spring.config.security.filer.CustomAuthenticationEntryPoint;
import com.app.APFinal.infrastructure.spring.config.security.filer.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private JwtRequestFilter jwtRequestFilter;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }

  @Bean
  public AuthenticationEntryPoint authenticationEntryPoint() {
    return new CustomAuthenticationEntryPoint();
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .cors()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()
        .antMatchers(HttpMethod.GET, "/portfolio")
        .permitAll()
        .antMatchers(HttpMethod.PUT, "/portfolio/update")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/auth/me")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/auth/login")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/auth/register")
        .permitAll()
        .antMatchers(HttpMethod.POST, "/education")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/education/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/education/{id:^\\d+$}")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/education")
        .permitAll()
        .antMatchers(HttpMethod.PUT, "/education/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/experience")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/experience/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/experience/{id:^\\d+$}")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/experience")
        .permitAll()
        .antMatchers(HttpMethod.PUT, "/experience/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/hardSkills")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/hardSkills/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/hardSkills/{id:^\\d+$}")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/hardSkills")
        .permitAll()
        .antMatchers(HttpMethod.PUT, "/hardSkills/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/softSkills")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/softSkills/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/softSkills/{id:^\\d+$}")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/softSkills")
        .permitAll()
        .antMatchers(HttpMethod.PUT, "/softSkills/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/projects")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/projects/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/projects/{id:^\\d+$}")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/projects")
        .permitAll()
        .antMatchers(HttpMethod.PUT, "/projects/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.POST, "/socials")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.DELETE, "/socials/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .antMatchers(HttpMethod.GET, "/socials/{id:^\\d+$}")
        .permitAll()
        .antMatchers(HttpMethod.GET, "/socials")
        .permitAll()
        .antMatchers(HttpMethod.PUT, "/socials/{id:^\\d+$}")
        .hasAnyRole(ERole.USER.name(), ERole.ADMIN.name())
        .anyRequest()
        .authenticated()
        .and()
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .accessDeniedHandler(accessDeniedHandler())
        .authenticationEntryPoint(authenticationEntryPoint());
  }

}
