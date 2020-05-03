package com.example.unit8.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
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

import com.example.unit8.Activity.MainActivity;
import com.example.unit8.Activity.MainFragemntActivity;
import com.example.unit8.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpdatepasswordFragment extends Fragment {
    private String password;
    private EditText editText;
    private Button button;


    public UpdatepasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_updatepassword, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editText=view.findViewById(R.id.password);
        button=view.findViewById(R.id.updatepw);





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                password=editText.getText().toString();

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("password",MODE_PRIVATE);

                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString("password", password);

                editor.commit();

                Intent intent = new Intent(getActivity(), MainActivity.class);

                startActivity(intent);
            }
        });

    }

}
