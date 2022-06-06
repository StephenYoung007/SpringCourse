package tech.ityoung.springcourse.a01;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

public class Bean1 {
    @Autowired
    @Resource(name = "bean4")
    private Inter bean3;

    public Bean1() {
        System.out.println("bean1 construction");;
    }

    @Override
    public String toString() {
        return "Bean1{}";
    }

    public Inter getInter() {
        return bean3;
    }
}
