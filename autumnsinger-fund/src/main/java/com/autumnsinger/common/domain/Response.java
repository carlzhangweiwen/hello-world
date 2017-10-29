package com.autumnsinger.common.domain;

import com.autumnsinger.common.enums.ResponseEnum;

/**
 * Created by carl on 17-7-17.
 */
public class Response {
    private ResponseEnum status;
    private String content;

    public Response(ResponseEnum status, String content) {
        this.status = status;
        this.content = content;
    }

    public ResponseEnum getStatus() {
        return status;
    }

    public void setStatus(ResponseEnum status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
