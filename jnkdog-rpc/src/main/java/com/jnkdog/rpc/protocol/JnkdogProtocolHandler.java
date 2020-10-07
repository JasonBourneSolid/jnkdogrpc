package com.jnkdog.rpc.protocol;

import com.jnkdog.rpc.remoting.Handler;
import com.jnkdog.rpc.remoting.Invoker;
import com.jnkdog.rpc.remoting.JnkdogRpcChannel;
import com.jnkdog.rpc.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 *
 * @author LJX
 */
public class JnkdogProtocolHandler implements Handler {
    private Invoker invoker;

    public JnkdogProtocolHandler(Invoker invoker) {
        this.invoker = invoker;
    }

    @Override
    public void onRead(JnkdogRpcChannel channel, Object message) {
        JnkdogRequest request  = (JnkdogRequest)message;
        System.out.println("执行方法调用:");
        System.out.println(message);
        Object result = null;
        try {
            result = invoker.invoke(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        JnkdogResponse response = new JnkdogResponse();
        if (result!=null){
            response.setRequestId(request.getId());
            response.setStatus(200);
            response.setContent("rpc respond content");
        }else{
            response.setRequestId(request.getId());
            response.setStatus(-1);
            response.setContent("方法调用异常");
        }
        byte[] body = null;
        try {
            //TODO 序列化器应该是介意选择的
            body = SerializerFactory.createSerializer().serialize(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        channel.getChannel().write(body);

    }

    @Override
    public void onWrite(JnkdogRpcChannel channel, Object message) { }
}
