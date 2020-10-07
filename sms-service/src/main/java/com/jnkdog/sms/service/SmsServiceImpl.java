package com.jnkdog.sms.service;

import com.jnkdog.rpc.annotation.JnkdogService;
import com.jnkdog.service.SmsService;

/**
 * @author LJX
 */
@JnkdogService
public class SmsServiceImpl implements SmsService {

    @Override
    public boolean send(String msg) {
        System.out.println("客户端发起参数为{"+msg+"}的远程调用!");
        return false;
    }
}
