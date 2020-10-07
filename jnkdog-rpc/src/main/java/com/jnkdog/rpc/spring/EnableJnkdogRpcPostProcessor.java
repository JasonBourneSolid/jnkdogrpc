package com.jnkdog.rpc.spring;


import com.jnkdog.rpc.annotation.JnkdogService;
import com.jnkdog.rpc.configuration.ProtocolConfig;
import com.jnkdog.rpc.protocol.JnkdogProtocolCodec;
import com.jnkdog.rpc.protocol.JnkdogProtocolHandler;
import com.jnkdog.rpc.protocol.StaticProxyInvoker;
import com.jnkdog.rpc.remoting.Codec;
import com.jnkdog.rpc.remoting.Handler;
import com.jnkdog.rpc.remoting.Invoker;
import com.jnkdog.rpc.remoting.Transporter;
import com.jnkdog.rpc.utils.SpiUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author LJX
 */
public class EnableJnkdogRpcPostProcessor implements ApplicationContextAware, InstantiationAwareBeanPostProcessor {
    private ApplicationContext applicationContext ;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }



    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().getAnnotationsByType(JnkdogService.class).length>0){
            ProtocolConfig protocolConfig =  applicationContext.getBean(ProtocolConfig.class);
            Transporter transporter = SpiUtil.getServiceImpl(Transporter.class,protocolConfig.getTransporter());

            //TODO 后期提供扩展,暂时默认使用这个实现.
            Invoker invoker = new StaticProxyInvoker(bean);
            //TODO 扩展自定义的Codec和Handler
            //TODO 会有反复启动的bug
            try {
                transporter.start(new URI("XXX://127.0.0.1:10080"),new JnkdogProtocolCodec(),new JnkdogProtocolHandler(invoker));
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        }
        return bean;
    }
}
