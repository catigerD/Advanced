package com.example.study.hotfixapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.hotfix.HotfixUtils;
import com.example.study.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewById(R.id.bt_bug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculator calculator = new Calculator();
                calculator.something();
            }
        });
        findViewById(R.id.bt_fix).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotfixUtils.fix(SecondActivity.this);
            }
        });
    }
}
