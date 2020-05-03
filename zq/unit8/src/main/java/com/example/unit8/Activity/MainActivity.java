package com.example.unit8.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.unit8.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private String[]titles=new String[]{"新增支出","新增收入","新增便签","我的支出","我的收入","便签管理","修改密码","帮助","退出"};

    private int item;

    private int[]images=new int[]{R.drawable.addoutaccount,R.drawable.addinaccount,R.drawable.accountflag,
            R.drawable.outaccountinfo,R.drawable.inaccountinfo,R.drawable.showinfo,
            R.drawable.sysset,R.drawable.help,R.drawable.exit};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = (GridView) findViewById(R.id.gvInfo);

        List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();

        for (int j = 0; j < images.length; j++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", images[j]);
            map.put("title", titles[j]);
            listItems.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                listItems,
                R.layout.gvitem,
                new String[]{"title", "image"},
                new int[]{R.id.title_item, R.id.image_item});
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "你点击了：" + titles[position], Toast.LENGTH_SHORT).show();
                item = position;
                for(int i=0;i<9;i++){
                        if(item==i) {
                            Intent intent = new Intent(MainActivity.this, MainFragemntActivity.class);
                            intent.putExtra("item", item);
                            Log.e("111", Integer.toString(item));
                            startActivity(intent);
                        }
                }

            }

        });

        }

    }
