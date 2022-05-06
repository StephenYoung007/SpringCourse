package tech.ityoung.springcourse;

import org.springframework.beans.factory.annotation.Autowired;

public class Bean1 {
    @Autowired
    private Bean3 bean3;

    public Bean1() {
        System.out.println("bean1 construction");;
    }

    @Override
    public String toString() {
        return "Bean1{}";
    }

    public Bean3 getBean3() {
        return bean3;
    }
}
