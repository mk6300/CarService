package project.carservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.carservice.interseptors.SubscribeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SubscribeInterceptor subscribeInterceptor;

    public WebConfig(SubscribeInterceptor subscribeInterceptor) {
        this.subscribeInterceptor = subscribeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(subscribeInterceptor)
                .addPathPatterns("/**");
    }
}
