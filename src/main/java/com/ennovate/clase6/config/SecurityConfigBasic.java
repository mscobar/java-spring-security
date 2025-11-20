package com.ennovate.clase6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile("basic")
public class SecurityConfigBasic {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request)
                // habilita el swaggerfile y lo deja publico
                ->request.requestMatchers("/","/public/**").permitAll()
                .anyRequest().authenticated()
        )
                // crea login publico
                .formLogin((form)
                        -> form.loginProcessingUrl("/login").permitAll())
                // crea logout publico
                .logout(logout ->
                        logout.logoutUrl("/logout").permitAll()
                );


        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user = User
                .withDefaultPasswordEncoder()
                .username("user")
                .password("pass")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
