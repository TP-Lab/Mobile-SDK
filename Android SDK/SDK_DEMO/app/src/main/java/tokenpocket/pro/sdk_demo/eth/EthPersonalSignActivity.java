package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Blockchain;
import com.tokenpocket.opensdk.simple.model.Signature;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Author: tp-clement
 * Create: 4/8/21
 * Desc: eth签名
 */
public class EthPersonalSignActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_personal_sign);
        findViewById(R.id.tv_confirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ethPersonalSign();
            }
        });
    }

    private void ethPersonalSign() {
        Signature signature = new Signature();
        //标识链
        List<Blockchain> blockchains = new ArrayList<>();
        blockchains.add(new Blockchain("ethereum", "1"));
        signature.setBlockchains(blockchains);

        signature.setDappName("Test demo");
        signature.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者提供的业务id，用来标识本次操作
        signature.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        //签名类型
        signature.setSignType("ethPersonalSign");
        //待签名原文
        signature.setMessage("hello");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        signature.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().signature(this, signature, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //签名成功后，会返回签名后信息，签名的钱包
                Toast.makeText(EthPersonalSignActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(EthPersonalSignActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(EthPersonalSignActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, EthPersonalSignActivity.class);
        context.startActivity(intent);
    }
}
