package tokenpocket.pro.sdk_demo.minwallet;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

//import com.tokenpocket.opensdk.innerwallet.model.LinkAction;

import java.util.ArrayList;
import java.util.List;

import tokenpocket.pro.sdk_demo.R;

/**
 * Created by duke on 2019/6/26.
 */

public class ActionAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

    public ActionAdapter() {
    }

    //    private List<LinkAction> linkActions = new ArrayList<>();
//    private Context mContext;
//
//    public ActionAdapter(Context context, List<LinkAction> datas) {
//        this.linkActions = datas;
//        this.mContext = context;
//    }
//
//    public void addData(LinkAction linkAction) {
//        linkActions.add(linkAction);
//        notifyDataSetChanged();
//    }
//
//    public void removeData(int index) {
//        if (index < 0 || index > linkActions.size() - 1) {
//            return;
//        }
//        linkActions.remove(index);
//        notifyDataSetChanged();
//    }
//
//    @Override
//    public int getCount() {
//        return linkActions == null ? 0 : linkActions.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return linkActions == null ? null : linkActions.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(mContext);
//        convertView = inflater.inflate(R.layout.item_action, null, true);
//        EditText etContract = convertView.findViewById(R.id.et_contract);
//        EditText etAction = convertView.findViewById(R.id.et_action);
//        etAction.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                linkActions.get(position).setAction(s.toString());
//            }
//        });
//        etContract.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                linkActions.get(position).setContract(s.toString());
//            }
//        });
//        String contract = linkActions.get(position).getContract();
//        String action = linkActions.get(position).getAction();
//        if(TextUtils.isEmpty(contract)) {
//            contract = "";
//        }
//        if(TextUtils.isEmpty(action)) {
//            action = "";
//        }
//        etContract.setText(contract);
//        etAction.setText(action);
//        return convertView;
//    }

}
