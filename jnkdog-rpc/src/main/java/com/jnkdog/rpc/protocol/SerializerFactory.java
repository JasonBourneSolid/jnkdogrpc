package com.jnkdog.rpc.protocol;

import com.jnkdog.rpc.remoting.Serializer;

/**
 *
 * @author LJX
 */
public class SerializerFactory {

    public static Serializer createSerializer(){
        return new JacksonJsonSerializer();
    }

    public static Serializer createSerializer(String serializername){
        return null;
    }

    public static Serializer createSerializer(Class clazz){
        return null;
    }

}
