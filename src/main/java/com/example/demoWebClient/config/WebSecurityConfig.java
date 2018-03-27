package com.example.demoWebClient.config;

import com.example.demoWebClient.filter.account.AccountAuthenticationFilter;
import com.example.demoWebClient.filter.account.AccountAuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    /**
     * Url权限拦截设置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
        /** 在口令校验之前 添加自定义过滤器 */
        http.addFilterAt(AuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 自定义账户过滤器
     * @return
     */
    private AccountAuthenticationFilter AuthenticationFilter(){
        AccountAuthenticationFilter authenticationFilter = new AccountAuthenticationFilter("/login/**");
        SimpleUrlAuthenticationSuccessHandler successHandler = new SimpleUrlAuthenticationSuccessHandler();
        successHandler.setAlwaysUseDefaultTargetUrl(true);
        successHandler.setDefaultTargetUrl("/user");
        authenticationFilter.setAuthenticationManager(new AccountAuthenticationManager());
        authenticationFilter.setAuthenticationSuccessHandler(successHandler);
        return authenticationFilter;
    }
}
