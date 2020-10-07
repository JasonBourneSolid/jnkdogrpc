package com.jnkdog.rpc.protocol;

/**
 * @author LJX
 */
public class JnkdogResponse {

    private long requestId;
    private int status;
    private Object content;

    public long getRequestId() {
        return requestId;
    }

    public void setRequestId(long requestId) {
        this.requestId = requestId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "JnkdogResponse{" +
                "requestId=" + requestId +
                ", status=" + status +
                ", content=" + content +
                '}';
    }
}
