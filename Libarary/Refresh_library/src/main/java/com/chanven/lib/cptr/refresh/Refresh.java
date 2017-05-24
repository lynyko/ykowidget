package com.chanven.lib.cptr.refresh;

/**
 * Created by Yko on 2016/9/23.
 */
public abstract class Refresh<T> {
    public abstract void refresh();
    public abstract void loadMore();
    protected IRefresh mRefreshImp;
    public void init(IRefresh refreshImp){
        mRefreshImp = refreshImp;
    }


}
