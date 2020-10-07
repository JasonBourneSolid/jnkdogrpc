package com.jnkdog.rpc.protocol;

import java.util.Arrays;

/**
 * 协议实例数据
 * 真正的协议还会加上两个字节的协议开始标识和四个字节协议实例序列化后的数据长度。
            * 作为协议头
 *
         * @author LJX
 */
    public class JnkdogRequest {

        private long id;
   private String serviceName;
   private String methodName;
   private Class<?>[] parameterTypes;
   private Object[] arguments;

   public JnkdogRequest(){
       this.id = System.currentTimeMillis();
   }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public String toString() {
        return "JnkdogRequest{" +
                "id=" + id +
                ", serviceName='" + serviceName + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", arguments=" + arguments +
                '}';
    }
}
