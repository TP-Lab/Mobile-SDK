package com.tokenpocket.sample;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tokenpocket.opensdk.TPListener;
import com.tokenpocket.opensdk.TPManager;

public class MainActivity extends Activity {

    private TextView tvTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        tvTips = findViewById(R.id.tv_tips);
        tvTips.setText("");

    }

    //通过包名跳转
    private void switch2TpByName() {
        Intent intent = new Intent();
        intent.setClassName("vip.mytokenpocket", "com.tokenbank.activity.export.ExportActivity");
        startActivity(intent);
    }

    //通过url跳转
    private void switch2TpByUrl() {
        Uri data = Uri.parse("tp://pull.activity");
        Intent intent = new Intent(Intent.ACTION_VIEW, data);
        //转账
        intent.putExtra("action", "transfer");
        intent.putExtra("data", getTransferData());
        //保证新启动的APP有单独的堆栈，如果希望新启动的APP和原有APP使用同一个堆栈则去掉该项
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "没有匹配的APP，请下载安装", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 按照以前的协议构建json 字符串
     *
     * @return
     */
    private String getTransferData() {
       return "{\n" +
               "\t\"protocol\": \"SimpleWallet\",\n" +
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
}
