package com.tokenpocket.opensdk.simple.model;

import com.google.gson.Gson;

/**
 * Author: tp-clement
 * Create: 2019/3/28
 * Desc: 通用交易
 */
public class Transaction extends BaseInfo {

    private String actions;

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }
}
