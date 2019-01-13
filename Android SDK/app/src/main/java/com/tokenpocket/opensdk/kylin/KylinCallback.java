package com.tokenpocket.opensdk.kylin;

import com.tokenpocket.opensdk.kylin.model.KylinResult;

import java.util.Map;

/**
 * Author: tp-clement
 * Create: 2018/10/23
 * Desc:
 */

public interface KylinCallback {

    /**
     * @param params 不同接口传递其他额外参数
     * @param result 每个接口都会有的result
     */
    void callback(Map<String, Object> params, KylinResult result);
}
