package org.example.securitystudy.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 특정 경로에 요청을 허용하거나 거부하거나
                .authorizeRequests((auth) -> auth
                        // 특정 경로 설정
                        // 경로 설정 후(requestMatchers)에 permitAll()는 모든 사용자에게 로그인하지 않아도 접근 가능
                        // hasRole은 특정 role이 있어야 접근 가능하도록
                        // hasAnyRole은 특정 role을 다수 설정 가능함.
                        // authenticated는 로그인만 진행하면 모두 접근 가능
                        // denyAll은 로그인을 진행해도 접근하지 못하도록 만든다.
                        .requestMatchers("/", "/login", "/join", "joinProc").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/my/**").hasAnyRole("ADMIN", "USER")
                        // 위에서 처리하지 못한 나머지 경로 처리하려면 anyRequest()를 쓰자
                        .anyRequest().authenticated()
                );
                // 여기서 인가 동작 순서는 위에서부터 아래로 차래대로 진행되기에 순서를 잘 지켜야한다.
                // 예를 들어 만약에 .anyRequest().authenticated()가 가장 위에 있으면 후에 설정하는 requestMatchers들은
                // 효력을 잃는다.


        http
                .formLogin((auth) -> auth.loginPage("/login")
                        .loginProcessingUrl("/loginProc")
                        .permitAll()
                );

        http
                .csrf((auth) -> auth.disable());

        return http.build();
        // 마지막에 http.build()로 http 객체를 리턴해준다.
    }
}
