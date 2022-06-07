package tech.ityoung.springcourse.a05.postProcessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.stereotype.Component;
import tech.ityoung.springcourse.a05.Config;

import java.io.IOException;

@Slf4j
public class ComponentScanPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        ComponentScan componentScan = AnnotationUtils.findAnnotation(Config.class, ComponentScan.class);
        // tech.ityoung.springcourse.a05.component -> classpath*:tech/ityoung/springcourse/a05/component/**
        try {
            if (componentScan != null) {
                CachingMetadataReaderFactory readerFactory = new CachingMetadataReaderFactory();
                for (String basePackage : componentScan.basePackages()) {
                    log.info(basePackage);
                    // tech.ityoung.springcourse.a05.component -> classpath*:tech/ityoung/springcourse/a05/component/**
                    String newPath = "classpath*:" + basePackage.replace(".", "/") + "/**";
                    log.info(newPath);
                    Resource[] resources = new PathMatchingResourcePatternResolver().getResources(newPath);
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
                            if (configurableListableBeanFactory instanceof DefaultListableBeanFactory beanFactory) {
                                beanFactory.registerBeanDefinition(beanNameGenerator.generateBeanName(beanDefinition, beanFactory), beanDefinition);
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
