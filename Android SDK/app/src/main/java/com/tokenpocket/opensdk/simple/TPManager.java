package com.tokenpocket.opensdk.simple;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.tokenpocket.opensdk.Constant;
import com.tokenpocket.opensdk.TPUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Author: tp-clement
 * Create: 2018/9/2
 * Desc: TPManager
 */

public class TPManager {

    private static TPManager sInstance;

    private TPListener mListener;
    //回调的状态
    private final static int SUCCESS = 1;
    private final static int CANCEL = 0;
    private final static int ERROR = 2;
    //TP uri
    private final static String TP_SCHEME_HOST = "tpoutside://pull.activity";

    private TPManager() {

    }

    public static TPManager getInstance() {
        if (sInstance == null) {
            synchronized (TPManager.class) {
                if (sInstance == null) {
                    sInstance = new TPManager();
                }
            }
        }
        return sInstance;
    }

    private void setTPListener(TPListener listener) {
        this.mListener = listener;
    }

    /**
     * 执行操作
     */
    private void doAction(Context context, String param, TPListener listener) {
        //设置监听器
        setTPListener(listener);
        pullUpTP(context, param);
    }

    /**
     * 转账
     */
    public void transfer(Context context, String transferData) {
        transfer(context, transferData, null);
    }

    /**
     * 转账
     */
    public void transfer(Context context, String transferData, TPListener listener) {
        doAction(context, transferData, listener);
    }

    /**
     * 提交交易
     */
    public void pushTransaction(Context context, String transactionData) {
        pushTransaction(context, transactionData, null);
    }

    /**
     * 提交交易
     */
    public void pushTransaction(Context context, String transactionData, TPListener listener) {
        doAction(context, transactionData, listener);
    }

    /**
     * 授权登陆
     */
    public void authLogin(Context context, String authData) {
        authLogin(context, authData, null);
    }

    /**
     * 授权登陆
     */
    public void authLogin(Context context, String authData, TPListener listener) {
        doAction(context, authData, listener);
    }

    /**
     * 解析数据，并回调
     */
    public void parseIntent(Intent intent) {
        if (mListener == null) {
            return;
        }

        int status = intent.getIntExtra("status", 0);
        String result = intent.getStringExtra("result");
        if (result == null) {
            mListener.onError("Unknown error");
            return;
        }

        switch (status) {
            case ERROR:
                mListener.onError(result);
                break;
            case CANCEL:
                mListener.onCancel(result);
                break;
            case SUCCESS:
            default:
                mListener.onSuccess(result);
                break;
        }
    }

    /**
     * 拉起TP
     */
    private void pullUpTP(Context context, String param) {
        Intent intent = new Intent();
        //传递包名、类名、app名
        intent.putExtra("packageName", context.getPackageName());
        intent.putExtra("className", TPAssistActivity.class.getName());
        intent.putExtra("appName", TPUtil.getAppName(context));
        //拼凑uri
        intent.setData(getParamUri(param));
        intent.setAction(Intent.ACTION_VIEW);
        //保证新启动的APP有单独的堆栈，如果希望新启动的APP和原有APP使用同一个堆栈则去掉该项
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            if (mListener != null) {
                mListener.onCancel("Please install TokenPocket or upgrade to the latest version");
            }
        }
    }

    /**
     * 获取uri
     */
    private Uri getParamUri(String param) {
        //将param encode处理
        try {
            param = URLEncoder.encode(param, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String temp = TP_SCHEME_HOST + "?param=" + param;
        return Uri.parse(temp);
    }

}
