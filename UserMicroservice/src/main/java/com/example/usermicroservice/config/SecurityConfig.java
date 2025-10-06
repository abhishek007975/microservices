package com.example.usermicroservice.config;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
 
@Configuration
public class SecurityConfig {
 
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/user/register", "/user/login","/user/**", "/eureka/**").permitAll() // public endpoints
                .anyRequest().authenticated() // everything else secured
            )
            .httpBasic(httpBasic -> httpBasic.disable()); // disable basic auth
        return http.build();
    }

//    @Bean
//      public InMemoryUserDetailsManager userDetailsService() {
//          UserDetails user = User.withDefaultPasswordEncoder()
//              .username("admin")
//              .password("admin123")
//              .roles("ADMIN")
//              .build();
//          return new InMemoryUserDetailsManager(user);
//      }
  }



 