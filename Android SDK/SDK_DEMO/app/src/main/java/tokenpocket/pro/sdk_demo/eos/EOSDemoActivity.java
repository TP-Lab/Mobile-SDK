package tokenpocket.pro.sdk_demo.eos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Authorize;
import com.tokenpocket.opensdk.simple.model.Blockchain;
import com.tokenpocket.opensdk.simple.model.Signature;
import com.tokenpocket.opensdk.simple.model.Transaction;
import com.tokenpocket.opensdk.simple.model.Transfer;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/7/9.
 */

public class EOSDemoActivity extends Activity implements View.OnClickListener {

    private Button btnAuthorize;
    private Button btnSign;
    private Button btnTransfer;
    private Button btnPushTransaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);

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
        List blockchains = new ArrayList();
        //blockchains指定可以用哪些网络的钱包操作，eosios系列，第一个参数是eosio,第二个参数是网络的id
        blockchains.add(new Blockchain("eosio",
                "aca376f206b8fc25a6ed44dbdc66547c36c6c33e3a119ffbeaef943642f0e906"));
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
                Toast.makeText(EOSDemoActivity.this, s, Toast.LENGTH_LONG).show();
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
        List blockchains = new ArrayList();
        //blockchains指定可以用哪些网络的钱包操作，eosios系列，第一个参数是eosio,第二个参数是网络的id
        blockchains.add(new Blockchain("eosio",
                "aca376f206b8fc25a6ed44dbdc66547c36c6c33e3a119ffbeaef943642f0e906"));
        signature.setBlockchains(blockchains);
        signature.setProtocol("TokenPocket");
        signature.setVersion("1.0");
        signature.setDappName("Test demo");
        signature.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务id，用来标识本次操作
        signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        signature.setAction("sign");
        //待签名的字符串
        signature.setMessage("message to sign");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        signature.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().signature(this, signature, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //签名完成后，会返回签名的钱包信息和签名信息
                Toast.makeText(EOSDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {

            }

            @Override
            public void onCancel(String s) {

            }
        });
    }

    private void transfer() {
        Transfer transfer = new Transfer();
        List blockchains = new ArrayList();
        //blockchains指定可以用哪些网络的钱包操作，eosios系列，第一个参数是eosio,第二个参数是网络的id
        blockchains.add(new Blockchain("eosio",
                "aca376f206b8fc25a6ed44dbdc66547c36c6c33e3a119ffbeaef943642f0e906"));
        transfer.setBlockchains(blockchains);
        transfer.setProtocol("TokenPocket");
        transfer.setVersion("1.0");
        transfer.setDappName("Test demo");
        transfer.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务id，用来标识本次操作
        transfer.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transfer.setMemo("demo");
        transfer.setAction("transfer");
        transfer.setFrom("testaccount1");
        transfer.setTo("testaccount2");
        //代币精度，一定要正确，否则代币会转账失败
        transfer.setPrecision(4);
        //代币合约
        transfer.setContract("eosio.token");
        //转账数量
        transfer.setAmount(0.0001);
        transfer.setSymbol("EOS");
        //仅供UI展示
        transfer.setDesc("");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        transfer.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().transfer(this, transfer, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //转账成功后，会返回相应的交易hash，注意，钱包只是把交易push出去，并不能保证最后交易结果，开发者需要根据hash自行确定链上结果
                Toast.makeText(EOSDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {

            }

            @Override
            public void onCancel(String s) {

            }
        });
    }

    /**
     * 通用合约操作，下面以转账为实例演示
     */
    private void pushTx() {
        Transaction transaction = new Transaction();
        List blockchains = new ArrayList();
        //blockchains指定可以用哪些网络的钱包操作，eosios系列，第一个参数是eosio,第二个参数是网络的id
        blockchains.add(new Blockchain("eosio",
                "aca376f206b8fc25a6ed44dbdc66547c36c6c33e3a119ffbeaef943642f0e906"));
        transaction.setBlockchains(blockchains);
        transaction.setProtocol("TokenPocket");
        transaction.setVersion("1.0");
        transaction.setDappName("Test demo");
        transaction.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务id，用来标识本次操作
        transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transaction.setAction("pushTransaction");
        //合约操作内容
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        transaction.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        transaction.setActions("[\n" +
                "        {\n" +
                "          \"account\": \"eosio.token\",\n" +
                "          \"name\": \"transfer\",\n" +
                "          \"authorization\": [\n" +
                "            {\n" +
                "              \"actor\": \"testaccount1\",\n" +
                "              \"permission\": \"active\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"data\": {\n" +
                "            \"from\": \"testaccount1\",\n" +
                "            \"memo\": \"ddd\",\n" +
                "            \"quantity\": \"0.0001 EOS\",\n" +
                "            \"to\": \"testaccount2\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]");
        TPManager.getInstance().pushTransaction(this, transaction, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //转账成功后，会返回相应的交易hash，注意，钱包只是把交易push出去，并不能保证最后交易结果，开发者需要根据hash自行确定链上结果
                Toast.makeText(EOSDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {

            }

            @Override
            public void onCancel(String s) {

            }
        });
    }
}
