package com.jnkdog.rpc.remoting;

import java.net.URI;

/**
 * 网络服务顶层接口
 * @author LJX
 */
public interface Server {
    /***
     * 网络服务器启动
     * @param uri
     * @return
     */
    boolean start(URI uri,Codec codec,Handler handler);

}
