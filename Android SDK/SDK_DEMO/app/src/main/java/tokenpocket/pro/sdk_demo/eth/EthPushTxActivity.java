package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Blockchain;
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

    /**
     * 通用交易
     */
    private void pushTx() {
        String chainId = etChainId.getText().toString();
        Transaction transaction = new Transaction();
        //标识链
        List<Blockchain> blockchains = new ArrayList<>();
        //指定哪个网络的钱包来执行这次操作，这里写的是BSC
        blockchains.add(new Blockchain("ethereum", chainId));
        transaction.setBlockchains(blockchains);
        transaction.setDappName("Test demo");
        transaction.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者提供的业务id，用来标识本次操作
        transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transaction.setAction("pushTransaction");
        //这里直接填充你生成的交易数据，
        transaction.setTxData(etData.getText().toString());
        //        transaction.setTxData("{\n" +
        //                "\t\"from\": \"0x5Da73693A062a11589F1b5c68434bf7eAff72366\",\n" +
        //                "\t\"gas\": \"0x8cec\",\n" +
        //                "\t\"chainId\": 56,\n" +
        //                "\t\"data\": " +
        //                "\"0xa9059cbb00000000000000000000000054018569ee4d68a275909cc2538ff67a742f41c8000000000000000000000000000000000000000000000000000000000000000a\",\n" +
        //                "\t\"to\": \"0xECa41281c24451168a37211F0bc2b8645AF45092\",\n" +
        //                "\t\"gasPrice\": \"0x13f2ed0c0\"\n" +
        //                "}");

        TPManager.getInstance().pushTransaction(this, transaction, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //成功后，会返回相应的交易hash，注意，这里并不能保证交易一定在链上成功，开发者需要自己通过交易hash,确认最终链上结果
                Toast.makeText(EthPushTxActivity.this, s, Toast.LENGTH_LONG).show();
                Log.e("pushTransaction", s);
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


    public static void start(Context context) {
        Intent intent = new Intent(context, EthPushTxActivity.class);
        context.startActivity(intent);
    }
}
