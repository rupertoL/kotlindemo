package com.project.lp.hometabdemo.order;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import com.project.lp.tabviewpager.ViewPager.BaseFragment;

import java.util.HashMap;
import java.util.List;

/**
 * @author lf 主标签页面
 */
public class SameFragmentFactory<T> {
    @SuppressLint("UseSparseArrays")
    public static HashMap<Integer, BaseFragment> fragmentMap = new HashMap<Integer, BaseFragment>();
    private List<Class> datas = null;
    private boolean isAllEqual;
    private static SameFragmentFactory fragmentFactory = null;

    public static SameFragmentFactory setMyFragment(List<Class> datas, boolean isAllEqual) {
        fragmentFactory = new SameFragmentFactory();
        fragmentFactory.datas = datas;
        fragmentFactory.isAllEqual = isAllEqual;
        return fragmentFactory;
    }

    /**
     * 根据位置获取对应位置的fragment对象
     *
     * @param position
     */
    public Fragment create(int position) throws IllegalAccessException, InstantiationException {
        // (一.优化加缓存 第一步) 缓存Fragment
        BaseFragment fragment = fragmentMap.get(position);
        if (fragment == null) {
            FragmentOrder fragmentOrder = null;
        if (null != datas && datas.size() > 0) {

           // [1]如果为空，创建并缓存

                if (isAllEqual) {
                    fragmentOrder = (FragmentOrder) datas.get(0).newInstance();
                    fragmentOrder.setType(position);
                    fragment =  fragmentOrder;
                }
//                if (position <= (datas.size() - 1)) {
//                    fragmentOrder = (FragmentOrder) datas.get(position).newInstance();
//                    fragmentOrder.setType(position);
//                }
            }

            fragmentMap.put(position, fragment);
    }
    // [2]不为空，直接返回
    return fragment;
}

    /**
     * 清空FragmentFactory的缓存
     */
    public void clearCache() {
        if (fragmentMap != null && fragmentMap.size() > 0) {// 不为空
            fragmentMap.clear();
        }
    }


}
