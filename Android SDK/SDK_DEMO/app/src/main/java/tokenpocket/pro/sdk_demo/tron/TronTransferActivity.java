package tokenpocket.pro.sdk_demo.tron;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Transfer;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/9/18.
 */

public class TronTransferActivity extends Activity implements View.OnClickListener {

    private Button btnTransfer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tron_transfer);

        btnTransfer = findViewById(R.id.btn_transfer);

        btnTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_transfer:
                transfer();
                break;
        }
    }


    private void transfer() {
        Transfer transfer = new Transfer();
        transfer.setBlockchain("TRON");
        transfer.setProtocol("TokenPocket");
        transfer.setVersion("1.0");
        transfer.setDappName("Test demo");
        transfer.setDappIcon("https://eosknights.io/img/icon.png");
        transfer.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transfer.setAction("transfer");
        transfer.setFrom("TYg1YJTQqaeWF8yhFFcnkEExYpFbAHuyyc");
        transfer.setTo("TJ6zhkmdsHrh7iFKW8aJvbqYiujaM3R9fb");
        //代币合约，如果是Tron，则为空
        transfer.setContract("TR7NHqjeKQxGTCi8q8ZY4pL8otSzgjLj6t");
        transfer.setAmount(0.1);
        //必须设置
        transfer.setDecimal(6);
        transfer.setSymbol("USDT");
        transfer.setDesc("UI展示,不上链");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        transfer.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().transfer(this, transfer, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //转账成功后，会返回相应的交易hash，注意，钱包只是把交易push出去，并不能保证最后交易结果，开发者需要根据hash自行确定链上结果
                Toast.makeText(TronTransferActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(TronTransferActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(TronTransferActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }
}
