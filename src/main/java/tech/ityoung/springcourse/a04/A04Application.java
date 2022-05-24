package tech.ityoung.springcourse.a04;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class A04Application {
    public static void main(String[] args) {
        GenericApplicationContext context = new GenericApplicationContext();
        context.registerBean("bean1", Bean1.class);
        context.registerBean("bean2", Bean2.class);
        context.registerBean("bean3", Bean3.class);
        context.registerBean("bean4", Bean4.class);

        /**
         * 解析@Value
         */
        context.getDefaultListableBeanFactory().setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());

        /**
         * 单独无法解析@Value
         */
        context.registerBean(AutowiredAnnotationBeanPostProcessor.class);

        /**
         * 解析resource、PostConstruct、PreDestroy等
         */
        context.registerBean(CommonAnnotationBeanPostProcessor.class);

        ConfigurationPropertiesBindingPostProcessor.register(context.getDefaultListableBeanFactory());

        context.refresh();
        log.info(context.getBean(Bean4.class).toString());
        context.close();
    }
}
