package com.project.lp.tabviewpager.ViewPager;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

/**
 * Created by jz on 2017/6/8.
 */

public class CommonUtil {

    // 将指定childView从它的父View中移除
    public static void removeSelfFromParent(View childView) {
        if (childView != null) {
            ViewParent parentView = childView.getParent();
            if (parentView instanceof ViewGroup) {
                ViewGroup group = (ViewGroup) parentView;
                group.removeView(childView);
            }
        }
    }
}
