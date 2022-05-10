package tech.ityoung.springcourse;

public class Bean3 implements Inter{
    @Override
    public String toString() {
        return "Bean3{}";
    }

    public Bean3() {
        System.out.println("bean3 construction");
    }
}
