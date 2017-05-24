package com.yko.widget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.yko.widget.pinyinheader.PinyinHeaderActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    TextView tvDialog, tvSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        tvDialog = (TextView) findViewById(R.id.tv_dialog);
        tvSwitch = (TextView) findViewById(R.id.tv_switch);

        tv.setOnClickListener(this);
        tvDialog.setOnClickListener(this);
        tvSwitch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == tv) {
            startActivity(new Intent(this, PinyinHeaderActivity.class));
        } else if(v == tvDialog){
            startActivity(new Intent(this, DialogActivity.class));
        } else if(v == tvSwitch){
            startActivity(new Intent(this, SwitchActivity.class));
        }
    }
}
