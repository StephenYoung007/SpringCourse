package tech.ityoung.springcourse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Optional;

@Configuration
public class Config {
    @Override
    public String toString() {
        return "Config{}";
    }

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }

    @Bean
    public Bean3 bean3() {
        return new Bean3();
    }

    @Bean
    public Bean4 bean4() {
        return new Bean4();
    }


}
