package com.ytudak.malzeme.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/favicon/**","/login").permitAll()
                // giriş yapan herkes
                .antMatchers("/kayit","/malzemeler","/anasayfa").hasAnyRole("ADMIN","USER","BASKAN")
                // sadece malzemeciler
                .antMatchers("/zimmetVer","/zimmetVer/*","/zimmetVer/*/*").hasRole("ADMIN")
                .antMatchers("/zimmetAl","/zimmetAl/*","/zimmetAl/*/*").hasRole("ADMIN")
                .antMatchers("/teslim","/teslim/*","/teslim/*/*").hasRole("ADMIN")
                .antMatchers("/malzemeEkle","/malzemesil","/malzemeDuzenle","/malzeme","/malzeme/*","/malzeme/*/*").hasRole("ADMIN")
                .antMatchers("/kategoriEkle").hasRole("ADMIN")
                //sadece baskan
                .antMatchers("/statuonay","/statuonay/onay","/statuonaysonuc").hasRole("BASKAN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/anasayfa")
                .loginPage("/login")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?success")
                .invalidateHttpSession(true)
                .permitAll();

    }

    @Autowired
    UserDetailServices userDetailServices;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailServices);
    }
}
