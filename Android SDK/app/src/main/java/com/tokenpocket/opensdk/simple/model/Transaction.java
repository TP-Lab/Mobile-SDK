package com.tokenpocket.opensdk.simple.model;

/**
 * Author: tp-clement
 * Create: 2019/3/28
 * Desc: 通用交易
 */
public class Transaction extends BaseInfo {

    private String actions;

    public Transaction() {
        //默认设置类型
        setAction(ActionEnum.Transaction.getValue());
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
}
