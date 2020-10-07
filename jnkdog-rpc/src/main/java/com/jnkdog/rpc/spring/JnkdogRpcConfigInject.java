package com.jnkdog.rpc.spring;

import com.jnkdog.rpc.configuration.ProtocolConfig;
import com.jnkdog.rpc.configuration.RegistryConfig;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.type.AnnotationMetadata;

import java.lang.reflect.Field;

/**
 * 通过spring获取jnkdog-rpc相关的配置信息，并注入到spring容器中
 * @author LJX
 */
public class JnkdogRpcConfigInject implements ImportBeanDefinitionRegistrar {

    private final static String JNKDOG_RPC_PROTOCOL_PREFIX = "jnkdog.rpc.protocol.";
    private final static String JNKDOG_RPC_REGISTRY_PREFIX = "jnkdog.rpc.registry.";

    public  final static String JNKDOG_RPC_PROTOCOL_BEAN_NAME = "protocolConfig";
    public  final static String JNKDOG_RPC_REGISTRY_BEAN_NAME = "registryConfig";


    Environment environment;
    JnkdogRpcConfigInject(Environment environment){
        this.environment = (StandardEnvironment)environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(ProtocolConfig.class);
        for (Field field:ProtocolConfig.class.getDeclaredFields()) {
            String name = field.getName();
            String value = environment.getProperty(JNKDOG_RPC_PROTOCOL_PREFIX+name);
            beanDefinitionBuilder.addPropertyValue(name,value);
        }
        registry.registerBeanDefinition(JNKDOG_RPC_PROTOCOL_BEAN_NAME,beanDefinitionBuilder.getBeanDefinition());

//        BeanDefinitionBuilder registryConfigBeanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RegistryConfig.class);
//        for (Field field:RegistryConfig.class.getDeclaredFields()) {
//            String name = field.getName();
//            String value = environment.getProperty(JNKDOG_RPC_REGISTRY_PREFIX+name);
//            registryConfigBeanDefinitionBuilder.addPropertyValue(name,value);
//        }
//        registry.registerBeanDefinition(JNKDOG_RPC_REGISTRY_BEAN_NAME,registryConfigBeanDefinitionBuilder.getBeanDefinition());
    }
}
