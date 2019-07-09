package tokenpocket.pro.sdk_demo.eos;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Authorize;
import com.tokenpocket.opensdk.simple.model.Signature;
import com.tokenpocket.opensdk.simple.model.Transaction;
import com.tokenpocket.opensdk.simple.model.Transfer;

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
        authorize.setBlockchain("EOS");
        authorize.setProtocol("TokenPocket");
        authorize.setVersion("1.0");
        authorize.setDappName("Test demo");
        authorize.setDappIcon("https://eosknights.io/img/icon.png");
        authorize.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        authorize.setMemo("demo");
        authorize.setAction("login");
        TPManager.getInstance().authorize(this, authorize, new TPListener() {
            @Override
            public void onSuccess(String s) {

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
        signature.setBlockchain("EOS");
        signature.setProtocol("TokenPocket");
        signature.setVersion("1.0");
        signature.setDappName("Test demo");
        signature.setDappIcon("https://eosknights.io/img/icon.png");
        signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        signature.setMemo("demo");
        signature.setAction("sign");
        signature.setMessage("message to sign");
        TPManager.getInstance().signature(this, signature, new TPListener() {
            @Override
            public void onSuccess(String s) {

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
        transfer.setBlockchain("EOS");
        transfer.setProtocol("TokenPocket");
        transfer.setVersion("1.0");
        transfer.setDappName("Test demo");
        transfer.setDappIcon("https://eosknights.io/img/icon.png");
        transfer.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transfer.setMemo("demo");
        transfer.setAction("transfer");
        transfer.setFrom("xljsdljf1234");
        transfer.setTo("xl123ljf1234");
        transfer.setPrecision(4);
        transfer.setContract("eosio.token");
        transfer.setAmount(0.1);
        transfer.setSymbol("EOS");
        transfer.setDesc("");
        TPManager.getInstance().transfer(this, transfer, new TPListener() {
            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(String s) {

            }

            @Override
            public void onCancel(String s) {

            }
        });
    }

    private void pushTx() {
        Transaction transaction = new Transaction();
        transaction.setBlockchain("EOS");
        transaction.setProtocol("TokenPocket");
        transaction.setVersion("1.0");
        transaction.setDappName("Test demo");
        transaction.setDappIcon("https://eosknights.io/img/icon.png");
        transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transaction.setAction("pushTransaction");
        transaction.setActions("[\n" +
                "        {\n" +
                "          \"account\": \"eosio.token\",\n" +
                "          \"name\": \"transfer\",\n" +
                "          \"authorization\": [\n" +
                "            {\n" +
                "              \"actor\": \"xljsdljf1234\",\n" +
                "              \"permission\": \"active\"\n" +
                "            }\n" +
                "          ],\n" +
                "          \"data\": {\n" +
                "            \"from\": \"xljsdljf1234\",\n" +
                "            \"memo\": \"\",\n" +
                "            \"quantity\": \"0.0001 EOS\",\n" +
                "            \"to\": \"xl123ljf1234\"\n" +
                "          },\n" +
                "        }\n" +
                "      ]");
        TPManager.getInstance().pushTransaction(this, transaction, new TPListener() {
            @Override
            public void onSuccess(String s) {

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
