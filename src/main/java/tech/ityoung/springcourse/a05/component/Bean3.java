package tech.ityoung.springcourse.a05.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class Bean3 {
    public Bean3() {
        log.info("managed by spring");
    }
}
