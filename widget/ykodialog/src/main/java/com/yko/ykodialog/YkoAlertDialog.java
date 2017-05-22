package com.yko.ykodialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by yko on 2017/5/22.
 */

@SuppressLint("ValidFragment")
public class YkoAlertDialog extends DialogFragment {
    View mView;
    TextView tvTitle;
    TextView tvLeft;
    TextView tvRight;
    LinearLayout llOperation;
    TextView tvMessage;

    String mLeft, mRight, mTitle, mMessage;
    View.OnClickListener mLeftClickListener, mRightClickListener;
    Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_layout, null);
        tvTitle = (TextView) mView.findViewById(R.id.tv_title);
        tvLeft = (TextView) mView.findViewById(R.id.tv_left);
        tvRight = (TextView) mView.findViewById(R.id.tv_right);
        llOperation = (LinearLayout) mView.findViewById(R.id.ll_operation);
        tvMessage = (TextView) mView.findViewById(R.id.tv_message);

        initView();
        return mView;
    }

    public YkoAlertDialog(Buidler buidler){
        mLeft = buidler.mLeft;
        mRight = buidler.mRight;
        mTitle = buidler.mTitle;
        mMessage = buidler.mMessage;
        mLeftClickListener = buidler.mLeftClickListener;
        mRightClickListener = buidler.mRightClickListener;
        mActivity = buidler.mActivity;
    }

    private void initView(){
        if(TextUtils.isEmpty(mTitle)){
            mTitle = mActivity.getResources().getString(R.string.default_title);
        }
        tvTitle.setText(mTitle);

        if(TextUtils.isEmpty(mMessage)){
            mMessage = "";
        }
        tvMessage.setText(mMessage);

        if(!TextUtils.isEmpty(mLeft)){
            tvLeft.setVisibility(View.VISIBLE);
            tvLeft.setText(mLeft);
            if(mLeftClickListener != null){
                tvLeft.setOnClickListener(mLeftClickListener);
            } else {
                tvLeft.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        }

        if(!TextUtils.isEmpty(mRight)){
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText(mRight);
            if(mLeftClickListener != null){
                tvRight.setOnClickListener(mRightClickListener);
            } else {
                tvRight.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        }
    }

    public void show() {
        if(mActivity != null){
            show(mActivity.getFragmentManager(), mTitle);
        }
    }
    static class Buidler {
        String mLeft, mRight, mTitle, mMessage;
        View.OnClickListener mLeftClickListener, mRightClickListener;
        Activity mActivity;
        public Buidler(Activity activity){
            mActivity = activity;
        }

        public Buidler setLeftButton(String left, View.OnClickListener onClickListener){
            mLeft = left;
            mLeftClickListener = onClickListener;
            return this;
        }

        public Buidler setRightButton(String right, View.OnClickListener onClickListener){
            mRight = right;
            mRightClickListener = onClickListener;
            return this;
        }

        public Buidler setTitle(String title){
            mTitle = title;
            return this;
        }

        public Buidler setMessage(String message){
            mMessage = message;
            return this;
        }

        public YkoAlertDialog buidle(){
            YkoAlertDialog alertDialog = new YkoAlertDialog(this);
            return alertDialog;
        }
    }
}
