package com.example.unit8.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unit8.Activity.MainFragemntActivity;
import com.example.unit8.Bean.FlagDataBean;
import com.example.unit8.Dao.DBHelper;
import com.example.unit8.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class FlagupdateFragment extends Fragment {
    private ListView listView;
    private ArrayAdapter<String> filelist;
    private EditText editText;
    private Button flagupdate,flagdel;
    private String strID;
    private String strcontent;

    private DBHelper helper;



    public FlagupdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flagupdate, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editText=view.findViewById(R.id.updateETtext);
        flagupdate=view.findViewById(R.id.flagupdate);
        flagdel=view.findViewById(R.id.uptatedel);
        view=this.getLayoutInflater().inflate((R.layout.fragment_flag_manage), null);
        listView=view.findViewById(R.id.flaglist);

        helper=new DBHelper(getContext());

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("flagChooseItem", Context.MODE_PRIVATE);


        strID = sharedPreferences.getString("flagUpdateId", "admin");

        FlagDataBean flagDataBean=helper.flagqueryone(strID);
        editText.setText(flagDataBean.getContent());

        flagupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strcontent=editText.getText().toString();
                FlagDataBean flagDataBean=new FlagDataBean();
                flagDataBean.setContent(strcontent);

                helper.FlagUpdateOne(strID,flagDataBean);
                Toast.makeText(getActivity(), "您已更新一条便签", Toast.LENGTH_SHORT).show();
                onResume();
                Intent intent = new Intent(getActivity(), MainFragemntActivity.class);
                intent.putExtra("ToQueryFlagFragment", true);
                startActivity(intent);

            }
        });

        flagdel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.flagdel(strID);
                onResume();
                Intent intent = new Intent(getActivity(), MainFragemntActivity.class);
                intent.putExtra("ToQueryFlagFragment", true);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();


        filelist=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_single_choice, helper.flagquery());//查询数据库，并将值传给适配器

        listView.setAdapter(filelist);//设置适配器

    }

}
