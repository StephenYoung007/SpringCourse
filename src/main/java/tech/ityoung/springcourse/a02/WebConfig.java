package tech.ityoung.springcourse.a02;

import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletRegistrationBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.mvc.Controller;

@Configuration
public class WebConfig {
    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

    @Bean
    public DispatcherServletRegistrationBean registrationBean(DispatcherServlet dispatcherServlet, ServletWebServerFactory servletWebServerFactory) {
        return new DispatcherServletRegistrationBean(dispatcherServlet, "/test");
    }

    @Bean("/hello")
    public Controller controller1() {
        return (request, response) -> {
            response.getWriter().write("hello world");
            return null;
        };
    }
}
