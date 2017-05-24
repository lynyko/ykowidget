package com.yko.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.kyleduo.switchbutton.SwitchButton;

/**
 * Created by yko on 2017/5/24.
 */

public class SwitchActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        SwitchButton switchButton = (SwitchButton)findViewById(R.id.sb_ios);
        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(SwitchActivity.this, "结果：" + isChecked, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
