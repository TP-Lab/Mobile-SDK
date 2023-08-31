package tokenpocket.pro.sdk_demo.eth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.simple.model.Blockchain;
import com.tokenpocket.opensdk.simple.model.Transfer;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/9/18.
 */

public class EthTransferActivity extends Activity implements View.OnClickListener {

    private Button btnTransfer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eth_transfer);

        btnTransfer = findViewById(R.id.btn_transfer);

        btnTransfer.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_transfer:
                transfer();
                break;
        }
    }


    /**
     * 这里以BSC上转账实例，说明各个参数
     */
    private void transfer() {
        Transfer transfer = new Transfer();
        //标识链
        List<Blockchain> blockchains = new ArrayList<>();
        //evm系列，第一个参数是ethereum,第二个参数是网络的id，这里56是BSC网络链上id
        blockchains.add(new Blockchain("ethereum", "1"));
        transfer.setBlockchains(blockchains);

        transfer.setProtocol("TokenPocket");
        transfer.setVersion("1.0");
        transfer.setDappName("Test demo");
        transfer.setDappIcon("https://eosknights.io/img/icon.png");
        //开发者自己定义的业务Id,用来标识这次操作
        transfer.setActionId("web-db4c5466-1a03-438c-90c9-2172e8becea5");
        //发送者
        transfer.setFrom("0x5Da73693A062a11589F1b5c68434bf7eAff72366");
        //接受者
        transfer.setTo("0x0Dd3758c88316723eC434C54BF3e56e733785DFE");
        //代币合约地址，如果是转原生币，不需要设置这个参数
//        transfer.setContract("0x55d398326f99059ff775485246999027b3197955");
        //如果是转原生币，可以添加上链数据
//        transfer.setMemo("0xe595a6");
        //转账数量，比如这里demo是转0.01个USDT，就是传入0.01
        transfer.setAmount(0.0001);
        //必须设置
        transfer.setDecimal(18);
        transfer.setSymbol("USDT");
        transfer.setDesc("仅供ui展示，不上链");
        //开发者服务端提供的接受调用登录结果的接口，如果设置该参数，钱包操作完成后，会将结果通过post application json方式将结果回调给callbackurl
        transfer.setCallbackUrl("http://115.205.0.178:9011/taaBizApi/taaInitData");
        TPManager.getInstance().transfer(this, transfer, new TPListener() {
            @Override
            public void onSuccess(String s) {
                //转账操作结果，注意，这里只是将交易发送后的hash返回，并不保证交易一定成功，需要开发者根据hash自行确认最终链上结果
                Toast.makeText(EthTransferActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(String s) {
                Toast.makeText(EthTransferActivity.this, s, Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancel(String s) {
                Toast.makeText(EthTransferActivity.this, s, Toast.LENGTH_LONG).show();

            }
        });
    }


    public static void start(Context context) {
        Intent intent = new Intent(context, EthTransferActivity.class);
        context.startActivity(intent);

    }
}
