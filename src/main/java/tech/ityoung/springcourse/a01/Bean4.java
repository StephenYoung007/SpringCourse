package tech.ityoung.springcourse.a01;

public class Bean4 implements Inter {
    @Override
    public String toString() {
        return "Bean4{}";
    }

    public Bean4() {
        System.out.println("Bean4 construction");
    }
}
