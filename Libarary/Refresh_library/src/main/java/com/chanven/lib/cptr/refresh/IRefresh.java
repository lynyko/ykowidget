package com.chanven.lib.cptr.refresh;

import java.util.List;

/**
 * Created by Yko on 2016/9/23.
 */
public interface IRefresh {
    public void refresh(List data, boolean hasMore);
    public void loadMore(List data, boolean hasMore);
    public void noData(int totle);
    public void failure(String content);
}
