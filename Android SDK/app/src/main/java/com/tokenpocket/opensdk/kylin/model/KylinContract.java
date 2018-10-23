package com.tokenpocket.opensdk.kylin.model;

import com.google.gson.annotations.SerializedName;
import com.tokenpocket.opensdk.kylin.KylinConstant;

import java.util.List;

/**
 * Author: tp-clement
 * Create: 2018/10/23
 * Desc: Kylin执行合约
 */

public class KylinContract {

    //协议版本
    private String v = KylinConstant.VERSION;

    //tokens_info.json 中的每个数字资产的唯一标识
    @SerializedName("tokenid")
    private String tokenId;

    //dapps_info.json 中DApp全网唯一的symbol字段, 可选参数
    @SerializedName("dappsymbol")
    private String dappSymbol;

    //String 认证，格式为 accesskey + ":" + signature
    private String authorization;

    //当前帐号
    private String account;

    //当前帐号对应的公钥地址，钱包会拿该地址对应的私钥进行签名
    private String address;

    //合约options，可选参数
    private String options;

    //当前标识该此次调用的ID，可选参数
    @SerializedName("actionid")
    private String actionId;

    //String, 其他信息，可用作钱包信息呈现，可选参数
    private String msg;

    //指定回调scheme
    private String cb;

    //合法的EOS action格式数据，具体格式见备注
    private List<String> actions;

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getActionId() {
        return actionId;
    }

    public void setActionId(String actionId) {
        this.actionId = actionId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public List<String> getActions() {
        return actions;
    }

    public void setActions(List<String> actions) {
        this.actions = actions;
    }
}
