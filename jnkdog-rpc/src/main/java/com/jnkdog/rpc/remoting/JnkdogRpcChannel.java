package com.jnkdog.rpc.remoting;


import io.netty.channel.Channel;

/***
 *
 * @author LJX
 */
public class JnkdogRpcChannel {

    private Channel channel;

    public JnkdogRpcChannel(Channel channel){
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
