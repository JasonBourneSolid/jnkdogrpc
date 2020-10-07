package com.jnkdog.rpc.protocol;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jnkdog.rpc.remoting.Serializer;

/**
 * 基于jackson的序列化器
 *
 * @author LJX
 */
public class JacksonJsonSerializer implements Serializer {

    @Override
    public byte[] serialize(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return  mapper.writeValueAsBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] data) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(data,clazz);
    }
}
