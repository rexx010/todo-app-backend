package com.mytodoapp.utils;

import org.springframework.boot.web.server.servlet.CookieSameSiteSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookieConfig {
    @Bean
    public CookieSameSiteSupplier cookieSameSiteSupplier() {
        // This makes Spring Boot add SameSite=None to JSESSIONID cookies
        return CookieSameSiteSupplier.ofLax();
    }
}
