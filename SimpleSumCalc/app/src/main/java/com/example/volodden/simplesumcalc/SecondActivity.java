package com.example.volodden.simplesumcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.File;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClickOnStart(View view) {
        Intent intent = new Intent(this, CalcDisplayActivity.class);

        startActivity(intent);
    }


}
