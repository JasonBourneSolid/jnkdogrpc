package com.jnkdog.sms.service;

import com.jnkdog.rpc.annotation.EnableJnkdogRpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

/**
 * @author LJX
 */
@SpringBootApplication
@EnableJnkdogRpc
public class SmsServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmsServiceApplication.class, args);
    }
}
