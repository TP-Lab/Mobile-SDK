package com.tokenpocket.opensdk.kylin.model;

import com.google.gson.annotations.SerializedName;
import com.tokenpocket.opensdk.kylin.KylinConstant;

/**
 * Author: tp-clement
 * Create: 2018/10/23
 * Desc: Kylin支付
 */

public class KylinPay {

    //协议版本
    private String v = KylinConstant.VERSION;

    //String 支付账户，可选参数
    private String from;

    //String 接收币的目的账户
    private String to;

    //tokens_info.json 中的每个数字资产的唯一标识
    @SerializedName("tokenid")
    private String tokenId;

    //String 支付数量
    private String num;

    //String 转账备注，可选参数
    private String memo;

    //String, 其他信息，可用作钱包信息呈现，可选参数
    private String msg;

    //当前标识该此次调用的ID，可选参数
    @SerializedName("actionid")
    private String actionId;

    //用户身份ID，可选参数
    @SerializedName("userid")
    private String userId;

    //dapps_info.json 中DApp全网唯一的symbol字段, 可选参数
    @SerializedName("dappsymbol")
    private String dappSymbol;

    //String 认证，格式为 accesskey + ":" + signature
    private String authorization;

    //指定回调scheme
    private String cb;

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDappSymbol() {
        return dappSymbol;
    }

    public void setDappSymbol(String dappSymbol) {
        this.dappSymbol = dappSymbol;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }
}
