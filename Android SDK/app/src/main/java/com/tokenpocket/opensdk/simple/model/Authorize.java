package com.tokenpocket.opensdk.simple.model;

/**
 * Author: tp-clement
 * Create: 2019/3/28
 * Desc: 授权登录
 */
public class Authorize extends BaseInfo {

    private String actionId;

    private String callbackUrl;

    private String memo;

    public Authorize() {
        //默认设置类型
        setAction(ActionEnum.Authorize.getValue());
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}


