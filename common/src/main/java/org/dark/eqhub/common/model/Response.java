package org.dark.eqhub.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.ZonedDateTime;

public class Response {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String message;
    private Object data;
    private long timestamp;

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Response(Object data) {
        this.data = data;
        this.timestamp = ZonedDateTime.now().toInstant().toEpochMilli();
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
