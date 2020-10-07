package com.jnkdog.rpc.protocol;

import com.jnkdog.rpc.remoting.Codec;
import com.jnkdog.rpc.remoting.JnkdogRpcChannel;
import com.jnkdog.rpc.remoting.Serializer;
import com.jnkdog.rpc.utils.ByteUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.ArrayList;
import java.util.List;

/***
 * 默认rpc调用协议
 *
 * @author LJX
 */
public class JnkdogProtocolCodec implements Codec {
    public static final byte[] START_MARK = new byte[]{(byte)0xda,(byte)0xbb};
    public static final int HEADER_LEN = 6;
    private ByteBuf tempMsg = Unpooled.buffer();

    @Override
    public List<Object> decode(byte[] bytes) {
        List<Object> protocolObjs = new ArrayList<>();
        Serializer serializer = SerializerFactory.createSerializer();

        ByteBuf message = Unpooled.buffer();
        if (tempMsg.readableBytes()>0){
            message.writeBytes(tempMsg);
            message.writeBytes(bytes);
        }else {
            message.writeBytes(bytes);
        }

        for (;;){
            if (message.readableBytes()<=HEADER_LEN){
                tempMsg.clear();
                tempMsg.writeBytes(message);
                return protocolObjs;
            }

            byte[] startMark = new byte[2];
            message.readBytes(startMark);
            for (;;){
                if (startMark[0]!=START_MARK[0]||startMark[1]!=START_MARK[1]){
                    if (message.readableBytes()==0){
                        tempMsg.clear();
                        tempMsg.writeByte(startMark[1]);
                        return protocolObjs;
                    }
                    startMark[0] = startMark[1];
                    startMark[1] = message.readByte();
                }else{
                    break;
                }
            }

            byte[] lengthByte = new byte[4];
            message.readBytes(lengthByte);
            int length = ByteUtil.byteArrayToInt(lengthByte);
            if (message.readableBytes()<length){
                tempMsg.clear();
                tempMsg.writeBytes(startMark);
                tempMsg.writeBytes(lengthByte);
                tempMsg.writeBytes(message);
                return protocolObjs;
            }

            byte[] body = new byte[length];
            message.readBytes(body);
            try {
                protocolObjs.add(serializer.deserialize(JnkdogRequest.class,body));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public byte[] encode(Object bytes) {
        byte[] body = (byte[])bytes;
        ByteBuf request = Unpooled.buffer();
        request.writeByte(0xda);
        request.writeByte(0xbb);
        request.writeBytes(ByteUtil.intToByteArray(body.length));
        request.writeBytes(body);
        return request.array();
    }
}
