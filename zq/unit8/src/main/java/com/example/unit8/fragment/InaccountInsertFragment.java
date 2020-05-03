package com.example.unit8.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.unit8.Dao.DBHelper;
import com.example.unit8.Bean.DataBean;
import com.example.unit8.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class InaccountInsertFragment extends Fragment {

    //声明
    private Button btnCommit;
    private EditText etMoney,etHandler,etMark;
    private String strMoney,strTime,strType,strHandler,strMark;
    private Spinner spinnerType;
    private DBHelper helper;

    public InaccountInsertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.inaccount_insert_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //绑定控件
        btnCommit =view.findViewById(R.id.commitBtn);

        etHandler=view.findViewById(R.id.et_handler);
        etMark=view.findViewById(R.id.et_mark);
        etMoney=view.findViewById(R.id.et_money);

        spinnerType=view.findViewById(R.id.spinner_type);
        helper=new DBHelper(view.getContext());

        //spinnerType点击选择事件
        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                strType=parent.getItemAtPosition(position).toString();//将获取到的点击信息赋值给strType
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //从界面获取参数
                strMoney=etMoney.getText().toString();
                strHandler=etHandler.getText().toString();
                strMark=etMark.getText().toString();

                //获取时间
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy年MM月dd日");
                Date date=new Date(System.currentTimeMillis());
                strTime=simpleDateFormat.format(date);//将时间赋值给strTime

                //创建数据结构DataBean的对象db
                DataBean db=new DataBean();
                //使用读写访问器获取到数据结构中的内容
                db.setStrMoney(strMoney);
                db.setStrTime(strTime);
                db.setStrType(strType);
                db.setStrHandle(strHandler);
                db.setStrMark(strMark);



                if(strMoney.equals(""))
                    Toast.makeText(getActivity(), "信息不能为空", Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getActivity(), "已成功插入一条信息", Toast.LENGTH_SHORT).show();

                    //把datebean中的数据插入数据库
                    helper.insert(db);

                    //打印日志
                    Log.e("DataBean.getStrMoney", db.getStrMoney());
                    Log.e("DataBean.getStrTime", db.getStrTime());
                    Log.e("DataBean.getStrType", db.getStrType());
                    Log.e("DataBean.getStrHandler", db.getStrHandle());
                    Log.e("DataBean.getStrMark", db.getStrMark());
                }
            }
        });

    }
}
