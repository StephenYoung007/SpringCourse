package tech.ityoung.springcourse.a01;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Component1 {

    @EventListener
    public void eventTest(UserRegisterEvent event) {
        System.out.println("event : " + event);
    }
}
