package com.tokenpocket.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tokenpocket.opensdk.simple.TPListener;
import com.tokenpocket.opensdk.simple.TPManager;
import com.tokenpocket.opensdk.simple.model.Authorize;
import com.tokenpocket.opensdk.simple.model.Signature;
import com.tokenpocket.opensdk.simple.model.Transaction;
import com.tokenpocket.opensdk.simple.model.Transfer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Transfer
        findViewById(R.id.tv_transfer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TPManager.getInstance().transfer(MainActivity.this, getTransfer(), new TPListener() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //Transaction
        findViewById(R.id.tv_push_transaction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TPManager.getInstance().pushTransaction(MainActivity.this, getTransaction(), new TPListener() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //Authorize
        findViewById(R.id.tv_auth_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TPManager.getInstance().authorize(MainActivity.this, getAuthorize(), new TPListener() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        //Signature
        findViewById(R.id.tv_sign).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TPManager.getInstance().signature(MainActivity.this, getSignature(), new TPListener() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(String data) {
                        Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 按照协议构建对象 Transfer
     */
    private Transfer getTransfer() {
        Transfer transfer = new Transfer();
        transfer.setBlockchain("EOS");
        transfer.setDappName("Newdex");
        transfer.setDappIcon("https://newdex.io/static/logoicon.png");
        transfer.setFrom("clement11111");
        transfer.setTo("newdexpocket");
        transfer.setAmount(0.0001);
        transfer.setContract("eosio.token");
        transfer.setSymbol("EOS");
        transfer.setPrecision(4);
        transfer.setMemo("test");
        transfer.setExpired(1535944144L);
        transfer.setCallbackUrl("https://newdex.io/api/account/transferCallback?uuid=1-46e023fc-015b-4b76-3809-1cab3fd76d2c");

        return transfer;
    }

    /**
     * 按照协议构建对象 Transaction
     */
    private Transaction getTransaction() {
        Transaction transaction = new Transaction();
        transaction.setBlockchain("EOS");
        transaction.setDappName("Test Name");
        transaction.setDappIcon("https://newdex.io/static/logoicon.png");
        transaction.setActions("[{\n" +
                "\"account\": \"eosio.token\",\n" +
                "\"name\": \"transfer\",\n" +
                "\"authorization\": [{\n" +
                "\"actor\": \"clement11111\",\n" +
                "\"permission\": \"active\"\n" +
                "}],\n" +
                "\"data\": {\n" +
                "\"from\": \"clement11111\",\n" +
                "\"to\": \"clement22222\",\n" +
                "\"quantity\": \"0.0001 EOS\",\n" +
                "\"memo\": \"jlsdjlsdjf\"\n" +
                "}\n" +
                "}]");
        transaction.setExpired(10000000000L);

        return transaction;
    }

    /**
     * 按照协议构建对象 Authorize
     */
    private Authorize getAuthorize() {
        Authorize authorize = new Authorize();
        authorize.setBlockchain("EOS");
        authorize.setDappName("Newdex");
        authorize.setDappIcon("https://newdex.io/static/logoicon.png");
        authorize.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");
        authorize.setCallbackUrl("https://newdex.io/api/account/walletVerify");
        authorize.setExpired(1537157808L);
        authorize.setMemo("The first gobal decentralized exchange built on EOS");

        return authorize;
    }

    /**
     * 按照协议构建对象 Signature
     */
    private Signature getSignature() {
        Signature signature = new Signature();
        signature.setBlockchain("EOS");
        signature.setDappName("Newdex");
        signature.setDappIcon("https://newdex.io/static/logoicon.png");
        signature.setActionId("web-99784c28-70f0-49ff-3654-f27b137b3502");
        signature.setCallbackUrl("https://newdex.io/api/account/walletVerify");
        signature.setExpired(1537157808L);
        signature.setMemo("The first gobal decentralized exchange built on EOS");
        signature.setMessage("hello");
        return signature;
    }


    /**
     * 按照协议构建json 字符串(旧版本)
     */
    @Deprecated
    private String getTransferString() {
        return "{\n" +
                "\t\"protocol\": \"TokenPocket\",\n" +
                "\t\"version\": \"1.0\",\n" +
                "\t\"dappName\": \"Newdex\",\n" +
                "\t\"dappIcon\": \"https://newdex.io/static/logoicon.png\",\n" +
                "\t\"action\": \"transfer\",\n" +
                "\t\"from\": \"clement22222\",\n" +
                "\t\"to\": \"newdexpocket\",\n" +
                "\t\"amount\": 0.0001,\n" +
                "\t\"contract\": \"eosio.token\",\n" +
                "\t\"symbol\": \"EOS\",\n" +
                "\t\"precision\": 4,\n" +
                "\t\"dappData\": \"{\\\"type\\\":\\\"buy-limit\\\",\\\"symbol\\\":\\\"IQ_EOS\\\",\\\"price\\\":\\\"0.00220\\\",\\\"count\\\":50,\\\"amount\\\":0.11}\",\n" +
                "\t\"desc\": \"\",\n" +
                "\t\"expired\": 1535944144181,\n" +
                "\t\"callback\": \"https://newdex.io/api/account/transferCallback?uuid=1-46e023fc-015b-4b76-3809-1cab3fd76d2c\"\n" +
                "}";
    }

    /**
     * 按照协议构建json 字符串(旧版本)
     */
    @Deprecated
    private String getTransactionString() {
        return "{\n" +
                "\t\"dappName\": \"test\",\n" +
                "\t\"dappIcon\": \"https://newdex.io/static/logoicon.png\",\n" +
                "\t\"action\": \"pushTransaction\",\n" +
                "\t\"actions\": [{\n" +
                "\t\t\"account\": \"eosio.token\",\n" +
                "\t\t\"name\": \"transfer\",\n" +
                "\t\t\"authorization\": [{\n" +
                "\t\t\t\"actor\": \"xiaoyuantest\",\n" +
                "\t\t\t\"permission\": \"active\"\n" +
                "\t\t}],\n" +
                "\t\t\"data\": {\n" +
                "\t\t\t\"from\": \"xiaoyuantest\",\n" +
                "\t\t\t\"to\": \"clement22222\",\n" +
                "\t\t\t\"quantity\": \"0.0001 EOS\",\n" +
                "\t\t\t\"memo\": \"jlsdjlsdjf\"\n" +
                "\t\t}\n" +
                "\t}],\n" +
                "\t\"expired\": \"10000000000000\"\n" +
                "}";
    }

    /**
     * 按照协议构建json 字符串(旧版本)
     */
    @Deprecated
    private String getAuthorizeString() {
        return "{\n" +
                " \"protocol\": \"TokenPocket\",\n" +
                " \"version\": \"1.0\",\n" +
                " \"dappName\": \"Newdex\",\n" +
                " \"dappIcon\": \"https://newdex.io/static/logoicon.png\",\n" +
                " \"action\": \"login\",\n" +
                " \"actionId\": \"web-99784c28-70f0-49ff-3654-f27b137b3502\",\n" +
                " \"callbackUrl\": \"https://newdex.io/api/account/walletVerify\",\n" +
                " \"expired\": 1537157808,\n" +
                " \"memo\": \"The first gobal decentralized exchange built on EOS\"\n" +
                "}";
    }

    /**
     * 按照协议构建json 字符串(旧版本)
     */
    @Deprecated
    private String getSignatureString() {
        return "{\n" +
                "   \"protocol\": \"TokenPocket\",\n" +
                "    \"version\": \"1.0\",\n" +
                "    \"dappName\": \"Newdex\",\n" +
                "    \"dappIcon\": \"https://newdex.io/static/logoicon.png\",\n" +
                "    \"action\": \"signature\",\n" +
                "    \"actionId\": \"web-99784c28-70f0-49ff-3654-f27b137b3502\",\n" +
                "    \"callbackUrl\": \"https://newdex.io/api/account/walletVerify\",\n" +
                "    \"expired\": 1537157808,\n" +
                "    \"memo\": \"The first gobal decentralized exchange built on EOS\",\n" +
                "    \"message\":\"hello\"\n" +
                "}";
    }

}
