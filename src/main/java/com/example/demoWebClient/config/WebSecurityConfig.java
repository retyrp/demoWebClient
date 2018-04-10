package com.example.demoWebClient.config;

import com.example.demoWebClient.config.service.*;
import com.example.demoWebClient.filter.account.CustomAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AnyUserDetailsService anyUserDetailsService;

    /**
     * Url权限拦截设置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling()
                .accessDeniedHandler(new GoAccessDeniedHandler())
                .authenticationEntryPoint(new GoAuthenticationEntryPoint())
                .and().authorizeRequests()
                .antMatchers("/", "/csrf","/login/account/login").permitAll()
                .antMatchers("/hello").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and().formLogin().usernameParameter("username").passwordParameter("password")
                .loginProcessingUrl("/login/account/login").permitAll()
                .successHandler(new GoAuthenticationSuccessHandler())
                .failureHandler(new GoAuthenticationFailureHandler())
                .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and().logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(new GoLogoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and().requiresChannel()
                .antMatchers("/pomer").requiresSecure()
                .anyRequest().requiresInsecure()
                .and().rememberMe()
                .tokenValiditySeconds(1800)
                .key("token_key")
                //.and().csrf().disable()
                .and().addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement().maximumSessions(1).expiredUrl("/login?expired")
        ;

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(anyUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 密码加密
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    CustomAuthenticationFilter customAuthenticationFilter() throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter();
        filter.setAuthenticationSuccessHandler(new GoAuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new GoAuthenticationFailureHandler());
        filter.setFilterProcessesUrl("/login/**");

        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
