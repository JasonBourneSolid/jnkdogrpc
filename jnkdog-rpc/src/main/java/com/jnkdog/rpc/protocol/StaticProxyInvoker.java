package com.jnkdog.rpc.protocol;

import com.jnkdog.rpc.remoting.Invoker;

import java.lang.reflect.Method;

public class StaticProxyInvoker implements Invoker {
    private Object bean;
    public StaticProxyInvoker(Object bean){
        this.bean = bean;
    }

    @Override
    public Object invoke(JnkdogRequest request) throws Exception {
        Method method = bean.getClass().getMethod(request.getMethodName(),request.getParameterTypes());
        return method.invoke(bean,request.getArguments());
    }
}
