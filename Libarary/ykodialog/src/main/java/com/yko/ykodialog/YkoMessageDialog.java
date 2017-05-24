package com.yko.ykodialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 显示消息dialog
 * Created by yko on 2017/5/22.
 */

@SuppressLint("ValidFragment")
public class YkoMessageDialog extends DialogFragment {
    View mView;
    TextView tvTitle;
    TextView tvLeft;
    TextView tvRight;
    LinearLayout llOperation;
    TextView tvMessage;

    boolean mOutsideFocusable = true;
    int mGravity = Builder.GRAVITY_CENTER;
    String mLeft, mRight, mTitle, mMessage;
    View.OnClickListener mLeftClickListener, mRightClickListener;
    Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.dialog_layout_message, null);
        tvTitle = (TextView) mView.findViewById(R.id.tv_title);
        tvLeft = (TextView) mView.findViewById(R.id.tv_left);
        tvRight = (TextView) mView.findViewById(R.id.tv_right);
        llOperation = (LinearLayout) mView.findViewById(R.id.ll_operation);
        tvMessage = (TextView) mView.findViewById(R.id.tv_message);

        initView();

        return mView;
    }

    public YkoMessageDialog(Builder builder){
        mLeft = builder.mLeft;
        mRight = builder.mRight;
        mTitle = builder.mTitle;
        mMessage = builder.mMessage;
        mLeftClickListener = builder.mLeftClickListener;
        mRightClickListener = builder.mRightClickListener;
        mActivity = builder.mActivity;
        mOutsideFocusable = builder.mOutsideFocusable;
        mGravity = builder.mGravity;
    }

    private void initView(){
        if(TextUtils.isEmpty(mTitle)){
            mTitle = "";
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
            tvRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mRightClickListener != null){
                        mRightClickListener.onClick(v);
                    }
                    dismiss();
                }
            });
        }

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().setCanceledOnTouchOutside(mOutsideFocusable);

//        if(Builder.GRAVITY_BOTTOM == mGravity){
//            final DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            final WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
////            layoutParams.width = dm.widthPixels;
////            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//            layoutParams.gravity = Gravity.BOTTOM;
//            getDialog().getWindow().setAttributes(layoutParams);
//            getDialog().getWindow().setLayout( dm.widthPixels, getDialog().getWindow().getAttributes().height );
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if(Builder.GRAVITY_BOTTOM == mGravity){
            final DisplayMetrics dm = new DisplayMetrics();
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
            final WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
            layoutParams.gravity = Gravity.BOTTOM;
            getDialog().getWindow().setAttributes(layoutParams);
            getDialog().getWindow().setLayout( dm.widthPixels, getDialog().getWindow().getAttributes().height );
        }
    }

    public void show() {
        if(mActivity != null){
            show(mActivity.getFragmentManager(), mTitle);
        }
    }

    public static class Builder {
        public static final int GRAVITY_CENTER = 0;
        public static final int GRAVITY_BOTTOM = 1;
        String mLeft, mRight, mTitle, mMessage;
        View.OnClickListener mLeftClickListener, mRightClickListener;
        Activity mActivity;
        boolean mOutsideFocusable = true;
        int mGravity = GRAVITY_CENTER;

        public Builder(Activity activity){
            mActivity = activity;
        }

        public Builder setmOutsideFocusable(boolean focusable){
            mOutsideFocusable = focusable;
            return this;
        }

        public Builder setGravity(int gravity){
            mGravity = gravity;
            return this;
        }

        public Builder setLeftButton(String left, View.OnClickListener onClickListener){
            mLeft = left;
            mLeftClickListener = onClickListener;
            return this;
        }

        public Builder setRightButton(String right, View.OnClickListener onClickListener){
            mRight = right;
            mRightClickListener = onClickListener;
            return this;
        }

        public Builder setTitle(String title){
            mTitle = title;
            return this;
        }

        public Builder setMessage(String message){
            mMessage = message;
            return this;
        }

        public YkoMessageDialog buidle(){
            YkoMessageDialog alertDialog = new YkoMessageDialog(this);
            return alertDialog;
        }
    }
}
