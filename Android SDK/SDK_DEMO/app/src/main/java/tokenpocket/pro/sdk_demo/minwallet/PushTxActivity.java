package tokenpocket.pro.sdk_demo.minwallet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
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
        etTransactions.setText(actions);
        findViewById(R.id.btn_pushtx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pushTx();
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
            "              \"actor\": \"ljxlzdh54321\",\n" +
            "              \"permission\": \"testtransfer\"\n" +
            "            }\n" +
            "          ],\n" +
            "          \"data\": {\n" +
            "            \"from\": \"ljxlzdh54321\",\n" +
            "            \"memo\": \"ddd\",\n" +
            "            \"quantity\": \"0.0100 TPT\",\n" +
            "            \"to\": \"clementtes51\"\n" +
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


//    private void pushTx() {
//        final Transaction transaction = getTxData();
//        //首先通过接口判断，本次执行的操作需要的权限，是否已经在账号中，这个链接的过程可以参考账号授权
//        TPManager.getInstance().isPermLinkAction(this, transaction, new TPListener() {
//            @Override
//            public void onSuccess(String data) {
//                //如果本次操作已经通过miniwallet授权，则将操作的参数修改为开发者自己定义的权限组，在demo实例中，
//                //我们自定义的权限组是testtransfer,详情可参加授权AuthActivity.java
//                replacePerm(transaction, transaction.getPerm());
//            }
//
//            @Override
//            public void onError(String data) {
//                //如果当前操作的权限组没有link到账号，则miniwallet无法完成当前操作，仍然需要拉起钱包授权
//                replacePerm(transaction, "active");
//            }
//
//            @Override
//            public void onCancel(String data) {
//
//            }
//        });
//    }

//    /**
//     *
//     * @param transaction
//     * @param permission
//     */
//    private void replacePerm(Transaction transaction, String permission) {
//        Json actions = new Json(transaction.getActions());
//        for (int i = 0; i < actions.getLength(); i++) {
//            Json authorization = actions.getObject(i, "{}").getArray("authorization", "[]")
//                    .getObject(0, "{}");
//            authorization.putString("permission", permission);
//        }
//        transaction.setActions(actions.toString());
//        //将操作的permission替换成自定义的权限组后，就可以直接在miniwallet中完成操作，而不需要拉起tp 钱包授权
//        realPushTx(transaction);
//    }

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
