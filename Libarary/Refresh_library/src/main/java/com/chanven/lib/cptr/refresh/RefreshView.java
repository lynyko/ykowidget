package com.chanven.lib.cptr.refresh;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chanven.lib.cptr.PtrClassicFrameLayout;
import com.chanven.lib.cptr.PtrDefaultHandler;
import com.chanven.lib.cptr.PtrFrameLayout;
import com.chanven.lib.cptr.R;
import com.chanven.lib.cptr.loadmore.OnLoadMoreListener;
import com.chanven.lib.cptr.recyclerview.RecyclerAdapterWithHF;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Yko on 2016/9/23.
 */
public class RefreshView extends FrameLayout implements IRefresh{
    PtrClassicFrameLayout ptrClassicFrameLayout;
    RefreshAdapter adapter;
    RecyclerView mRecyclerView;
    Refresh refresh;
    Context mContext;
    TextView tvNoData;

    private RecyclerAdapterWithHF mAdapter;

    public RefreshView(Context context) {
        this(context, null);
    }

    public RefreshView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RefreshView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.layout_recyclerview, this);
        tvNoData = (TextView) view.findViewById(R.id.tv_no_data);
        ptrClassicFrameLayout = (PtrClassicFrameLayout)view.findViewById(R.id.recycler_view_frame);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_view);
        tvNoData.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tvNoData.setVisibility(GONE);
                ptrClassicFrameLayout.autoRefresh();
            }
        });
    }

    public void init(Refresh refresh, RefreshAdapter adapter){
        this.refresh = refresh;
        this.adapter = adapter;
        this.refresh.init(this);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        ptrClassicFrameLayout.setPtrHandler(new PtrDefaultHandler() {

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                tvNoData.setVisibility(GONE);
                RefreshView.this.refresh.refresh();
            }
        });
        ptrClassicFrameLayout.setOnLoadMoreListener(new OnLoadMoreListener() {

            @Override
            public void loadMore() {
                RefreshView.this.refresh.loadMore();
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tvNoData.setVisibility(GONE);
                ptrClassicFrameLayout.autoRefresh();
            }
        }, 100);
    }

    public void setHeader(View header){
        if(ptrClassicFrameLayout != null){
            ptrClassicFrameLayout.setHeaderView(header);
        }
    }

    public void startRefresh(){
        ptrClassicFrameLayout.autoRefresh();
    }

    @Override
    public void refresh(List data, boolean hasMore) {
        adapter.setData(data);
        ptrClassicFrameLayout.refreshComplete();
        ptrClassicFrameLayout.setLoadMoreEnable(hasMore);
    }

    @Override
    public void loadMore(List data, boolean hasMore) {
        adapter.addAll(data);
        ptrClassicFrameLayout.loadMoreComplete(true);
        ptrClassicFrameLayout.setLoadMoreEnable(hasMore);
    }

    @Override
    public void noData(int totle) {
        if(totle == 0){
            // 刷新时没有数据
            adapter.setData(new ArrayList<>());
            ptrClassicFrameLayout.refreshComplete();
            tvNoData.setVisibility(VISIBLE);
        } else {
            // 加载更多时没有数据
            ptrClassicFrameLayout.loadMoreComplete(true);
        }
    }

    @Override
    public void failure(String content) {
        Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
        if(ptrClassicFrameLayout.isPullToRefresh()) {
            ptrClassicFrameLayout.refreshComplete();
        }
        if(ptrClassicFrameLayout.isLoadingMore()) {
            ptrClassicFrameLayout.loadMoreComplete(true);
        }
    }
}
