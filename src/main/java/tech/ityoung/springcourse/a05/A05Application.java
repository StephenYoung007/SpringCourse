package tech.ityoung.springcourse.a05;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import tech.ityoung.springcourse.a05.postProcessor.ComponentScanPostProcessor;

import java.io.IOException;
import java.util.Optional;

@Slf4j
public class A05Application {
    public static void main(String[] args) throws IOException {
        GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean("config", Config.class);

        // @Bean @ComponentScan @Import @ImportSource etc..
//        applicationContext.registerBean(ConfigurationClassPostProcessor.class);

        // @Mapper
//        applicationContext.registerBean(MapperScannerConfigurer.class, beanDefinition -> {
//            beanDefinition.getPropertyValues().addPropertyValue("basePackage", "tech.ityoung.springcourse.a05.mapper");
//        });

        /*ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        // tech.ityoung.springcourse.a05.component -> classpath*:tech/ityoung/springcourse/a05/component/**
        if (componentScan != null) {
            CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
            for (String basePackage : componentScan.basePackages()) {
                log.info(basePackage);
                // tech.ityoung.springcourse.a05.component -> classpath*:tech/ityoung/springcourse/a05/component/**
                String newPath = "classpath*:" + basePackage.replace(".", "/") + "/**";
                log.info(newPath);
                DefaultListableBeanFactory beanFactory = applicationContext.getDefaultListableBeanFactory();
                Resource[] resources = applicationContext.getResources(newPath);
                for (Resource resource : resources) {
                    MetadataReader metadataReader = readerFactory.getMetadataReader(resource);
                    log.info(resource.toString());
                    AnnotationMetadata annotationMetadata = metadataReader.getAnnotationMetadata();
                    String annotationName = Component.class.getName();
                    if (annotationMetadata.hasMetaAnnotation(annotationName)) {
                        log.info("加了Component派生注解" + metadataReader.getClassMetadata().getClassName());
                    }
                    if (annotationMetadata.hasAnnotation(annotationName)) {
                        log.info("加了Component注解" + metadataReader.getClassMetadata().getClassName());
                    }
                    if (annotationMetadata.hasAnnotation(annotationName) || annotationMetadata.hasMetaAnnotation(annotationName)) {
                        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(metadataReader.getAnnotationMetadata().getClassName()).getBeanDefinition();
                        AnnotationBeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();
                        beanFactory.registerBeanDefinition(beanNameGenerator.generateBeanName(beanDefinition, beanFactory), beanDefinition);
                    }
                }
            }
        }*/

        applicationContext.registerBean(ComponentScanPostProcessor.class);


        applicationContext.refresh();
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        for (String definitionName : beanDefinitionNames) {
            log.info(definitionName);
        }
    }
}
