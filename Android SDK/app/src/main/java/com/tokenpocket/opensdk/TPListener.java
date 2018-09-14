package com.tokenpocket.opensdk;

/**
 * Author: tp-clement
 * Create: 2018/9/1
 * Desc:
 */

public interface TPListener {

    void onSuccess(String data);

    void onError(String data);

    void onCancel(String data);
}
