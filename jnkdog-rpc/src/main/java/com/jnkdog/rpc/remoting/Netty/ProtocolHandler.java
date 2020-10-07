package com.jnkdog.rpc.remoting.Netty;

import com.jnkdog.rpc.remoting.Handler;
import com.jnkdog.rpc.remoting.JnkdogRpcChannel;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

/**
 * @author LJX
 */
public class ProtocolHandler extends ChannelDuplexHandler {
    private Handler handler;

    public ProtocolHandler(Handler handler){
        this.handler = handler;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        handler.onRead(new JnkdogRpcChannel(ctx.channel()),msg);
    }


}
