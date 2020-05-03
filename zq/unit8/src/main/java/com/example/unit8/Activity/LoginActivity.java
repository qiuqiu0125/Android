package com.example.unit8.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.unit8.R;

public class LoginActivity extends AppCompatActivity {
    private String password;
    private String content; //暂存密码，用作比较
    private EditText editText;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText = findViewById(R.id.password);
        login = findViewById(R.id.login);




        SharedPreferences sharedPreferences =getSharedPreferences("password", Context.MODE_PRIVATE);


        password = sharedPreferences.getString("password","");

    }

    public void onclic(View view) {

        content = editText.getText().toString();
        if (password.equals(content)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else
            Toast.makeText(this, "密码有误！", Toast.LENGTH_SHORT).show();
            Log.e("pw",password);
            Log.e("pws",content);
    }
}



