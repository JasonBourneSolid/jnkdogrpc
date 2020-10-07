package com.jnkdog.service;

/**
 *
 * @author LJX
 */
public interface SmsService {
    /***
     * 发送短信
     *
     * @return
     */
    boolean send(String msg);
}
