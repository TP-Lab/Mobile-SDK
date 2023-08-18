package tokenpocket.pro.sdk_demo.iost;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

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
        //开发者自己定义的业务id，用来标识本次操作,授权操作一定要设置这个参数
        authorize.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        authorize.setAction("login");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        authorize.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().authorize(this, authorize, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //登录成功后，会返回登录的钱包地址，签名信息，开发者可以利用这两个参数验证有效性
                Toast.makeText(IostDemoActivity.this, s, Toast.LENGTH_LONG).show();
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
                Toast.makeText(IostDemoActivity.this, s, Toast.LENGTH_LONG).show();
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
        //开发者自己定义的业务id，用来标识本次操作
        transfer.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transfer.setMemo("demo");
        transfer.setAction("transfer");
        transfer.setFrom("mingyi1");
        transfer.setTo("_bbbb");
        //代币精度，一定要准确
        transfer.setPrecision(4);
        transfer.setContract("token.iost");
        transfer.setAmount(0.001);
        transfer.setSymbol("IOST");
        //仅供UI展示，不上链
        transfer.setDesc("");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        transfer.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().transfer(this, transfer, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //转账成功后，会返回相应的交易hash，注意，钱包只是把交易push出去，并不能保证最后交易结果，开发者需要根据hash自行确定链上结果
                Toast.makeText(IostDemoActivity.this, s, Toast.LENGTH_LONG).show();
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
     * 通用合约操作，下面以投票为实例展示
     */
    private void pushTx() {
        Transaction transaction = new Transaction();
        transaction.setBlockchain("IOST");
        transaction.setProtocol("TokenPocket");
        transaction.setVersion("1.0");
        transaction.setDappName("Test demo");
        transaction.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务id，用来标识本次操作
        transaction.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        transaction.setAction("pushTransaction");
        //iost中，pushTx操作实际上是对构造好的交易对象进行签名，要求的参数和iost插件钱包签名数据格式一样，
        // 具体生成方式，可以参照iost开发文档:https://developers.iost.io/docs/zh-CN/1-getting-started/Overview.html
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        transaction.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        transaction.setPayload("{\n" +
                "\t\t\"tx\": {\n" +
                "\t\t\t\"gasRatio\": 1,\n" +
                "\t\t\t\"gasLimit\": 2000000,\n" +
                "\t\t\t\"actions\": [{\n" +
                "\t\t\t\t\"contract\": \"ContractF3tLtxdXwYmKsDiUtTmaQztwJQLPVf9VyWDqufMZHP5p\"," +
                "\n" +
                "\t\t\t\t\"actionName\": \"vote\",\n" +
                "\t\t\t\t\"data\": \"[\\\"mingyi\\\",\\\"tokenpocket\\\",\\\"0.01\\\"]\"\n" +
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
                "\t\t\t\"time\": 1562733927307045600,\n" +
                "\t\t\t\"expiration\": 1562734017307045600,\n" +
                "\t\t\t\"delay\": 0\n" +
                "\t\t},\n" +
                "\t\t\"domain\": \"tool.l6iebi.com\",\n" +
                "\t\t\"account\": \"mingyi\",\n" +
                "\t\t\"network\": \"MAINNET\",\n" +
                "\t\t\"txABI\": [\"ContractF3tLtxdXwYmKsDiUtTmaQztwJQLPVf9VyWDqufMZHP5p\", " +
                "\"vote\", [\"mingyi\", \"tokenpocket\", \"0.01\"]]\n" +
                "\t}");
        TPManager.getInstance().pushTransaction(this, transaction, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //转账成功后，会返回相应的交易hash，注意，钱包只是把交易push出去，并不能保证最后交易结果，开发者需要根据hash自行确定链上结果
                Toast.makeText(IostDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(IostDemoActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancel(String s) {

            }
        });
    }
}
