package tech.ityoung.springcourse.a05;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;

@Slf4j
public class A05Application {
    public static void main(String[] args) {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean("config", Config.class);

        // @Bean @ComponentScan @Import @ImportSource etc..
        applicationContext.registerBean(ConfigurationClassPostProcessor.class);

        // @Mapper
        applicationContext.registerBean(MapperScannerConfigurer.class, beanDefinition -> {
            beanDefinition.getPropertyValues().addPropertyValue("basePackage", "tech.ityoung.springcourse.a05.mapper");
        });

        applicationContext.refresh();
        for (String definitionName : applicationContext.getBeanDefinitionNames()) {
            log.info(definitionName);
        }
    }
}
