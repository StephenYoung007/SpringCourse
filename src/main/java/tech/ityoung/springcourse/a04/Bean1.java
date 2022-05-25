package tech.ityoung.springcourse.a04;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

@Component
@Slf4j
public class Bean1 {
    private Bean3 bean3;
    private Bean2 bean2;

    private String home;

    /**
     * run first
     */
    public Bean1() {
        log.info("construction....................................");
    }

    @Resource
    public void setBean3(Bean3 bean3) {
        this.bean3 = bean3;
        log.info("@Resource生效.........................................");
    }

    /**
     * run for the second
     *
     * @param javaHome
     * @Value可以进行值注入，除此之外可以根据类型自动注入
     */
    @Autowired
    public void autowire(@Value("${JAVA_HOME}") String javaHome) {
        log.info("@Value生效 : {}.............................", javaHome);
        this.home = javaHome;
    }

    @Autowired
    public void setBean2(Bean2 bean2) {
        log.info("@Autowired:{}.............................", bean2);
        this.bean2 = bean2;
    }

    /**
     * run for third
     */
    @PostConstruct
    public void init() {
        log.info("@PostConstruct生效........................................");
    }

    // 单例多礼不同时机
    // run for the last
    @PreDestroy
    public void destroy() {
        log.info("@PreDestroy生效........................................");
    }

    @Override
    public String toString() {
        return "Bean1{" +
                "bean3=" + bean3 +
                ", bean2=" + bean2 +
                ", home='" + home + '\'' +
                '}';
    }
}
