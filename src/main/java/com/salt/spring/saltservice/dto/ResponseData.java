package com.salt.spring.saltservice.dto;

import java.util.ArrayList;
import java.util.List;

// Response HTTP Method access
// DTO (Data Transfer Object) digunakan untuk perantara antara client ke server atau sebaliknya
public class ResponseData<T> {
    private boolean status;

    private List<String> messages = new ArrayList<>();

    private T payload;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

}

