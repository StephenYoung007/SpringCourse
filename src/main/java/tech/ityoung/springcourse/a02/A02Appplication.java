package tech.ityoung.springcourse.a02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import java.util.Arrays;

@Slf4j
public class A02Appplication {
    public static void main(String[] args) {
        testClassPathXmlApplicationContext();
//        stepsOfClassPathXmlApplicationContext();
//        testAnnotationConfigApplicationContext();
        testAnnotationConfigServletWebServerApplicationContext();
    }

    private static void testAnnotationConfigApplicationContext() {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AnnotationConfig.class);
        Arrays.stream(annotationConfigApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(annotationConfigApplicationContext.getBean(XmlBean02.class).getXmlBean01());
    }

    private static void stepsOfClassPathXmlApplicationContext() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        log.info("before add beans>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        log.info("after add beans>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
//        beanDefinitionReader.loadBeanDefinitions(new ClassPathResource("classPathXmlBeanDefinition.xml"));
//        FileSystemXmlApplicationContext -- absolute path
//        beanDefinitionReader.loadBeanDefinitions(new FileSystemResource("E:\\Java\\SpringCourse\\src\\main\\resources\\classPathXmlBeanDefinition.xml"));
//        FileSystemXmlApplicationContext -- relative path of working directory
        beanDefinitionReader.loadBeanDefinitions(new FileSystemResource("src\\main\\resources\\classPathXmlBeanDefinition.xml"));
        Arrays.stream(beanFactory.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(beanFactory.getBean(XmlBean02.class).getXmlBean01());
        new AnnotationConfigServletWebServerApplicationContext();
    }

    public static void testAnnotationConfigServletWebServerApplicationContext() {
        AnnotationConfigServletWebServerApplicationContext context =
                new AnnotationConfigServletWebServerApplicationContext(WebConfig.class);

    }

    /**
     * xml 标签<context:annotation-config/>
     * 补全五种bean后处理器
     */
    private static void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classPathXmlBeanDefinition.xml");
        Arrays.stream(xmlApplicationContext.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(xmlApplicationContext.getBean(XmlBean02.class).getXmlBean01());
    }
}
