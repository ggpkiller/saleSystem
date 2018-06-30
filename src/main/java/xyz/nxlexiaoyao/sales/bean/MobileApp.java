package xyz.nxlexiaoyao.sales.bean;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class MobileApp {
    @JSONField(serialize = false)
    private String id;
    private String url;
    private Integer version;
    @JSONField(serialize = false)
    private Date insert_time;
    private String introduce;
    private String forceStatus;




    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getForceStatus() {
        return forceStatus;
    }

    public void setForceStatus(String forceStatus) {
        this.forceStatus = forceStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getInsert_time() {
        return insert_time;
    }

    public void setInsert_time(Date insert_time) {
        this.insert_time = insert_time;
    }
}
