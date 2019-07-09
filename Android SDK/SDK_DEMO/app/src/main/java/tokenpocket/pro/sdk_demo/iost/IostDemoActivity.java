package tokenpocket.pro.sdk_demo.iost;

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

public class IostDemoActivity extends Activity implements View.OnClickListener {

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
        authorize.setBlockchain("IOST");
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
        signature.setBlockchain("IOST");
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
        transfer.setBlockchain("IOST");
        transfer.setProtocol("TokenPocket");
        transfer.setVersion("1.0");
        transfer.setDappName("Test demo");
        transfer.setDappIcon("https://eosknights.io/img/icon.png");
        transfer.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transfer.setMemo("demo");
        transfer.setAction("transfer");
        transfer.setFrom("_bbbb");
        transfer.setTo("mingyi1");
        transfer.setPrecision(4);
        transfer.setContract("token.iost");
        transfer.setAmount(0.1);
        transfer.setSymbol("IOST");
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
        transaction.setBlockchain("IOST");
        transaction.setProtocol("TokenPocket");
        transaction.setVersion("1.0");
        transaction.setDappName("Test demo");
        transaction.setDappIcon("https://eosknights.io/img/icon.png");
        transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transaction.setAction("pushTransaction");
        transaction.setPayload("{\n" +
                "\t\t\"tx\": {\n" +
                "\t\t\t\"gasRatio\": 1,\n" +
                "\t\t\t\"gasLimit\": 2000000,\n" +
                "\t\t\t\"actions\": [{\n" +
                "\t\t\t\t\"contract\": \"ContractF3tLtxdXwYmKsDiUtTmaQztwJQLPVf9VyWDqufMZHP5p\",\n" +
                "\t\t\t\t\"actionName\": \"bet\",\n" +
                "\t\t\t\t\"data\": \"[\\\"_bbbb\\\",\\\"tokenpocket\\\",\\\"0.19\\\"]\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"signers\": [],\n" +
                "\t\t\t\"signatures\": [],\n" +
                "\t\t\t\"publisher\": \"\",\n" +
                "\t\t\t\"publisher_sigs\": [],\n" +
                "\t\t\t\"amount_limit\": [{\n" +
                "\t\t\t\t\"token\": \"*\",\n" +
                "\t\t\t\t\"value\": \"unlimited\"\n" +
                "\t\t\t}],\n" +
                "\t\t\t\"chain_id\": 1024,\n" +
                "\t\t\t\"reserved\": null,\n" +
                "\t\t\t\"time\": 1559296424058534000,\n" +
                "\t\t\t\"expiration\": 1559296514058534000,\n" +
                "\t\t\t\"delay\": 0\n" +
                "\t\t},\n" +
                "\t\t\"domain\": \"vote.liebi.com\",\n" +
                "\t\t\"account\": \"_bbbb\",\n" +
                "\t\t\"network\": \"MAINNET\",\n" +
                "\t\t\"txABI\": [\"ContractF3tLtxdXwYmKsDiUtTmaQztwJQLPVf9VyWDqufMZHP5p\", \"vote\", [\"_bbbb\", \"tokenpocket\", \"0.19\"]]\n" +
                "\t}");
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
