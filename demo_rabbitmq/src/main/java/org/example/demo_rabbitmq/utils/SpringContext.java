package org.example.demo_rabbitmq.utils;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * *@ClassName SpringContext
 * *@Description TODO
 * *@Author 211295
 * *@Date 2025/9/18 15:06
 * *Version 1.0
 **/
@Component
public class SpringContext implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    // 动态注册 Bean
    public static void registerBean(String beanName, Object bean) {
        if (context instanceof BeanDefinitionRegistry registry) {
            GenericBeanDefinition definition = new GenericBeanDefinition();
            definition.setBeanClass(bean.getClass());
            definition.setInstanceSupplier(() -> bean);
            registry.registerBeanDefinition(beanName, definition);
        } else if (context instanceof org.springframework.context.ConfigurableApplicationContext configurableContext) {
            configurableContext.getBeanFactory().registerSingleton(beanName, bean);
        }
    }
}
