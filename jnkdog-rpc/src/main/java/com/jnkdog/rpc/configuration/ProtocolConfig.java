package com.jnkdog.rpc.configuration;

/**
 * 协议信息配置类
 * @author LJX
 */
public class ProtocolConfig {
    private String name;
    private String port;
    private String transporter;
    private String serialization;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTransporter() {
        return transporter;
    }

    public void setTransporter(String transporter) {
        this.transporter = transporter;
    }

    public String getSerialization() {
        return serialization;
    }

    public void setSerialization(String serialization) {
        this.serialization = serialization;
    }

    @Override
    public String toString() {
        return "ProtocolConfig{" +
                "name='" + name + '\'' +
                ", port=" + port +
                ", transporter='" + transporter + '\'' +
                ", serialization='" + serialization + '\'' +
                '}';
    }
}
