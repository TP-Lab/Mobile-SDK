package com.tokenpocket.opensdk;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Author: tp-clement
 * Create: 2018/9/2
 * Desc:
 */

public class TPAssistActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在回调的activity处理逻辑
        TPManager.getInstance().parseIntent(getIntent());
        this.finish();
    }

}
