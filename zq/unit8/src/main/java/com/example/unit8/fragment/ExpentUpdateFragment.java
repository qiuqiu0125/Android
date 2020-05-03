package com.example.unit8.fragment;


import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unit8.Bean.DataBean;
import com.example.unit8.Bean.OutDataBean;
import com.example.unit8.Dao.DBHelper;
import com.example.unit8.R;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpentUpdateFragment extends Fragment {

    private TextView tvID;
    private EditText etMoney, etHandler, etMark;
    private Spinner spinnerType;
    private String strMoney, strTime, strType, strHandler, strMark;
    private Button btnCommit;
    private String strID;

    private DBHelper helper;

    public ExpentUpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.expent_update_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        helper = new DBHelper(view.getContext());
        etMoney = view.findViewById(R.id.exet_money);
        spinnerType = view.findViewById(R.id.exspinner_type);
        etHandler = view.findViewById(R.id.exet_handler);
        etMark = view.findViewById(R.id.exet_mark);
        btnCommit = view.findViewById(R.id.excommitUpdateBtn);

        tvID = view.findViewById(R.id.extvID);


        // 获得SharedPreferences对象，里面存的是id
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("exChooseItem", Context.MODE_PRIVATE);

        // 通过键值对读取保存的内容
        strID = sharedPreferences.getString("exUpdateId", "admin");

        OutDataBean data = helper.expentqueryOne(strID);
        tvID.setText("ID号：" + data.get_id());
        etMoney.setText(data.getStrMoney());
        etHandler.setText(data.getStrHandle());
        etMark.setText(data.getStrMark());

        Resources res = getResources();//获取资源
        String[] spinnerArray = res.getStringArray(R.array.outype);//创建数组保存res中的spinner array
        for (int i = 0; i < spinnerArray.length; i++) {           //遍历数组，将其中与数据结构中的strtype相同的item赋值给spinnertype
            if (spinnerArray[i].equals(data.getStrType())) {
                spinnerType.setSelection(i);
            }
        }

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 从界面获取填写内容
                strMoney = etMoney.getText().toString();
                strHandler = etHandler.getText().toString();
                strMark = etMark.getText().toString();

                // 获取当前时间
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                strTime = simpleDateFormat.format(date);

                // 存放数据到outDataBean对象
                OutDataBean data = new OutDataBean();
                data.setStrMoney(strMoney);
                data.setStrTime(strTime);
                data.setStrType(strType);
                data.setStrHandle(strHandler);
                data.setStrMark(strMark);

                // 把outDataBean对象插入数据库
                helper.expentUpdateOne(strID, data);

                Toast.makeText(getActivity(), "您已更新一条支出信息", Toast.LENGTH_SHORT).show();


            }
        });


    }
}
