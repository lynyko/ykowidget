package com.yko.widget;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.yko.ykodialog.YkoProcessDialog;

/**
 * Created by yko on 2017/5/23.
 */

public class DialogActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
//        new YkoMessageDialog.Builder(this).setTitle("测试标题")
//                .setLeftButton("我要取消", null)
//                .setRightButton("我要确认", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast.makeText(MainActivity.this, "点击确认", Toast.LENGTH_SHORT).show();
//                    }
//                })
//                .setMessage("测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息测试信息")
//                .setGravity(YkoMessageDialog.Builder.GRAVITY_BOTTOM)
//                .setmOutsideFocusable(false)
//                .buidle().show();

        final YkoProcessDialog ykoProcessDialog = new YkoProcessDialog(this).setProcess("正在加载");
        ykoProcessDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ykoProcessDialog.setFailure("失败了");
            }
        }, 4000);
    }
}
