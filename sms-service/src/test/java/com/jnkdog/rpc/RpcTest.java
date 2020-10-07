package com.jnkdog.rpc;


import com.jnkdog.rpc.protocol.JnkdogRequest;
import com.jnkdog.rpc.protocol.SerializerFactory;
import com.jnkdog.rpc.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/***
 * 协议解码测试类
 *
 */
public class RpcTest {

    public static void main(String[] args) throws Exception {
        JnkdogRequest jnkdogRequest = new JnkdogRequest();
        jnkdogRequest.setServiceName("SmsService");
        jnkdogRequest.setMethodName("send");
        jnkdogRequest.setParameterTypes(new Class[]{String.class});
        jnkdogRequest.setArguments(new Object[]{"123"});

        byte[] body =  SerializerFactory.createSerializer().serialize(jnkdogRequest);
        ByteBuf request = Unpooled.buffer();
        request.writeByte(0xda);
        request.writeByte(0xbb);
        request.writeBytes(ByteUtil.intToByteArray(body.length));
        request.writeBytes(body);
        SocketChannel client = SocketChannel.open();
        client.connect(new InetSocketAddress("127.0.0.1",10080));
        client.write(ByteBuffer.wrap(request.array()));
        ByteBuffer respond = ByteBuffer.allocate(1024);
        client.read(respond);
        System.out.println(new String(respond.array()));
    }

}
