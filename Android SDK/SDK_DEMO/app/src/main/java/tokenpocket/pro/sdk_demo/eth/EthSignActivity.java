package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Blockchain;
import com.tokenpocket.opensdk.simple.model.Signature;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Author: tp-clement
 * Create: 4/8/21
 * Desc: eth签名
 */
public class EthSignActivity extends Activity {

    private EditText etData;
    private EditText etChainId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_sign);
        etData = findViewById(R.id.et_data);
        etChainId = findViewById(R.id.et_chain_id);

        findViewById(R.id.bt_ethPersonalSign).setOnClickListener(v -> sign("ethPersonalSign"));
        findViewById(R.id.bt_ethSignTypedDataLegacy).setOnClickListener(v -> sign("ethSignTypedDataLegacy"));
        findViewById(R.id.bt_ethSignTypedData).setOnClickListener(v -> sign("ethSignTypedData"));
        findViewById(R.id.bt_ethSignTypedData_v4).setOnClickListener(v -> sign("ethSignTypedData_v4"));
    }

    private void sign(String signType) {
        String chainId = etChainId.getText().toString();
        Signature signature = new Signature();
        //标识链
        List<Blockchain> blockchains = new ArrayList<>();
        blockchains.add(new Blockchain("ethereum", chainId));
        signature.setBlockchains(blockchains);

        signature.setDappName("Test demo");
        signature.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务ID，用于标识操作，在授权登录中，需要设置该字段
        signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        //签名类型,如ethPersonalSign
        signature.setSignType(signType);
        //签名的数据
        signature.setMessage(etData.getText().toString());
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        signature.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().signature(this, signature, new TPListener() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(EthSignActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(EthSignActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(EthSignActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, EthSignActivity.class);
        context.startActivity(intent);
    }
}
