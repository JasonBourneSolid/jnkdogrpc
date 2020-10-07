package com.jnkdog.rpc.remoting;

/***
 * 对进行对协议对象的处理
 *
 * @author LJX
 */
public interface Handler {
    /***
     * 输入请求或响应处理
     * @param message
     */
    void onRead(JnkdogRpcChannel channel,Object message);

    /***
     * 输出响应或请求
     * @param message
     */
    void onWrite(JnkdogRpcChannel channel,Object message);
}
