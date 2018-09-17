package com.tokenpocket.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.tokenpocket.opensdk.TPListener;
import com.tokenpocket.opensdk.TPManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //transfer
        findViewById(R.id.tv_transfer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TPManager.getInstance().transfer(MainActivity.this, getTransferData(), new TPListener() {
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

        //pushTransaction
        findViewById(R.id.tv_push_transaction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TPManager.getInstance().pushTransaction(MainActivity.this, getPushTransactionData(), new TPListener() {
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

        //authLogin
        findViewById(R.id.tv_auth_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TPManager.getInstance().authLogin(MainActivity.this, getAuthLoginData(), new TPListener() {
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
     * 按照协议构建json 字符串
     */
    private String getTransferData() {
        return "{\n" +
                "\t\"protocol\": \"TokenPocket\",\n" +
                "\t\"version\": \"1.0\",\n" +
                "\t\"dappName\": \"Newdex\",\n" +
                "\t\"dappIcon\": \"https://newdex.io/static/logoicon.png\",\n" +
                "\t\"action\": \"transfer\",\n" +
                "\t\"from\": \"clement22222\",\n" +
                "\t\"to\": \"newdexpocket\",\n" +
                "\t\"amount\": 0.11,\n" +
                "\t\"contract\": \"eosio.token\",\n" +
                "\t\"symbol\": \"EOS\",\n" +
                "\t\"precision\": 4,\n" +
                "\t\"dappData\": \"{\\\"type\\\":\\\"buy-limit\\\",\\\"symbol\\\":\\\"IQ_EOS\\\",\\\"price\\\":\\\"0.00220\\\",\\\"count\\\":50,\\\"amount\\\":0.11}\",\n" +
                "\t\"desc\": \"\",\n" +
                "\t\"expired\": 1535944144181,\n" +
                "\t\"callback\": \"https://newdex.io/api/account/transferCallback?uuid=1-46e023fc-015b-4b76-3809-1cab3fd76d2c&type=SimpleWallet\"\n" +
                "}";
    }

    private String getPushTransactionData() {
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

    private String getAuthLoginData() {
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

}
