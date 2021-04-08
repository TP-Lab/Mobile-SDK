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
import com.tokenpocket.opensdk.innerwallet.model.LinkAction;
import com.tokenpocket.opensdk.simple.model.Blockchain;
import com.tokenpocket.opensdk.simple.model.Signature;
import com.tokenpocket.opensdk.simple.model.Transaction;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Author: tp-clement
 * Create: 4/8/21
 * Desc: eth签名
 */
public class EthPushTxActivity extends Activity {

    private EditText etData;
    private EditText etChainId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_push_tx);
        etData = findViewById(R.id.et_data);
        etChainId = findViewById(R.id.et_chain_id);
        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushTx();
            }
        });
    }

    private void pushTx() {
        Transaction transaction = new Transaction();
        //已废弃
        //transaction.setBlockchain(CHAIN);
        //标识链
        List<Blockchain> blockchains = new ArrayList<>();
        blockchains.add(new Blockchain("ethereum", getChainId()));
        transaction.setBlockchains(blockchains);

        transaction.setDappName("Test demo");
        transaction.setDappIcon("https://eosknights.io/img/icon.png");
        transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transaction.setAction("pushTransaction");
        transaction.setLinkActions(new ArrayList<LinkAction>());
//        transaction.setTxData("{\"from\":\"0x22F4900A1fB41f751b8F616832643224606B75B4\",\"gasPrice\":\"0x6c088e200\",\"gas\":\"0xea60\",\"chainId\":\"1\",\"to\":\"0x7d1e7fb353be75669c53c18ded2abcb8c4793d80\",\"data\":\"0xa9059cbb000000000000000000000000171a0b081493722a5fb8ebe6f0c4adf5fde49bd8000000000000000000000000000000000000000000000000000000000012c4b0\"}");
        transaction.setTxData(etData.getText().toString());
        TPManager.getInstance().pushTransaction(this, transaction, new TPListener() {
            @Override
            public void onSuccess(String s) {
                Toast.makeText(EthPushTxActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(String s) {
                Toast.makeText(EthPushTxActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(EthPushTxActivity.this, s, Toast.LENGTH_LONG).show();

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
        Intent intent = new Intent(context, EthPushTxActivity.class);
        context.startActivity(intent);
    }
}
