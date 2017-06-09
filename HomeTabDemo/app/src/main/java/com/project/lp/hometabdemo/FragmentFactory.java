package com.project.lp.hometabdemo;

import android.annotation.SuppressLint;
import android.support.v4.app.Fragment;

import java.util.HashMap;
import java.util.List;

/**
 * @author lf 主标签页面
 */
public class FragmentFactory {
    @SuppressLint("UseSparseArrays")
    private static HashMap<Integer, Fragment> fragmentMap = new HashMap<Integer, Fragment>();
    private List<Class> datas = null;
    private static FragmentFactory fragmentFactory = null;

    public static FragmentFactory setMyFragment(List<Class> datas) {
        fragmentFactory = new FragmentFactory();
        fragmentFactory.datas = datas;
        return fragmentFactory;
    }

    /**
     * 根据位置获取对应位置的fragment对象
     *
     * @param position
     */
    public Fragment create(int position) throws IllegalAccessException, InstantiationException {
        // (一.优化加缓存 第一步) 缓存Fragment
        Fragment fragment = fragmentMap.get(position);
        if (null != datas && datas.size() > 0) {
            if (fragment == null) {// [1]如果为空，创建并缓存
                    if (position <= (datas.size() - 1)) {
                        fragment = (Fragment) datas.get(position).newInstance();

                }
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
