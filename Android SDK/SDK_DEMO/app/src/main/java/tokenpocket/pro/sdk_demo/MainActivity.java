package tokenpocket.pro.sdk_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import tokenpocket.pro.sdk_demo.eos.EOSDemoActivity;
import tokenpocket.pro.sdk_demo.eth.EthDemoActivity;
import tokenpocket.pro.sdk_demo.iost.IostDemoActivity;
import tokenpocket.pro.sdk_demo.minwallet.MiniWalletActivity;
import tokenpocket.pro.sdk_demo.tron.TronDemoActivity;

/**
 * Created by duke on 2019/7/9.
 */

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        TPManager.getInstance().initSDK(this);
        findViewById(R.id.btn_eos).setOnClickListener(this);
//        findViewById(R.id.btn_bos).setOnClickListener(this);
        findViewById(R.id.btn_iost).setOnClickListener(this);
        findViewById(R.id.btn_eth).setOnClickListener(this);
        findViewById(R.id.btn_tron).setOnClickListener(this);
        findViewById(R.id.btn_minwallet).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_eos:
                startActivity(EOSDemoActivity.class);
                break;
//            case R.id.btn_bos:
//                startActivity(BOSDemoActivity.class);
//                break;
            case R.id.btn_iost:
                startActivity(IostDemoActivity.class);
                break;
            case R.id.btn_eth:
                startActivity(EthDemoActivity.class);
                break;
            case R.id.btn_tron:
                startActivity(TronDemoActivity.class);
                break;
            case R.id.btn_minwallet:
                startActivity(MiniWalletActivity.class);
                break;
        }
    }

    private void startActivity(Class cls) {
        Intent intent = new Intent(this, cls);
        this.startActivity(intent);
    }
}
