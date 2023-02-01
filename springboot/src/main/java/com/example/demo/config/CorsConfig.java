package com.example.demo.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description TODO
 * @Author zsq
 * @Date 2022/7/13 17:18
 */
@Slf4j
@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        //允许所有域名进行跨域调用
        // config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        //允许跨越发送cookie
        config.setAllowCredentials(true);
        //放行全部原始头信息
        config.addAllowedHeader("*");
        //允许所有请求方法跨域调用
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // @Bean
    // CorsFilter corsFilter() {
    //     //1.添加CORS配置信息
    //     CorsConfiguration config = new CorsConfiguration();
    //     //1) 允许的域,不要写*，否则cookie就无法使用了
    //     log.info("跨域：url = {}", url);
    //     config.addAllowedOrigin(url);
    //     //2) 是否发送Cookie信息
    //     config.setAllowCredentials(true);
    //     //3) 允许的请求方式
    //     config.addAllowedMethod("OPTIONS");
    //     config.addAllowedMethod("HEAD");
    //     config.addAllowedMethod("GET");
    //     config.addAllowedMethod("PUT");
    //     config.addAllowedMethod("POST");
    //     config.addAllowedMethod("DELETE");
    //     config.addAllowedMethod("PATCH");
    //     // 4）允许的头信息
    //     config.addAllowedHeader("*");
    //
    //     //2.添加映射路径，我们拦截一切请求
    //     UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
    //     configSource.registerCorsConfiguration("/**", config);
    //
    //     //3.返回新的CorsFilter.
    //     return new CorsFilter(configSource);
    // }

}
