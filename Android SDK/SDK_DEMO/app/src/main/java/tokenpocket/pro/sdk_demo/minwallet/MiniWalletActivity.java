package tokenpocket.pro.sdk_demo.minwallet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/6/26.
 * minwallet 操作demo
 */

public class MiniWalletActivity extends Activity {

    private TextView tvAccounts;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

//        TPManager.getInstance().initSDK(this);
//        //设置主网和节点
//        TPManager.getInstance().setBlockChain(this, NetTypeEnum.EOS_MAINNET, "http://openapi.eos.ren");
//        //设置miniwallet插件地址
//        TPManager.getInstance().setAppPluginNode(this, "http://eosinfo.mytokenpocket.vip");
//        //设置一个密码，用来miniwallet数据，这里只是为了展示需要设置为12345
//        TPManager.getInstance().setSeed(this, "123456");
//
//        tvAccounts = findViewById(R.id.tv_accounts);
//
//        //清除所有授权账号
//        findViewById(R.id.btn_clear).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<String> accounts = TPManager.getInstance().getAccounts(MiniWalletActivity.this);
//                for(String account : accounts) {
//                    TPManager.getInstance().clearAuth(MiniWalletActivity.this, account);
//                }
//            }
//        });
//
//        //获取已经授权的所有账号
//        findViewById(R.id.btn_accounts).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<String> accounts = TPManager.getInstance().getAccounts(MiniWalletActivity.this);
//                StringBuilder sb = new StringBuilder();
//                for (String account : accounts) {
//                    sb.append(account).append("  ");
//                }
//                tvAccounts.setText(accounts.toString());
//            }
//
//        });
//
//        //对账号授权
//        findViewById(R.id.btn_auth).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MiniWalletActivity.this, AuthActivity.class);
//                MiniWalletActivity.this.startActivity(intent);
//            }
//        });
//
//        //测试利用minwallet发送交易
//        findViewById(R.id.btn_pushtx).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MiniWalletActivity.this, PushTxActivity.class);
//                MiniWalletActivity.this.startActivity(intent);
//            }
//        });
    }
}
