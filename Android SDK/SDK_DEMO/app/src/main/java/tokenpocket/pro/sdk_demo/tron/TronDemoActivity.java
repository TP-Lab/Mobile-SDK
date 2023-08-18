package tokenpocket.pro.sdk_demo.tron;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Authorize;
import com.tokenpocket.opensdk.simple.model.Signature;
import com.tokenpocket.opensdk.simple.model.Transaction;

import tokenpocket.pro.sdk_demo.R;


/**
 * Created by duke on 2019/7/9.
 */

public class TronDemoActivity extends Activity implements View.OnClickListener {

    private Button btnAuthorize;
    private Button btnSign;
    private Button btnTransfer;
    private Button btnPushTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tron_demo);

        btnAuthorize = findViewById(R.id.btn_authorize);
        btnSign = findViewById(R.id.btn_sign);
        btnTransfer = findViewById(R.id.btn_transfer);
        btnPushTransaction = findViewById(R.id.btn_pushtx);

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
                sign();
                break;
            case R.id.btn_transfer:
                transfer();
                break;
            case R.id.btn_pushtx:
                pushTx();
                break;
        }
    }

    private void authorize() {
        Authorize authorize = new Authorize();
        authorize.setBlockchain("TRON");
        authorize.setProtocol("TokenPocket");
        authorize.setVersion("1.0");
        authorize.setDappName("Test demo");
        authorize.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务id，用来标识本次操作,授权操作一定要设置这个参数
        authorize.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        authorize.setAction("login");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        authorize.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().authorize(this, authorize, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //登录成功后，会返回登录的钱包地址，签名信息，开发者可以利用这两个参数验证有效性
                Toast.makeText(TronDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {

            }

            @Override
            public void onCancel(String s) {

            }
        });

    }

    private void sign() {
        Signature signature = new Signature();
        signature.setBlockchain("TRON");
        signature.setProtocol("TokenPocket");
        signature.setVersion("1.0");
        signature.setDappName("Test demo");
        signature.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务id，用来标识本次操作
        signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        //只支持16进制字符串
        signature.setMessage("0x2ed4adccb3b5e3ee5212b6148a9fafaf716ddf2820f8dab63a77a1b682c51457");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        signature.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().signature(this, signature, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //签名完成后，会返回签名的钱包信息和签名信息
                Toast.makeText(TronDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(TronDemoActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(TronDemoActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }

    private void transfer() {
        Intent intent = new Intent(this, TronTransferActivity.class);
        startActivity(intent);
    }

    /**
     * 通用操作接口，以抵押资源为例演示
     */
    private void pushTx() {
        Transaction transaction = new Transaction();
        transaction.setBlockchain("TRON");
        transaction.setProtocol("TokenPocket");
        transaction.setVersion("1.0");
        transaction.setDappName("Test demo");
        transaction.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务id，用来标识本次操作
        transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transaction.setAction("pushTransaction");
        //tron，pushTx操作实际上是对构造好的交易对象进行签名，要求的参数和tronlink插件钱包签名数据格式一样，

        /**
         * {
         * 	"visible": false,
         * 	"txID": "7d4a8868a806aaf2a3f5bf23f51667d83bf67bcaf17d3e176a4ce347a2450649",
         * 	"raw_data": {
         * 		"contract": [{
         * 			"parameter": {
         * 				"value": {
         * 					"frozen_duration": 3,
         * 					"frozen_balance": 4000000,
         * 					"owner_address": "41593a115d4e04249a7e4c02a2150b596081b37f9f"
         *                                },
         * 				"type_url": "type.googleapis.com\/protocol.FreezeBalanceContract"*
         * 				},
         * 			"type": "FreezeBalanceContract"
         *        }        ],
         * 		"ref_block_bytes": "ebec",
         * 		"ref_block_hash": "83e6e13e48ce9f6c",
         * 		"expiration": 1648893501000,
         * 		"timestamp": 1648893443731
         *    },
         * 	"raw_data_hex":
         * 	"0a02ebec220883e6e13e48ce9f6c40c8bcd8cdfe2f5a58080b12540a32747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e467265657a6542616c616e6365436f6e7472616374121e0a1541593a115d4e04249a7e4c02a2150b596081b37f9f108092f40118037093fdd4cdfe2f",
         * 	"userTronHeader": true
         * }
         */
        transaction.setTxData("{\n" +
                "\t\t\"visible\": false,\n" +
                "\t\t\"txID\": \"7111456f4e7e86360790a8d10e8e3e78e7f882cfd6f190805a78a1e5da335705\",\n" +
                "\t\t\"raw_data\": {\n" +
                "\t\t\t\"contract\": [{\n" +
                "\t\t\t\t\"parameter\": {\n" +
                "\t\t\t\t\t\"value\": {\n" +
                "\t\t\t\t\t\t\"data\": " +
                "\"c10bea5c000000000000000000000000000000000000000000000000000000000000004000000000000000000000000000000000000000000000000000000000000001c0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003487b63d30b5b2c87fb7ffa8bcfade38eaac1abe00000000000000000000000087d5ef4c42a78f85dd1ca399fa0c99f9ab0875d9000000000000000000000000f90a4115ca0859c0db8415d73b3a22626506cbbe000000000000000000000000000000000000000000000000000000000098968000000000000000000000000000000000000000000000000000000000000ad4a400000000000000000000000000000000000000000000000000000000000001400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000891cdb91d149f23b1a45d9c5ca78a88d0cb44c180000000000000000000000000000000000000000000000000000000000000003776562000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002e000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f90a4115ca0859c0db8415d73b3a22626506cbbe00000000000000000000000000000000000000000000000000000000000000e00000000000000000000000000000000000000000000000000000000000000120000000000000000000000000000000000000000000000000000000000000016000000000000000000000000000000000000000000000000000000000000001a000000000000000000000000000000000000000000000000000000000000001e000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000982150000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000ec5e8661f8855b1724d4f668f11ab722b4b3b74d00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000064ce22f85800000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000064df3808000000000000000000000000f90a4115ca0859c0db8415d73b3a22626506cbbe00000000000000000000000000000000000000000000000000000000\",\n" +
                "\t\t\t\t\t\t\"owner_address\": \"41f90a4115ca0859c0db8415d73b3a22626506cbbe\",\n" +
                "\t\t\t\t\t\t\"contract_address\": \"416c37365d9f04aa507c019e91849a6c61584c9ef2\",\n" +
                "\t\t\t\t\t\t\"call_value\": 10000000\n" +
                "\t\t\t\t\t},\n" +
                "\t\t\t\t\t\"type_url\": \"type.googleapis.com/protocol.TriggerSmartContract\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t\"type\": \"TriggerSmartContract\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"ref_block_bytes\": \"9571\",\n" +
                "\t\t\t\"ref_block_hash\": \"cde1f067d8565a7e\",\n" +
                "\t\t\t\"expiration\": 1692349329000,\n" +
                "\t\t\t\"fee_limit\": 150000000,\n" +
                "\t\t\t\"timestamp\": 1692349272590\n" +
                "\t\t},\n" +
                "\t\t\"raw_data_hex\": " +
                "\"0a0295712208cde1f067d8565a7e40e8dc85bfa0315a950b081f12900b0a31747970652e676f6f676c65617069732e636f6d2f70726f746f636f6c2e54726967676572536d617274436f6e747261637412da0a0a1541f90a4115ca0859c0db8415d73b3a22626506cbbe1215416c37365d9f04aa507c019e91849a6c61584c9ef21880ade20422a40ac10bea5c000000000000000000000000000000000000000000000000000000000000004000000000000000000000000000000000000000000000000000000000000001c0000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000003487b63d30b5b2c87fb7ffa8bcfade38eaac1abe00000000000000000000000087d5ef4c42a78f85dd1ca399fa0c99f9ab0875d9000000000000000000000000f90a4115ca0859c0db8415d73b3a22626506cbbe000000000000000000000000000000000000000000000000000000000098968000000000000000000000000000000000000000000000000000000000000ad4a400000000000000000000000000000000000000000000000000000000000001400000000000000000000000000000000000000000000000000000000000000000000000000000000000000000891cdb91d149f23b1a45d9c5ca78a88d0cb44c180000000000000000000000000000000000000000000000000000000000000003776562000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000006000000000000000000000000000000000000000000000000000000000000002e000000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000000000000000000000000000000f90a4115ca0859c0db8415d73b3a22626506cbbe00000000000000000000000000000000000000000000000000000000000000e00000000000000000000000000000000000000000000000000000000000000120000000000000000000000000000000000000000000000000000000000000016000000000000000000000000000000000000000000000000000000000000001a000000000000000000000000000000000000000000000000000000000000001e000000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000982150000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000001000000000000000000000000ec5e8661f8855b1724d4f668f11ab722b4b3b74d00000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000100000000000000000000000000000000000000000000000000000000000000200000000000000000000000000000000000000000000000000000000000000064ce22f85800000000000000000000000000000000000000000000000000000000000000010000000000000000000000000000000000000000000000000000000064df3808000000000000000000000000f90a4115ca0859c0db8415d73b3a22626506cbbe00000000000000000000000000000000000000000000000000000000708ea482bfa031900180a3c347\"\n" +
                "\t}");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        transaction.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().pushTransaction(this, transaction, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //转账成功后，会返回相应的交易hash，注意，钱包只是把交易push出去，并不能保证最后交易结果，开发者需要根据hash自行确定链上结果
                Toast.makeText(TronDemoActivity.this, s, Toast.LENGTH_LONG).show();
                Log.e("pushTransaction", s);
            }

            @Override
            public void onError(String s) {
                Toast.makeText(TronDemoActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(TronDemoActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }
}
