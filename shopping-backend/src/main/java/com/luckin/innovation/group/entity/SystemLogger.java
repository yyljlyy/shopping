package com.luckin.innovation.group.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Entity
@Table(name = "system_logger")
public class SystemLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    /**
    请求耗时（毫秒单位）
    */
    private Integer timeConsuming ;
    /**
    客户端请求IP地址
    */
    private String clientIp ;
    /**
    请求时httpStatusCode代码，如：200,400,404等
    */
    private String httpStatusCode ;
    /**
    请求方式method,post,get等
    */
    private String method ;

    @Column(columnDefinition = "longtext")
    private String paramData ;
    /**
     * 接口返回时间
     */
    private String returnTime;

    @Column(columnDefinition = "longtext")
    private String returnData ;
    /**
    请求接口唯一session标识
    */
    private String sessionId ;

    private String type ;

    private String uri ;
    /**
    请求时间
    */
    private Date createTime ;

    public SystemLogger() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimeConsuming() {
        return timeConsuming;
    }

    public void setTimeConsuming(Integer timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(String httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParamData() {
        return paramData;
    }

    public void setParamData(String paramData) {
        this.paramData = paramData;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }

    public String getReturnData() {
        return returnData;
    }

    public void setReturnData(String returnData) {
        this.returnData = returnData;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
