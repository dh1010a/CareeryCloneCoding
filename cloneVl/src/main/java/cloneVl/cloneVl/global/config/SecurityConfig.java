package cloneVl.cloneVl.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig{

    @Bean
    protected void configure(HttpSecurity http) throws Exception{
        http
                .formLogin().disable() // formLogin 인증 비활성화
                .httpBasic().disable() // httpBasic 인증 비활성화
                .csrf().disable();
    }
}
