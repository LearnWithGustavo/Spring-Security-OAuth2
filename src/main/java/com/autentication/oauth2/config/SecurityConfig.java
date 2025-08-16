package com.autentication.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //Define as rotas e como deve ser tratadas a autenticação a elas
        return http.authorizeHttpRequests(authorizeConfig -> {
            authorizeConfig.requestMatchers("/public").permitAll();
            authorizeConfig.requestMatchers("/logout").permitAll();
            authorizeConfig.anyRequest().authenticated();
        })
//        .oauth2Login(Customizer.withDefaults()).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))  //Ative se quiser autenticar usando JWT que foi gerado pelo Google tambem, atraves de um requisição
        .build();
    };
}
