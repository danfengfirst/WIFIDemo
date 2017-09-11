package com.danfeng.lucky.wifidemo.mvp;

import java.util.List;

/**
 * Created by danfeng.wang on 2017/2/7.
 */

public interface IShopIndexView extends IView {
    void onGetRecommendGoods(List<ShopSkuItem> list,boolean isCacheResult);
    void onGetRecommendFailed(String msg,boolean isCacheResult);
    void onRecommonedEmpty(boolean isCacheResult);
}
