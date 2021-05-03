package com.example.securitydemo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired



    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .csrf()
               .disable()
               .authorizeRequests()
               .antMatchers("/","index","/css/*", "/js/*")
               .permitAll()
               .antMatchers("/api/**")
               .hasRole(ApplicationUserRoles.STUDENT.name())
//               .antMatchers(HttpMethod.DELETE,"/management/api/**")
//               .hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//               .antMatchers(HttpMethod.POST,"/management/api/**")
//               .hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//               .antMatchers(HttpMethod.PUT,"/management/api/**")
//               .hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//               .antMatchers(HttpMethod.GET,"/management/api/**")
//               .hasAnyRole(ApplicationUserRoles.ADMIN.name(),ApplicationUserRoles.ADMINTRAINEE.name())
               .anyRequest()
               .authenticated()
               .and()
               .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails james = User.builder()
                .username("james")
                .password(passwordEncoder.encode("james123"))
//                .roles(ApplicationUserRoles.STUDENT.name())
                .authorities(ApplicationUserRoles.STUDENT.getGrantedAuthority())
                .build();

        UserDetails mike = User.builder()
                .username("mike")
                .password(passwordEncoder.encode("mike123"))
//                .roles(ApplicationUserRoles.ADMIN.name())
                .authorities(ApplicationUserRoles.ADMIN.getGrantedAuthority())
                .build();
        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("tom123"))
//                .roles(ApplicationUserRoles.ADMINTRAINEE.name())
                .authorities(ApplicationUserRoles.ADMINTRAINEE.getGrantedAuthority())
                .build();
        return  new InMemoryUserDetailsManager(
          james,
                mike,
                tom
        );

    }
}
