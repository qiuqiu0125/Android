package com.example.unit8.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unit8.Activity.MainFragemntActivity;
import com.example.unit8.Bean.FlagDataBean;
import com.example.unit8.Dao.DBHelper;
import com.example.unit8.R;



public class FlagInsertFragment extends Fragment {
    private EditText editText;
    private String strText;
    private Button insert;

    private DBHelper helper;


    public FlagInsertFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText=view.findViewById(R.id.ETtext);
        insert=view.findViewById(R.id.insert);

        helper= new DBHelper(view.getContext());

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strText=editText.getText().toString();

                FlagDataBean flagDataBean = new FlagDataBean();
                flagDataBean.setContent(strText);
                Toast.makeText(getActivity(), "已成功插入一条便签", Toast.LENGTH_SHORT).show();
                helper.flagadd(flagDataBean);
                Log.e("FlagDataBean.getContent", flagDataBean.getContent());
                Intent intent = new Intent(getActivity(), MainFragemntActivity.class);
                intent.putExtra("ToQueryFlagFragment", true);
                startActivity(intent);
            }
        });

    }
}
