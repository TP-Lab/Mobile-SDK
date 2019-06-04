package com.tokenpocket.opensdk.simple.model;

import com.tokenpocket.opensdk.BuildConfig;

/**
 * Author: tp-clement
 * Create: 2019/3/28
 * Desc: simple 协议的基类
 */
public abstract class BaseInfo {

    /**
     * 协议
     */
    private String protocol = "TokenPocket";

    /**
     * 版本号
     */
    private String version = BuildConfig.VERSION_NAME;

    /**
     * dapp名称
     */
    private String dappName;

    /**
     * dapp 图标
     */
    private String dappIcon;

    /**
     * 执行的操作类型:transfer,pushTransaction,login,sign
     */
    private String action;

    /**
     * 过期时间，单位为秒
     */
    private long expired;

    /**
     * 标识底层
     */
    private String blockchain;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDappName() {
        return dappName;
    }

    public void setDappName(String dappName) {
        this.dappName = dappName;
    }

    public String getDappIcon() {
        return dappIcon;
    }

    public void setDappIcon(String dappIcon) {
        this.dappIcon = dappIcon;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getExpired() {
        return expired;
    }

    public void setExpired(long expired) {
        this.expired = expired;
    }

    public String getBlockchain() {
        return blockchain;
    }

    public void setBlockchain(String blockchain) {
        this.blockchain = blockchain;
    }
}
