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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.unit8.Dao.DBHelper;
import com.example.unit8.Activity.MainFragemntActivity;
import com.example.unit8.R;

import static android.content.Context.MODE_PRIVATE;


public class InaccountQueryFragment extends Fragment {

    private ArrayAdapter<String>fileList;
    private ListView listView;
    private String strCheckedID;

    private Button btnDeleteitmes,btnUpdateitem,btnDeletall;

    private DBHelper helper;


    public InaccountQueryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.inaccount_query_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        helper = new DBHelper(getActivity());



        btnDeleteitmes=view.findViewById(R.id.delete_items);
        btnUpdateitem = view.findViewById(R.id.update_item);
        btnDeletall=view.findViewById(R.id.deletall);

        listView=view.findViewById(R.id.lv_query_all);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String stritem=parent.getItemAtPosition(position).toString();//选中信息

                strCheckedID = stritem.split("\\|")[0].trim();//从|处分割信息，将id赋值给strCheckedID
            }
        });

        btnDeletall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helper.deleteAll();
                onResume();
            }
        });

        btnDeleteitmes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iCheckedCount=listView.getCheckedItemCount();//获选信息的数量
                //如果没有选中任何一条则给出提示
                if(iCheckedCount<1){
                    Toast.makeText(getActivity(), "请选择一条收入信息", Toast.LENGTH_SHORT).show();
                    return;
                }
                helper.deleteOne(strCheckedID);//删除一条信息
                onResume();//刷新
            }
        });

        btnUpdateitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int iCheckedCount = listView.getCheckedItemCount();
                //如果没有选中任何一条则给出提示
                if (iCheckedCount < 1) {
                    Toast.makeText(getActivity(), "请选择一条收入信息", Toast.LENGTH_SHORT).show();
                    return;
                }


                // 获得SharedPreferences对象，用来进行Fragment之间的传值
                SharedPreferences sp = getActivity().getSharedPreferences("ChooseItem", MODE_PRIVATE);
                // 获得Editor对象
                SharedPreferences.Editor editor = sp.edit();
                // 保存选择的ID，便于将ID传给updateFragment
                editor.putString("UpdateId", strCheckedID);
                // 提交保存
                editor.commit();



                /*
                这里是要跳到UpdateFragment界面上去，但先跳到Activity里面
                再在Activity里面判断界面传值，跳转到UpdateFragment
                 */
                Intent intent = new Intent(getActivity(), MainFragemntActivity.class);
                intent.putExtra("ToUpdateFragment", true);
                startActivity(intent);
            }
        });




    }

    @Override
    public void onResume() {
        super.onResume();


        listView.clearChoices();//清除选中状态
        fileList=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_single_choice, helper.queryAll());//查询数据库，并将值传给适配器
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);//设置选择模块
        listView.setAdapter(fileList);//设置适配器

    }

}
