package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.innerwallet.model.LinkAction;
import com.tokenpocket.opensdk.simple.model.Authorize;
import com.tokenpocket.opensdk.simple.model.Blockchain;
import com.tokenpocket.opensdk.simple.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/7/9.
 */

public class EthDemoActivity extends Activity implements View.OnClickListener {

    private Button btnAuthorize;
    private Button btnSign;
    private Button btnPersonalSign;
    private Button btnTransfer;
    private Button btnPushTransaction;
    private EditText etChainId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_demo);

        btnAuthorize = findViewById(R.id.btn_authorize);
        btnSign = findViewById(R.id.btn_sign);
        btnPersonalSign = findViewById(R.id.btn_personsign);
        btnTransfer = findViewById(R.id.btn_transfer);
        btnPushTransaction = findViewById(R.id.btn_pushtx);
        etChainId = findViewById(R.id.et_chain_id);

        btnAuthorize.setOnClickListener(this);
        btnSign.setOnClickListener(this);
        btnPersonalSign.setOnClickListener(this);
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
            case R.id.btn_personsign:
                EthPersonalSignActivity.start(this);
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
        Authorize authorize = new Authorize();
        //已废弃
        //authorize.setBlockchain(CHAIN);
        //标识链
        List<Blockchain> blockchains = new ArrayList<>();
        blockchains.add(new Blockchain("ethereum", getChainId()));
        authorize.setBlockchains(blockchains);

        authorize.setDappName("Test demo");
        authorize.setDappIcon("https://eosknights.io/img/icon.png");
        authorize.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        authorize.setMemo("demo");
        TPManager.getInstance().authorize(this, authorize, new TPListener() {
            @Override
            public void onSuccess(String s) {
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
}
