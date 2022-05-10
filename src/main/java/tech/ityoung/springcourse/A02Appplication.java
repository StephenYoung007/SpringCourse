package tech.ityoung.springcourse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class A02Appplication {
    public static void main(String[] args) {
        testClassPathXmlApplicationContext();
    }

    private static void testClassPathXmlApplicationContext() {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("classPathXmlBeanDefinition.xml");
        Arrays.stream(xmlApplicationContext.getBeanDefinitionNames()).forEach(String::toString);
        System.out.println(xmlApplicationContext.getBean(XmlBean02.class).getXmlBean01());
    }
}
