package me.whiteship.demospringmvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/m/**") // 여기로 요청이 오면
                .addResourceLocations("classpath:/m/") // 여기서 제공을 하겠다 << 반드시 "/"로 끝나야 함
                .setCachePeriod(20);
    }
}
