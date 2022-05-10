package tech.ityoung.springcourse;

import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

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
