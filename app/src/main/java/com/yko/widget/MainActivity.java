package com.yko.widget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.yko.ykodialog.YkoMessageDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new YkoMessageDialog.Builder(this).setTitle("测试标题")
                .setLeftButton("我要取消", null)
                .setRightButton("我要确认", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, "点击确认", Toast.LENGTH_SHORT).show();
                    }
                })
                .setMessage("测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息")
                .setGravity(YkoMessageDialog.Builder.GRAVITY_BOTTOM)
                .setmOutsideFocusable(false)
                .buidle().show();
    }
}
