package org.example.demo.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.demo.util.JwtUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * Spring Security 提供的一个内置用户对象，实现了 UserDetails 接口。
 * Spring Security 的核心是 身份认证和权限管理，而 UserDetails 就是用来描述“用户身份信息”的接口。
 * org.springframework.security.core.userdetails.User 是 UserDetails 的一个实现类，用来快速封装用户
 *
 * **/
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collections;

/**
 * *@ClassName SecurityConfig
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/28 19:42
 * *Version 1.0
 **/

/**
 * 引入了 Spring Security（比如你做 JWT 登录验证时），那么它默认会把 所有请求 都保护起来，
 * 包括 /v3/api-docs 和 /swagger-ui/**。
 * 所以你在访问 /v3/api-docs 时会被拦截，要求先认证
 * **/
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new JwtFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /**
     * 封装用户名和密码
     * 用于身份认证：
     * new User("admin", "{noop}123456", Collections.emptyList());
     * 封装权限信息
     * 可以把角色或权限放在 authorities 里，方便 Spring Security 进行 访问控制（@PreAuthorize, URL 权限拦截等）。
     * 配合 UserDetailsService 使用
     * Spring Security 登录验证时会调用你实现的 UserDetailsService：
     * @Bean
     * public UserDetailsService userDetailsService() {
     *     return username -> new User(username, "{noop}123456", Collections.emptyList());
     * }
     * 然后 Spring Security 会自动检查用户名、密码，决定是否允许访问。
     * **/
    static class JwtFilter extends org.springframework.web.filter.OncePerRequestFilter {
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                throws ServletException, IOException {
            String header = request.getHeader("Authorization");
            System.out.println("Authorization: " + header);
            if (header != null && header.startsWith("Bearer ")) {
                String token = header.substring(7);
                String username = JwtUtil.validateToken(token);
                if (username != null) {
                    var auth = new UsernamePasswordAuthenticationToken(
                            new User(username, "", Collections.emptyList()),
                            null,
                            Collections.emptyList()
                    );
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
            filterChain.doFilter(request, response);
        }
    }
}
