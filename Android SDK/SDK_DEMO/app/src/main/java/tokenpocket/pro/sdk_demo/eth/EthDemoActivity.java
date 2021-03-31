package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.innerwallet.model.LinkAction;
import com.tokenpocket.opensdk.simple.model.Authorize;
import com.tokenpocket.opensdk.simple.model.Signature;
import com.tokenpocket.opensdk.simple.model.Transaction;
import com.tokenpocket.opensdk.simple.model.Transfer;

import java.util.ArrayList;

import tokenpocket.pro.sdk_demo.R;
import tokenpocket.pro.sdk_demo.tron.TronDemoActivity;

/**
 * Created by duke on 2019/7/9.
 */

public class EthDemoActivity extends Activity implements View.OnClickListener {

    private Button btnAuthorize;
    private Button btnSign;
    private Button btnPersonalSign;
    private Button btnTransfer;
    private Button btnPushTransaction;
    private EditText etSign;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_demo);

        btnAuthorize = findViewById(R.id.btn_authorize);
        btnSign = findViewById(R.id.btn_sign);
        btnPersonalSign = findViewById(R.id.btn_personsign);
        btnTransfer = findViewById(R.id.btn_transfer);
        btnPushTransaction = findViewById(R.id.btn_pushtx);
        etSign = findViewById(R.id.et_sign);

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
                sign();
                break;
            case R.id.btn_personsign:
                personalSign();
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
        authorize.setBlockchain("ETH");
        authorize.setProtocol("TokenPocket");
        authorize.setVersion("1.0");
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

    private void sign() {
        Signature signature = new Signature();
        signature.setBlockchain("ETH");
        signature.setProtocol("TokenPocket");
        signature.setVersion("1.0");
        signature.setDappName("Test demo");
        signature.setDappIcon("https://eosknights.io/img/icon.png");
        signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        signature.setMemo("demo");
        signature.setSignType("ethSign");
        signature.setMessage(etSign.getText().toString());
        TPManager.getInstance().signature(this, signature, new TPListener() {
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

    private void personalSign() {
        Signature signature = new Signature();
        signature.setBlockchain("ETH");
        signature.setProtocol("TokenPocket");
        signature.setVersion("1.0");
        signature.setDappName("Test demo");
        signature.setDappIcon("https://eosknights.io/img/icon.png");
        signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        signature.setMemo("demo");
        signature.setSignType("ethPersonalSign");
        signature.setMessage(etSign.getText().toString());
        TPManager.getInstance().signature(this, signature, new TPListener() {
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

    private void transfer() {
        Intent intent = new Intent(this, EthTransferActivity.class);
        startActivity(intent);
//        Transfer transfer = new Transfer();
//        transfer.setBlockchain("ETH");
//        transfer.setProtocol("TokenPocket");
//        transfer.setVersion("1.0");
//        transfer.setDappName("Test demo");
//        transfer.setDappIcon("https://eosknights.io/img/icon.png");
//        transfer.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
//        transfer.setMemo("demo");
//        transfer.setAction("transfer");
//        transfer.setFrom("0x40e5A542087FA4b966209707177b103d158Fd3A4");
//        transfer.setTo("0x171a0b081493722A5fB8Ebe6F0c4aDf5fde49BD8");
//        transfer.setPrecision(4);
//        transfer.setContract("");
//        transfer.setAmount(0.00012);
//        transfer.setSymbol("ETH");
//        transfer.setDesc("");
//        TPManager.getInstance().transfer(this, transfer, new TPListener() {
//            @Override
//            public void onSuccess(String s) {
//                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onError(String s) {
//                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();
//
//            }
//
//            @Override
//            public void onCancel(String s) {
//                Toast.makeText(EthDemoActivity.this, s, Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    private void pushTx() {

        Transaction transaction = new Transaction();
        transaction.setBlockchain("ETH");
        transaction.setProtocol("TokenPocket");
        transaction.setVersion("1.0");
        transaction.setDappName("Test demo");
        transaction.setDappIcon("https://eosknights.io/img/icon.png");
        transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transaction.setAction("pushTransaction");
        transaction.setLinkActions(new ArrayList<LinkAction>());
        transaction.setTxData("{\"from\":\"0x22F4900A1fB41f751b8F616832643224606B75B4\",\"gasPrice\":\"0x6c088e200\",\"gas\":\"0xea60\",\"chainId\":\"1\",\"to\":\"0x7d1e7fb353be75669c53c18ded2abcb8c4793d80\",\"data\":\"0xa9059cbb000000000000000000000000171a0b081493722a5fb8ebe6f0c4adf5fde49bd8000000000000000000000000000000000000000000000000000000000012c4b0\"}");
        TPManager.getInstance().pushTransaction(this, transaction, new TPListener() {
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
}
