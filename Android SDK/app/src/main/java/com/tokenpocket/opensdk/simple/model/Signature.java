package com.tokenpocket.opensdk.simple.model;

/**
 * Author: tp-clement
 * Create: 2019/3/28
 * Desc: 签名
 */
public class Signature extends BaseInfo {

    private String actionId;

    private String callbackUrl;

    private String memo;

    private String message;

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }
}
