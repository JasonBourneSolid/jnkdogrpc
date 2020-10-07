package com.jnkdog.rpc.utils;

import com.jnkdog.rpc.remoting.Transporter;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 * @author LJX
 */
public class SpiUtil {

    public static  <T> T  getServiceImpl(Class<T> interfaceClazz,String implName){
        ServiceLoader<T> serviceLoader = ServiceLoader.load(interfaceClazz);
        Iterator<T> iterator = serviceLoader.iterator();
        while (iterator.hasNext()){
            T implObj = iterator.next();
            if (implObj.getClass().getSimpleName().equals(implName)){
                return implObj;
            }
        }
        return null;
    }
}
