package tech.ityoung.springcourse.a03;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class A03Appplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(A03Appplication.class, args);
        context.close();
    }
}
