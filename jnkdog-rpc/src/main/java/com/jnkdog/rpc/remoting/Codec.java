package com.jnkdog.rpc.remoting;

import java.util.List;

/**
 * @author LJX
 */
public interface Codec {
    /***
     * 将字节数组解码为java对象
     *
     * @param bytes
     * @return
     */
    List<Object> decode(byte[] bytes);

    /***
     * 编码方法
     * @param bytes
     * @return
     */
    byte[] encode(Object bytes);

}
