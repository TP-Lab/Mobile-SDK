package tokenpocket.pro.sdk_demo.minwallet;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/6/26.
 * 展示如果利用minwallet sdk在EOS账号上添加自定义权限组，并且将特定操作链接到权限组
 */


public class AuthActivity extends Activity implements View.OnClickListener {
//    private List<LinkAction> linkActions = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        findViewById(R.id.btn_auth).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_auth:
//                auth();
                break;
        }
    }

//    private void auth() {
//        AuthorizePerm authorizePerm = new AuthorizePerm();
//        //添加权限组的账号
//        authorizePerm.setAccount("ljxlzdh54321");
//        authorizePerm.setPermExisted(false);
//        //要添加的权限组的名字
//        authorizePerm.setPerm("testtransfer");
//        //和权限组关联的操作，只有把操作关联到添加的权限组上面了，才能利用自定义的权限进行相应的操作
//        LinkAction linkAction = new LinkAction();
//        //这里定义EOS上TPT的转账操作关联到自定义的testtransfer权限组
//        linkAction.setContract("eosiotptoken");
//        linkAction.setAction("transfer");
//        linkActions.add(linkAction);
//        authorizePerm.setActions(linkActions);
//
//        authorizePerm.setDappIcon("https://newdex.io/static/logoicon.png");
//        authorizePerm.setDappName("Test");
//        authorizePerm.setSelectAll(true);
//
//        TPManager.getInstance().auth(AuthActivity.this, authorizePerm, new TPListener() {
//                    @Override
//                    public void onSuccess(String data) {
//                        //操作成功后，会返回本次操作的hash
//                        Toast.makeText(AuthActivity.this, data, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onError(String data) {
//                        Toast.makeText(AuthActivity.this, data, Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancel(String data) {
//                        Toast.makeText(AuthActivity.this, data, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }


}
