package com.jnkdog.rpc.remoting;

import com.jnkdog.rpc.protocol.JnkdogRequest;

/***
 * 获取协议对象后,实际进行方法调用的类
 *
 * @author LJX
 */
public interface Invoker {

    Object invoke(JnkdogRequest request) throws NoSuchMethodException, Exception;
}
