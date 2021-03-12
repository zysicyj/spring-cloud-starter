package com.huan.common.util.leaf.snowflake;

public class Endpoint {
    private String ip;
    private String port;
    private long timestamp;

    public Endpoint() {
    }

    Endpoint(String ip, String port, long timestamp) {
        this.ip = ip;
        this.port = port;
        this.timestamp = timestamp;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}