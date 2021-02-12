package com.mmfinfotech.NavneetTiwaritest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mmfinfotech.NavneetTiwaritest.adapter.UserAdapter;
import com.mmfinfotech.NavneetTiwaritest.interfacess.Consts;
import com.mmfinfotech.NavneetTiwaritest.model.DataDTO;

import java.util.ArrayList;

public class SelectedProfile extends AppCompatActivity {

    ArrayList<DataDTO> dataDTOS=new ArrayList<>();
    LinearLayoutManager mLayoutManager;
    RecyclerView rvUsers;
    ImageView ivBack;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_profile);

        rvUsers=findViewById(R.id.rvUsers);
        ivBack=findViewById(R.id.ivBack);
        if(getIntent().hasExtra(Consts.DTO)){
        dataDTOS= (ArrayList<DataDTO>) getIntent().getSerializableExtra(Consts.DTO);
        setData();
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();
            }
        });
    }

    private void setData() {

        mLayoutManager = new LinearLayoutManager(SelectedProfile.this,LinearLayoutManager.VERTICAL,false);
        rvUsers.setLayoutManager(mLayoutManager);

        userAdapter = new UserAdapter(SelectedProfile.this, dataDTOS);
        rvUsers.setAdapter(userAdapter);
    }
}