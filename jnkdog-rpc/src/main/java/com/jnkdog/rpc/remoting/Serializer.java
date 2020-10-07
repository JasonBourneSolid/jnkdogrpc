package com.jnkdog.rpc.remoting;


/**
 * 序列化器接口
 * 用于实现可插拔序列化方式
 *
 * @author LJX
 */
public interface Serializer {
    /***
     * 序列化
     * @param obj
     * @return
     */
    byte[] serialize(Object obj) throws Exception;

    /***
     *
     * @param clazz
     * @param data
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz,byte[] data) throws Exception;
}
