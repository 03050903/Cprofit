package com.chen.cprofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chen.cprofit.operation.AddOperationActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initListeners();
    }
    private void initViews(){
        Toolbar toolbar=findViewById(R.id.tl_bar);
        setSupportActionBar(toolbar);
        recyclerView=findViewById(R.id.rv_list);
        actionButton=findViewById(R.id.fl_action);
    }
    private void initListeners(){
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(v.getContext(), AddOperationActivity.class);
                startActivity(intent);
            }
        });
    }

}