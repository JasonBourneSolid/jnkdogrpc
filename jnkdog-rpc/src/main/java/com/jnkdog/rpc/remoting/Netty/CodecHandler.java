package com.jnkdog.rpc.remoting.Netty;

import com.jnkdog.rpc.remoting.Codec;
import com.jnkdog.rpc.remoting.JnkdogRpcChannel;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.List;

/**
 * 负责将网络请求或者相应中的二进制数据转化为java对象
 * 此处的二进制数据存在拆包粘包的问题
 *
 * @author LJX
 */
public class CodecHandler extends ChannelDuplexHandler {
    private Codec codec;

    public CodecHandler(Codec codec){
        this.codec = codec;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //获取原始数据
        ByteBuf data = (ByteBuf) msg;
        byte[] dataByteArray = new byte[data.readableBytes()];
        data.readBytes(dataByteArray);

        //将数据转化为java对象
        List<Object> objectList = codec.decode(dataByteArray);

        for (Object obj : objectList) {
            ctx.fireChannelRead(obj);
        }
    }


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        byte[] array = codec.encode(msg);
        //TODO flush的作用?
        super.write(ctx, Unpooled.wrappedBuffer(array),promise);
        super.flush(ctx);
    }

}
