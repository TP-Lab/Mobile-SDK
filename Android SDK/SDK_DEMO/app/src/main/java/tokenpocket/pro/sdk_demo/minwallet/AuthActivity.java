package tokenpocket.pro.sdk_demo.minwallet;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.tokenpocket.opensdk.base.TPListener;
import com.tokenpocket.opensdk.base.TPManager;
import com.tokenpocket.opensdk.innerwallet.model.AuthorizePerm;
import com.tokenpocket.opensdk.innerwallet.model.Permission;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/6/26.
 */

/**
 * Created by duke on 2019/6/20.
 */

public class AuthActivity extends Activity implements View.OnClickListener {
    private EditText etAccount;
    private EditText etPerm;
    private ListView lsActions;
    private ActionAdapter mAdapter;
    private List<Permission.LinkAction> linkActions = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        etAccount = findViewById(R.id.et_account);
        etPerm = findViewById(R.id.et_perm);
        findViewById(R.id.btn_auth).setOnClickListener(this);
        findViewById(R.id.btn_addaction).setOnClickListener(this);
        findViewById(R.id.btn_removeaction).setOnClickListener(this);

        lsActions = findViewById(R.id.ls_actions);
        mAdapter = new ActionAdapter(this, linkActions);
        lsActions.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_auth:
                auth();
                break;
            case R.id.btn_addaction:
                Permission.LinkAction linkAction = new Permission.LinkAction();
                mAdapter.addData(linkAction);
                break;
            case R.id.btn_removeaction:
                mAdapter.removeData(linkActions.size() - 1);
                break;
        }
    }

    private void auth() {
        AuthorizePerm authorizePerm = new AuthorizePerm();
        authorizePerm.setAccount(etAccount.getText().toString());
        authorizePerm.setPermExisted(false);
        authorizePerm.setPerm(etPerm.getText().toString());
        authorizePerm.setActions(linkActions);
        authorizePerm.setDappIcon("https://newdex.io/static/logoicon.png");
        authorizePerm.setDappName("Test");
        authorizePerm.setSelectAll(true);

        TPManager.getInstance().auth(AuthActivity.this, authorizePerm, new TPListener() {
                    @Override
                    public void onSuccess(String data) {
                        Toast.makeText(AuthActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String data) {
                        Toast.makeText(AuthActivity.this, data, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(String data) {
                        Toast.makeText(AuthActivity.this, data, Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
