package com.jnkdog.rpc.remoting;


import java.net.URI;

/**
 * @author LJX
 */
public interface Transporter {
    /***
     *
     * @param uri
     * @return
     */
    Server start(URI uri,Codec codec,Handler handler);
}
