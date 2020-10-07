package com.jnkdog.rpc.remoting.Netty;

import com.jnkdog.rpc.remoting.Codec;
import com.jnkdog.rpc.remoting.Handler;
import com.jnkdog.rpc.remoting.Server;
import com.jnkdog.rpc.remoting.Transporter;

import java.net.URI;

/**
 * @author LJX
 */
public class NettyTransporter implements Transporter {

    @Override
    public Server start(URI uri, Codec codec, Handler handler) {
        NettyServer server = new NettyServer();
        server.start(uri, codec, handler);
        return server;
    }
}
