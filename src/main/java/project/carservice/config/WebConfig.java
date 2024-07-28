package project.carservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.carservice.interseptors.SubscribeInterceptor;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final SubscribeInterceptor subscribeInterceptor;
    private final LocaleChangeInterceptor locateChangeInterceptor;

    public WebConfig(SubscribeInterceptor subscribeInterceptor, LocaleChangeInterceptor locateChangeInterceptor) {
        this.subscribeInterceptor = subscribeInterceptor;
        this.locateChangeInterceptor = locateChangeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(subscribeInterceptor)
                .addPathPatterns("/**");
        registry.addInterceptor(locateChangeInterceptor);
    }
}
