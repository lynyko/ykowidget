package com.chanven.lib.cptr.refresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Created by yko on 2016/10/27.
 */

public abstract  class RefreshAdapter<T> extends RecyclerView.Adapter {
    protected Context mContext;
    protected List<T> mList;
    protected RefreshItemClickListener listener;
    public RefreshAdapter(Context context, List<T> list){
        mContext = context;
        mList = list;
    }

    public void setData(List<T> data){
        mList = data;
        notifyDataSetChanged();
    }

    public void addAll(List<T> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    public void setOnItemClickListener(RefreshItemClickListener listener){
        this.listener = listener;
    }

    public interface RefreshItemClickListener<V>{
        public void onItemClick(View view, V data, int position);
    }
}
