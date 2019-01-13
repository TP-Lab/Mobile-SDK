package com.tokenpocket.opensdk.kylin;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tokenpocket.opensdk.TPUtil;
import com.tokenpocket.opensdk.kylin.model.KylinContract;
import com.tokenpocket.opensdk.kylin.model.KylinLogin;
import com.tokenpocket.opensdk.kylin.model.KylinPay;
import com.tokenpocket.opensdk.kylin.model.KylinResult;
import com.tokenpocket.opensdk.kylin.model.KylinSign;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Author: tp-clement
 * Create: 2018/10/23
 * Desc: Kylin协议管理器
 */

public class KylinManager {

    private static KylinManager sInstance;
    //回调
    private KylinCallback mKylinCallback;

    private KylinManager() {
    }

    public static KylinManager getInstance() {
        if (sInstance == null) {
            synchronized (KylinManager.class) {
                if (sInstance == null) {
                    sInstance = new KylinManager();
                }
            }
        }
        return sInstance;
    }

    /**
     * 支付
     */
    public void pay(Context context, KylinPay pay, KylinCallback callback) {
        this.mKylinCallback = callback;
        pullUpTP(context, KylinConstant.KYLIN_PAY, new Gson().toJson(pay));
    }

    /**
     * 授权登录
     */
    public void login(Context context, KylinLogin login, KylinCallback callback) {
        this.mKylinCallback = callback;
        pullUpTP(context, KylinConstant.KYLIN_LOGIN, new Gson().toJson(login));
    }

    /**
     * 签名
     */
    public void sign(Context context, KylinSign sign, KylinCallback callback) {
        this.mKylinCallback = callback;
        pullUpTP(context, KylinConstant.KYLIN_SIGN, new Gson().toJson(sign));
    }

    /**
     * 执行合约
     */
    public void contract(Context context, KylinContract contract, KylinCallback callback) {
        this.mKylinCallback = callback;
        pullUpTP(context, KylinConstant.KYLIN_CONTRACT, new Gson().toJson(contract));
    }

    /**
     * 解析数据，并回调
     */
    public void parseIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        Uri uri = intent.getData();
        if (uri == null) {
            return;
        }

        if (TextUtils.isEmpty(uri.getScheme())) {
            return;
        }

        String params = uri.getQueryParameter("params");
        if (TextUtils.isEmpty(params)) {
            return;
        }

        //Base64 解密
        params = TPUtil.base64Decode(params);
        //解析常规的字段
        KylinResult result = new Gson().fromJson(params, KylinResult.class);

        //解析所有的params下的参数
        Map<String, Object> mapParams = new HashMap<>();
        JsonObject jsonObject = new JsonParser().parse(params).getAsJsonObject();
        Set<String> keys = jsonObject.keySet();
        for (String key : keys) {
            mapParams.put(key, jsonObject.get(key));
        }

        //执行回调
        if (mKylinCallback != null) {
            mKylinCallback.callback(mapParams, result);
        }

    }

    /**
     * 拉起TP
     */
    private void pullUpTP(Context context, String protocol, String params) {
        Intent intent = new Intent();
        //传递包名、类名、app名
        intent.putExtra("packageName", context.getPackageName());
        intent.putExtra("className", KylinAssistActivity.class.getName());
        intent.putExtra("appName", TPUtil.getAppName(context));
        //拼凑uri
        intent.setData(getParamUri(protocol, params));
        intent.setAction(Intent.ACTION_VIEW);
        //保证新启动的APP有单独的堆栈，如果希望新启动的APP和原有APP使用同一个堆栈则去掉该项
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            //如果找不到，提示升级TP钱包
            if (mKylinCallback != null) {
                mKylinCallback.callback(null,
                        new KylinResult(-1, "Please install TokenPocket or upgrade to the latest version"));
            }
        }
    }

    /**
     * 拼凑要传递数据的uri
     */
    private Uri getParamUri(String protocol, String params) {
        //将param encode处理
        String temp = protocol + "?params=" + TPUtil.base64Encode(params);
        return Uri.parse(temp);
    }

}
