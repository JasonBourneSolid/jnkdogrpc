package com.jnkdog.rpc.annotation;

import com.jnkdog.rpc.spring.EnableJnkdogRpcPostProcessor;
import com.jnkdog.rpc.spring.JnkdogRpcConfigInject;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 该注解配合@JnkdogService注解使用
 * 只有使用了该注解才能，启用Jnkddog-Rpc功能
 * 否则@JnkdogService只是能充当一个普通@service
 *
 * @author LJX
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({EnableJnkdogRpcPostProcessor.class, JnkdogRpcConfigInject.class})
@PropertySource("classpath:jnkdogrpc.properties")
public @interface EnableJnkdogRpc {
}
