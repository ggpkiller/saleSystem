package xyz.nxlexiaoyao.sales.bean;

import com.alibaba.fastjson.annotation.JSONField;

public class Token {
    private String userId;
    private String token;
    @JSONField(serialize = false)
    private Long timestamp;


    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
