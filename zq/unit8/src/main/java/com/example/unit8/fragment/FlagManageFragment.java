package com.example.unit8.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.unit8.Activity.MainFragemntActivity;
import com.example.unit8.Dao.DBHelper;
import com.example.unit8.R;

import static android.content.Context.MODE_PRIVATE;


public class FlagManageFragment extends Fragment {
    private ArrayAdapter<String> filelist;
    private ListView listView;
    private String strCheckedID;

    private DBHelper helper;


    public FlagManageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flag_manage, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        helper =new DBHelper(getActivity());

        listView=view.findViewById(R.id.flaglist);

        filelist=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_single_choice, helper.flagquery());//查询数据库，并将值传给适配器

        listView.setAdapter(filelist);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String stritem = parent.getItemAtPosition(position).toString();//选中信息

                strCheckedID = stritem.split("\\|")[0].trim();//从|处分割信息，将id赋值给strCheckedID

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("flagChooseItem", MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("flagUpdateId", strCheckedID);

                editor.commit();

                Intent intent = new Intent(getActivity(), MainFragemntActivity.class);
                intent.putExtra("ToFlagUpdateFragment", true);
                startActivity(intent);
            }
        });
    }

}
