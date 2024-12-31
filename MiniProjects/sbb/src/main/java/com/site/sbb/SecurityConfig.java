package com.site.sbb;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// 스프링의 환경 설정 파일임을 의미
@Configuration
@EnableWebSecurity // 모든 요청 URL 이 스프링 시큐리티의 제 어를 받도록 만듬. (스프링 시큐리티 활성화 역할)
/**
 * 내부적으로 SecurityFilterChain 클래스가 동작하여 모든 요청 URL 에 이 클래스가 필터로 적용되어
 * URL 별로 설정 할 수 있음.
 * 스프링 시큐리티의 세부 설정은 @Bean을 통해 SecurityFilterChain 빈을 생성하여 설정
 */
public class SecurityConfig {

    /**
     * 스프링에의해생성또는관리되는객체를의미한다.
     * 컨트롤러, 서비스, 리포지터리 등도 모두 빈에 해당한다.
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // 인증되지 않은 모든 페이지 요청 허락한다는 의미 (로그인 필요x)
        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests.requestMatchers(new AntPathRequestMatcher("/**"))
                        .permitAll())
                .csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
                .headers((headers) -> headers.addHeaderWriter(new XFrameOptionsHeaderWriter(
                        XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN
                ))) // formLogin : 스프링 시큐리티의 로그인 설정을 담당하는 부분
                .formLogin((formLogin) -> formLogin
                        .loginPage("/user/login")
                        .defaultSuccessUrl("/"))
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true));
                        // .invalidateHttpSession(true)를 통해 로그아웃 시 생성된 사용자 세션도 삭제하도록 처리
        return  http.build();
        // 스프링 시큐리티의 CSRF 방어 기능에 의해 H2 콘솔 접근이 거부됨
        // /h2-console/로 시작하는 모든 URL 은 CSRF 검증을 하지 않는다는 설정
        // URL 요청 시 X-Frame-Options 헤더를 DENY 대신 SAMEORIGIN 으로 설정하여 오류가 발 생하지 않도록 처리
        // 236p

    }

    /**
     * PasswordEncoder 빈을 만드는 가장 쉬운 방법은
     * @Configuration이 적용된 SecurityConfig .java 파일에 @Bean 메서드를 새로 추가하는 것
     * PasswordEncoder 를 @Bean으로 등록하면 UserService.java도 수정 가능
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * AuthenticationManager 빈 생성 (스프링 시큐리티의 인증을 처리)
     * 사용자 인증 시 앞에서 작성한 UserSecurityService와
     * PasswordEncoder를 내부적으로 사용하여 인증과 권한 부여 프로세스를 처리
     */
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
