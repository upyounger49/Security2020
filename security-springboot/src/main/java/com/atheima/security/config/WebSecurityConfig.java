package com.atheima.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * security的必备配置类
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    //查询用户信息
    @Bean
    public UserDetailsService userDetailsService1(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("admin").password("123456").authorities("admin","p1").build());
        return manager;
    }
    //密码编码器(定义比对密码的方式)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //安全拦截机制主要是对用户的请求进行拦截，
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeRequests()
                //当访问r/r1的时候必须有p1的权限
                .antMatchers("/r/r1").hasAuthority("admin")
                .antMatchers("/r/r2").hasAuthority("p1")
                //所有的请求必须验证通过
                .antMatchers("/r/**").authenticated()
                //除了/r/**请求 其他都可以通过
                .anyRequest().permitAll()
                .and()
                //允许表单登录 成功后跳转susscess页面地址
                .formLogin().successForwardUrl("/success");
    }
}
