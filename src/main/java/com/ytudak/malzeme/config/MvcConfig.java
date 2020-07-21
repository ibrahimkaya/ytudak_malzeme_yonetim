package com.ytudak.malzeme.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //login pathi login html e yönlendirir
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/anasayfa").setViewName("anasayfa");
        registry.addRedirectViewController("/","/anasayfa");
        registry.addViewController("/sonuc").setViewName("sonuc");
        registry.addViewController("/error").setViewName("error");
    }
}
