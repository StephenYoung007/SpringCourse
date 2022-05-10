package tech.ityoung.springcourse;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;

public class TestBeanFactory {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Config.class).getBeanDefinition();

        beanFactory.registerBeanDefinition("config", beanDefinition);

        System.out.println(beanFactory.getBean("config"));

        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).forEach((name, processor) -> {
            processor.postProcessBeanFactory(beanFactory);
        });

        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            System.out.println(beanDefinitionName);
        }

//        System.out.println(beanFactory.getBean(Bean1.class).getBean3());

        beanFactory.getBeansOfType(BeanPostProcessor.class).forEach((name, processor) -> {
            beanFactory.addBeanPostProcessor(processor);
        });

        beanFactory.preInstantiateSingletons();

        System.out.println("=====================================================");

        System.out.println(beanFactory.getBean(Bean1.class));

    }
}
