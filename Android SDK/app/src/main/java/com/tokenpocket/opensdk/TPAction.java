package com.tokenpocket.opensdk;

/**
 * Author: tp-clement
 * Create: 2018/9/14
 * Desc:
 */

public enum TPAction {

    TRANSFER("transfer"),
    PUSH_TRANSACTION("pushTransaction"),
    AUTH_LOGIN("login");

    private String value;

    TPAction(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
