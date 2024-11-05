package com.kokwai.api.users.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebConfig {

    private final Environment environment;

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
        http.csrf(csrfConfigurer -> {
                    csrfConfigurer.disable();
                    csrfConfigurer.ignoringRequestMatchers(PathRequest.toH2Console());
                }
        );

        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers(
                                PathRequest.toH2Console(),
                                AntPathRequestMatcher.antMatcher(HttpMethod.POST, "/users")
                        )
                        .permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/users/**")).access(
                                new WebExpressionAuthorizationManager("hasIpAddress('"+environment.getProperty("gateway.ip")+"')"))
                        .anyRequest().authenticated()
        );

        http.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.headers((headers) -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));

        return http.build();
    }



}
