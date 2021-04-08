package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
public class EthPersonalSignActivity extends Activity {

    private EditText etData;
    private EditText etChainId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_personal_sign);
        etData = findViewById(R.id.et_data);
        etChainId = findViewById(R.id.et_chain_id);
        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ethSign();
            }
        });
    }

    private void ethSign() {
        Signature signature = new Signature();
        //已废弃
        //signature.setBlockchain(CHAIN);
        //标识链
        List<Blockchain> blockchains = new ArrayList<>();
        blockchains.add(new Blockchain("ethereum", getChainId()));
        signature.setBlockchains(blockchains);

        signature.setDappName("Test demo");
        signature.setDappIcon("https://eosknights.io/img/icon.png");
        signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        signature.setMemo("demo");
        signature.setSignType("ethPersonalSign");
        signature.setMessage(etData.getText().toString());
        TPManager.getInstance().signature(this, signature, new TPListener() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(EthPersonalSignActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(EthPersonalSignActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(EthPersonalSignActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }

    /**
     * 获取chainId，默认是1，即eth链
     */
    private String getChainId() {
        String chainId = etChainId.getText().toString();
        if (TextUtils.isEmpty(chainId)) {
            return "1";
        }
        return chainId;
    }

    public static void start(Context context) {
        Intent intent = new Intent(context, EthPersonalSignActivity.class);
        context.startActivity(intent);
    }
}
