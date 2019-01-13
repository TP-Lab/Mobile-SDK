package com.tokenpocket.opensdk.kylin;

import android.app.Activity;
import android.os.Bundle;

/**
 * Author: tp-clement
 * Create: 2018/10/23
 * Desc: 接收Kylin回调
 */

public class KylinAssistActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在回调的activity处理逻辑
        KylinManager.getInstance().parseIntent(getIntent());
        this.finish();
    }

}
