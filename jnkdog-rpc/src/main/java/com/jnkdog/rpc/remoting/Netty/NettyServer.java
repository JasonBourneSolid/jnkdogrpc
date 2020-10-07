package com.jnkdog.rpc.remoting.Netty;

import com.jnkdog.rpc.remoting.Codec;
import com.jnkdog.rpc.remoting.Handler;
import com.jnkdog.rpc.remoting.Server;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.URI;

/**
 * 网络通讯服务器
 *
 * @author LJX
 */
public class NettyServer implements Server {

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup worker = new NioEventLoopGroup();

    @Override
    public boolean start(URI uri, Codec codec, Handler handler) {
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(boss,worker)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(uri.getHost(),uri.getPort())
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //TODO 存在线程安全问题
                            ch.pipeline().addLast(new CodecHandler(codec));
                            ch.pipeline().addLast(new ProtocolHandler(handler));
                        }
                    });
            ChannelFuture future = bootstrap.bind().sync();
            System.out.println("完成网络服务启动");
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
