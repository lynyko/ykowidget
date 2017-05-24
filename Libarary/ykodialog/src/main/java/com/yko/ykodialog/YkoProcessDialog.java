package com.yko.ykodialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by yko on 2017/5/23.
 */

@SuppressLint("ValidFragment")
public class YkoProcessDialog extends DialogFragment {
    View mView;
    ProgressBar progressBar;
    TextView tvMessage;
    ImageView ivResult;
    String mMessage;
    private final int STATE_LOADING = 0;
    private final int STATE_SUCCESS = 1;
    private final int STATE_FAILURE = 2;
    int state = STATE_LOADING;
    Activity mActivity;


    public YkoProcessDialog(Activity activity){
        mActivity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_layout_process, null);
        progressBar = (ProgressBar) mView.findViewById(R.id.progressBar);
        tvMessage = (TextView) mView.findViewById(R.id.tv_message);
        ivResult = (ImageView) mView.findViewById(R.id.iv_result);
        if(STATE_LOADING == state){
            setProcess(mMessage);
        } else if(STATE_SUCCESS == state){
            setSuccess(mMessage);
        } else {
            setFailure(mMessage);
        }
        return mView;
    }

    public YkoProcessDialog setProcess(String message){
        mMessage = message;
        if(mView == null){
            state = STATE_LOADING;
        } else {
            ivResult.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                progressBar.setIndeterminateDrawable(getDialog().getContext().getDrawable(R.drawable.process_loading));
            } else {
                progressBar.setIndeterminateDrawable(getDialog().getContext().getResources().getDrawable(R.drawable.process_loading));
            }
            if(TextUtils.isEmpty(message)){
                message = "";
            }
            tvMessage.setText(message);
        }
        return this;
    }

    public void show(){
        show(mActivity.getFragmentManager(), "YkoProcessDialog");
    }

    public YkoProcessDialog setSuccess(String message){
        mMessage = message;
        if(mView == null){
            state = STATE_SUCCESS;
        } else {
            progressBar.setVisibility(View.GONE);
            ivResult.setVisibility(View.VISIBLE);
            ivResult.setImageResource(R.mipmap.success);
            if(TextUtils.isEmpty(message)){
                message = "";
            }
            tvMessage.setText(message);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, 2000);
        }
        return this;
    }

    public YkoProcessDialog setFailure(String message){
        mMessage = message;
        if(mView == null){
            state = STATE_FAILURE;
        } else {
            progressBar.setVisibility(View.GONE);
            ivResult.setVisibility(View.VISIBLE);
            ivResult.setImageResource(R.mipmap.failed);
            if(TextUtils.isEmpty(message)){
                message = "";
            }
            tvMessage.setText(message);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    dismiss();
                }
            }, 2000);
        }
        return this;
    }

}
