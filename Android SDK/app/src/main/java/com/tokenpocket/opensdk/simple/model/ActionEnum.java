package com.tokenpocket.opensdk.simple.model;

/**
 * Author: tp-clement
 * Create: 2019/4/1
 * Desc: 操作类型
 */
public enum ActionEnum {

    /**
     * 转账
     */
    Transfer("transfer"),

    /**
     * 通用交易
     */
    Transaction("pushTransaction"),

    /**
     * 授权
     */
    Authorize("login"),

    /**
     * 授权
     */
    Signature("sign");

    private String value;

    ActionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
