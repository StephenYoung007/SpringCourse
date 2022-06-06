package tech.ityoung.springcourse.a01;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;

import java.util.List;

@Slf4j
public class TestBeanFactory01 {
    public static void main(String[] args) {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // bean definition: class, scope, initiation, destroy
        AbstractBeanDefinition beanDefinition =
                BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();
        beanFactory.registerBeanDefinition("config", beanDefinition);

        log.info("==============before add annotation post processor========================");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            log.info("bean definition: {}", beanDefinitionName);
        }

        // add post processors for beanFactory
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);

        log.info("==============after add annotation post processor==============");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            log.info("bean definition: {}", beanDefinitionName);
        }

        // make bean post processor work
        // 补充一些bean定义, bean工厂后处理器
        List<BeanPostProcessor> postProcessors = beanFactory.getBeanPostProcessors();
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values()
                .forEach(beanFactoryPostProcessor -> beanFactoryPostProcessor.postProcessBeanFactory(beanFactory));
        log.info("==============after post processor works==============");
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            log.info("bean definition: {}", beanDefinitionName);
        }
        log.info("==============================================================");

        // 尚且无法解析autowire，需要bean后处理器
//        System.out.println(beanFactory.getBean(Bean1.class).getBean3());

        log.info("==========================after add bean post processor====================================");
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream().sorted((ele1, ele2) -> {
                    if (ele1 instanceof CommonAnnotationBeanPostProcessor) {
                        return -1;
                    } else if (ele2 instanceof CommonAnnotationBeanPostProcessor) {
                        return 1;
                    } else {
                        return 0;
                    }
                })
                .forEach(ele -> {
                    System.out.println(ele);
                    if (ele instanceof AutowiredAnnotationBeanPostProcessor) {
                        ((AutowiredAnnotationBeanPostProcessor) ele).setOrder(0);
                    }
                    if (ele instanceof CommonAnnotationBeanPostProcessor) {
                        ((CommonAnnotationBeanPostProcessor) ele).setOrder(1);
                    }
                    beanFactory.addBeanPostProcessor(ele);
                });

        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            log.info("bean definition: {}", beanDefinitionName);
        }
        log.info("==========================after add bean post processor====================================");

        // 如果上面一步已经打印了bean1，此时autowire尚未就绪，不会注入成功，打印bean3依然为null
        // 默认懒加载，使用时才开始调用构造方法
//        System.out.println(beanFactory.getBean(Bean1.class).getBean3());

        beanFactory.preInstantiateSingletons();
        System.out.println(beanFactory.getBean(Bean1.class).getInter());

        /*
        a.beanFactory不会做的事
            1,不会主动调用BeanFactory后处理器
            2.不会主动，添加Bean后处理器
            3,不会主动初始化单例
            4.不会解beanFactory还不会解析${}与#{}
        b.bean后处理器会有排序的逻辑
        * */

    }
}
