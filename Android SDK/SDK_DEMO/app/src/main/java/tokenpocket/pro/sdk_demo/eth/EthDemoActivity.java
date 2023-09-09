package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Authorize;
import com.tokenpocket.opensdk.simple.model.Blockchain;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/7/9.
 */

public class EthDemoActivity extends Activity implements View.OnClickListener {

    private Button btnAuthorize;
    private Button btnSign;
    private Button btnTransfer;
    private Button btnPushTransaction;
    private EditText etChainId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_demo);

        btnAuthorize = findViewById(R.id.btn_authorize);
        btnSign = findViewById(R.id.btn_sign);
        btnTransfer = findViewById(R.id.btn_transfer);
        btnPushTransaction = findViewById(R.id.btn_pushtx);
        etChainId = findViewById(R.id.et_chain_id);

        btnAuthorize.setOnClickListener(this);
        btnSign.setOnClickListener(this);
        btnTransfer.setOnClickListener(this);
        btnPushTransaction.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_authorize:
                authorize();
                break;
            case R.id.btn_sign:
                EthSignActivity.start(this);
                break;
            case R.id.btn_transfer:
                EthTransferActivity.start(this);
                break;
            case R.id.btn_pushtx:
                EthPushTxActivity.start(this);
                break;
        }
    }

    private void authorize() {
        String chainId = etChainId.getText().toString();
        Authorize authorize = new Authorize();
        List blockchains = new ArrayList();
        //blockchains指定可以用哪些网络的钱包操作，evm系列，第一个参数是ethereum,第二个参数是网络的id
        blockchains.add(new Blockchain("ethereum", chainId));
        authorize.setBlockchains(blockchains);
        authorize.setAction("login");
        //开发者自己定义的业务ID，用于标识操作，在授权登录中，需要设置该字段
        authorize.setActionId(String.valueOf(System.currentTimeMillis()));
        authorize.setProtocol("TokenPocket");
        authorize.setVersion("v1.0");
        authorize.setDappName("zs");
        authorize.setDappIcon("https://eosknights.io/img/icon.png");
        authorize.setMemo("demo");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        authorize.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");

        TPManager.getInstance().authorize(this, authorize, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //登录成功后，会返回登录的钱包地址，签名信息，开发者可以利用这两个参数验证有效性
                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }
}
