package tech.ityoung.springcourse.a03;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class LifeCycle {

    /**
     * run first
     */
    public LifeCycle() {
        log.info("construction....................................");
    }

    /**
     * run for the second
     * @param javaHome
     */
    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String javaHome) {
        log.info("dependency injection : {}.............................", javaHome);
    }

    /**
     * run for third
     */
    @PostConstruct
    public void init() {
        log.info("init........................................");
    }

    // 单例多礼不同时机
    // run for the last
    @PreDestroy
    public void destroy() {
        log.info("destroy........................................");
    }
}
