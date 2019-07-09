package tokenpocket.pro.sdk_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import tokenpocket.pro.sdk_demo.bos.BOSDemoActivity;
import tokenpocket.pro.sdk_demo.eos.EOSDemoActivity;
import tokenpocket.pro.sdk_demo.iost.IostDemoActivity;
import tokenpocket.pro.sdk_demo.minwallet.MinWalletActivity;

/**
 * Created by duke on 2019/7/9.
 */

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_eos);
        findViewById(R.id.btn_bos);
        findViewById(R.id.btn_iost);
        findViewById(R.id.btn_minwallet);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_eos:
                startActivity(EOSDemoActivity.class);
                break;
            case R.id.btn_bos:
                startActivity(BOSDemoActivity.class);
                break;
            case R.id.btn_iost:
                startActivity(IostDemoActivity.class);
                break;
            case R.id.btn_minwallet:
                startActivity(MinWalletActivity.class);
                break;
        }
    }

    private void startActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        this.startActivity(intent);
    }
}
