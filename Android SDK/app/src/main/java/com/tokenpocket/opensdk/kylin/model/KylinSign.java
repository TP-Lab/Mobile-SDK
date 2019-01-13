package com.tokenpocket.opensdk.kylin.model;

import com.google.gson.annotations.SerializedName;
import com.tokenpocket.opensdk.kylin.KylinConstant;

/**
 * Author: tp-clement
 * Create: 2018/10/23
 * Desc: Kylin签名
 */

public class KylinSign {

    //协议版本
    private String v = KylinConstant.VERSION;

    //tokens_info.json 中的每个数字资产的唯一标识
    @SerializedName("tokenid")
    private String tokenId;

    //String 提供签名的钱包账号在钱包系统中的userid。如eos中为其eos账号名，eth为公钥地址
    @SerializedName("accountName")
    private String accountname;

    //String, 自定义签名附加字段，可选参数
    @SerializedName("customdata")
    private String customData;

    //String, 其他信息，可用作钱包信息呈现，可选参数
    private String msg;

    //dapps_info.json 中DApp全网唯一的symbol字段, 可选参数
    @SerializedName("dappsymbol")
    private String dappSymbol;

    //String 认证，格式为 accesskey + ":" + signature
    private String authorization;

    //指定回调scheme
    private String cb;
}
