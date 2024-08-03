package project.carservice.interseptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import project.carservice.model.dto.SubscriberDTO;

@Component
public class SubscribeInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {

        if (modelAndView != null) {
            modelAndView.addObject("subscriberDTO", new SubscriberDTO());
        }
    }
}