package tokenpocket.pro.sdk_demo.minwallet;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.net.custom.Json;
import com.tokenpocket.opensdk.simple.model.Transaction;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/6/26.
 * if you want to use min wallet push transaction, please read the tips carefully
 * 1 call TPManager.getInstance.auth first
 * 2 when push transaction, if you want to do tranasction in your app without pull up tokenpocket,
 * you should set the permission in authorization to the value which used in auth method.
 * then call TPManager.getInstance().isPermiLinkAction. if get success callback, just call TPManager.getInstance().pushTransaction is ok.
 * and if get error callback, replace the permission to active or owner so that can pull up TokenPocket do this transaction.
 * 3 read the demo carefully to get how to use it
 */

public class PushTxActivity extends Activity {
    private EditText etTransactions;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pushtx);
        etTransactions = findViewById(R.id.et_actions);
        findViewById(R.id.btn_pushtx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushTx();
            }
        });
    }

    //test data
    String actions = "[\n" +
            "        {\n" +
            "          \"account\": \"eosiotptoken\",\n" +
            "          \"name\": \"transfer\",\n" +
            "          \"authorization\": [\n" +
            "            {\n" +
            "              \"actor\": \"sldjdljklf12\",\n" +
            "              \"permission\": \"testtransfer\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"data\": {\n" +
            "            \"from\": \"sldjdljklf12\",\n" +
            "            \"memo\": \"ddd\",\n" +
            "            \"quantity\": \"0.0100 TPT\",\n" +
            "            \"to\": \"clement21212\"\n" +
            "          }\n" +
            "        }\n" +
            "      ]";

    private Transaction getTxData() {
        Transaction transaction = new Transaction();
        transaction.setActions(etTransactions.getText().toString());
        transaction.setAction("pushTransaction");
        transaction.setBlockchain("EOS");
        return transaction;
    }


    private void pushTx() {
        final Transaction transaction = getTxData();
        TPManager.getInstance().isPermLinkAction(this, transaction, new TPListener() {
            @Override
            public void onSuccess(String data) {
                replacePerm(transaction, transaction.getPerm());
            }

            @Override
            public void onError(String data) {
                replacePerm(transaction, "active");
            }

            @Override
            public void onCancel(String data) {

            }
        });
    }

    /**
     *
     * @param transaction
     * @param permission
     */
    private void replacePerm(Transaction transaction, String permission) {
        Json actions = new Json(transaction.getActions());
        for (int i = 0; i < actions.getLength(); i++) {
            Json authorization = actions.getObject(i, "{}").getArray("authorization", "[]")
                    .getObject(0, "{}");
            authorization.putString("permission", permission);
        }
        transaction.setActions(actions.toString());
        realPushTx(transaction);
    }

    private void realPushTx(Transaction transaction) {
        TPManager.getInstance().pushTransaction(PushTxActivity.this, transaction, new TPListener() {
            @Override
            public void onSuccess(String data) {
                Toast.makeText(PushTxActivity.this, data, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(String data) {
                Toast.makeText(PushTxActivity.this, data, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String data) {
                Toast.makeText(PushTxActivity.this, data, Toast.LENGTH_LONG).show();

            }
        });
    }

}
