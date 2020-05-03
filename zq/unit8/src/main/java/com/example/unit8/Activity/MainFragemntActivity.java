package com.example.unit8.Activity;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.unit8.Dao.DBHelper;
import com.example.unit8.fragment.FlagManageFragment;
import com.example.unit8.R;
import com.example.unit8.fragment.ExpentInsertFragment;
import com.example.unit8.fragment.ExpentQueryFragment;
import com.example.unit8.fragment.ExpentUpdateFragment;
import com.example.unit8.fragment.FlagInsertFragment;
import com.example.unit8.fragment.FlagupdateFragment;
import com.example.unit8.fragment.HelpFragment;
import com.example.unit8.fragment.InaccountInsertFragment;
import com.example.unit8.fragment.InaccountQueryFragment;
import com.example.unit8.fragment.InaccountUpdateFragment;
import com.example.unit8.fragment.UpdatepasswordFragment;

public class MainFragemntActivity extends AppCompatActivity {

    //声明fragment
    private InaccountInsertFragment inaccountInsertFragment;
    private InaccountQueryFragment inaccountQueryFragment;
    private InaccountUpdateFragment inaccountUpdateFragment;

    private ExpentInsertFragment expentInsertFragment;
    private ExpentQueryFragment expentQueryFragment;
    private ExpentUpdateFragment expentUpdateFragment;

    private FlagInsertFragment flagInsertFragment;
    private FlagManageFragment flagManageFragment;
    private FlagupdateFragment flagupdateFragment;

    private UpdatepasswordFragment updatepasswordFragment;

    private HelpFragment helpFragment;

    private FragmentManager fm;
    private DBHelper helper;
    private ArrayAdapter<String>fileList;
    private Intent intent;
    private int item;//存储intent传过来的值
    private boolean inaccountupdate;//储存收入查询页面传过来的值，用于跳转到更新页面
    private boolean expentupdata;
    private boolean flagupdate;
    private boolean flagquery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_fragment);
        //创建DBhelper
        helper= new DBHelper(this);

        //创建fragment对象
        inaccountInsertFragment = new InaccountInsertFragment();
        inaccountQueryFragment =new InaccountQueryFragment();
        inaccountUpdateFragment=new InaccountUpdateFragment();
        expentInsertFragment =new ExpentInsertFragment();
        expentQueryFragment =new ExpentQueryFragment();
        expentUpdateFragment =new ExpentUpdateFragment();
        flagInsertFragment=new FlagInsertFragment();
        flagManageFragment=new FlagManageFragment();
        flagupdateFragment=new FlagupdateFragment();
        updatepasswordFragment=new UpdatepasswordFragment();
        helpFragment=new HelpFragment();

        //创建fragmentManager
        fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        //添加fragments
        ft.add(R.id.f1Framents,inaccountInsertFragment);
        ft.add(R.id.f1Framents, inaccountQueryFragment);
        ft.add(R.id.f1Framents,inaccountUpdateFragment);
        ft.add(R.id.f1Framents,expentInsertFragment);
        ft.add(R.id.f1Framents,expentQueryFragment);
        ft.add(R.id.f1Framents,expentUpdateFragment);
        ft.add(R.id.f1Framents,flagInsertFragment);
        ft.add(R.id.f1Framents,flagManageFragment);
        ft.add(R.id.f1Framents,flagupdateFragment);
        ft.add(R.id.f1Framents,helpFragment);
        ft.add(R.id.f1Framents,updatepasswordFragment);

        hideAllFragment();
        ft.commit();

        intent=getIntent();
        item=intent.getExtras().getInt("item");
            switch (item){
                case 0:Expentadd();break;
                case 1:databaseAdd();break;
                case 2:FlagInsert();break;
                case 3:ExpentQuery();break;
                case 4:databasemanage();break;
                case 5:FlagManage();{};break;
                case 6:updatepw();{};break;
                case 7:help();{};break;
                case 8:exit();{};break;
            }
        inaccountupdate=intent.getExtras().getBoolean("ToUpdateFragment") ;
            if(inaccountupdate){Inaccountupdate();}
        expentupdata=intent.getExtras().getBoolean("ToexUpdateFragment") ;
            if(expentupdata){Expentupdata();}
        flagupdate=intent.getExtras().getBoolean("ToFlagUpdateFragment");
            if(flagupdate){FlagUpDate();}
        flagquery=intent.getExtras().getBoolean("ToQueryFlagFragment");
            if(flagquery){FlagManage();}

    }

    public void exit(){
        Intent intent=new Intent(this,LoginActivity.class);
        startActivity(intent);
    }

    public void help(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(helpFragment);
        ft.commit();
    }

    public void databaseAdd(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(inaccountInsertFragment);//显示insertfragment
        ft.commit();

    }
    public void databasemanage(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(inaccountQueryFragment);//显示queryfragment
        ft.commit();

    }

    public void Inaccountupdate(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(inaccountUpdateFragment);
        ft.commit();
    }

    public void Expentadd(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(expentInsertFragment);
        ft.commit();
    }

    public void ExpentQuery(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(expentQueryFragment);
        ft.commit();
    }

    public void Expentupdata(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(expentUpdateFragment);
        ft.commit();
    }

    public void FlagInsert(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(flagInsertFragment);
        ft.commit();
    }

    public void FlagManage(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(flagManageFragment);
        ft.commit();
    }

    public void FlagUpDate(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(flagupdateFragment);
        ft.commit();
    }

    public void updatepw(){
        hideAllFragment();
        FragmentTransaction ft=fm.beginTransaction();
        ft.show(updatepasswordFragment);
        ft.commit();
    }

    private void hideAllFragment() {
        FragmentTransaction ft = fm.beginTransaction();
        ft.hide(inaccountInsertFragment);
        ft.hide(inaccountQueryFragment);
        ft.hide(inaccountUpdateFragment);
        ft.hide(expentQueryFragment);
        ft.hide(expentInsertFragment);
        ft.hide(expentUpdateFragment);
        ft.hide(flagInsertFragment);
        ft.hide(flagManageFragment);
        ft.hide(flagupdateFragment);
        ft.hide(helpFragment);
        ft.hide(updatepasswordFragment);
        ft.commit();


    }


}
