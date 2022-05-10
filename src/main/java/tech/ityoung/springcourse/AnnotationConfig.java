package tech.ityoung.springcourse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnnotationConfig {
    @Bean
    public AnnotationConfigBean1 annotationConfigBean1() {
        return new AnnotationConfigBean1();
    }

    @Bean
    public AnnotationConfigBean2 annotationConfigBean2(AnnotationConfigBean1 annotationConfigBean1) {
        AnnotationConfigBean2 annotationConfigBean2 = new AnnotationConfigBean2();
        annotationConfigBean2.setBean1(annotationConfigBean1);
        return annotationConfigBean2;
    }
}
