package com.tokenpocket.opensdk.kylin.model;

import com.google.gson.annotations.SerializedName;
import com.tokenpocket.opensdk.kylin.KylinConstant;

/**
 * Author: tp-clement
 * Create: 2018/10/23
 * Desc: Kylin授权登录
 */

public class KylinLogin {

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

    //指定回调scheme
    private String cb;

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

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }
}
