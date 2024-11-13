package acothon.backend.config;


import ai.platform.proma.intercepter.LoginUserArgumentResolver;
import ai.platform.proma.intercepter.LoginUserIntercpter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;


@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(final CorsRegistry registry ){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("PATCH","GET","POST","PUT","DELETE","HEAD","OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

}
