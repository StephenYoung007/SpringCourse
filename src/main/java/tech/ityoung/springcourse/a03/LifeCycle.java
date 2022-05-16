package tech.ityoung.springcourse.a03;

import lombok.Value;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class LifeCycle {

    public LifeCycle() {
        log.info("construction....................................");
    }

//    @Autowired
//    public void autowire(@Value("${JAVA_HOME}") String javaHome) {
//        log.debug("dependency injection : {}", javaHome);
//    }

    @PostConstruct
    public void init() {
        log.info("init........................................");
    }

    // 单例多礼不同时机
    @PreDestroy
    public void destroy() {
        log.info("destroy........................................");
    }
}
