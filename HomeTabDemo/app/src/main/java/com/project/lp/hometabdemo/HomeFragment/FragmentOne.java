package com.project.lp.hometabdemo.HomeFragment;

import android.view.View;
import android.widget.TextView;

import com.project.lp.hometabdemo.R;
import com.project.lp.tabviewpager.ViewPager.BaseFragment;

/**
 * Created by jz on 2017/6/8.
 */


public class FragmentOne extends BaseFragment {

    //如果使用module中的BaseFragmen那么这里就必须有返回 因为通过有无数据判断该页面是否是正常页面
    @Override
    protected Object loadData() {
        return "FragmentOne";
    }

    @Override
    protected View getSucessView() {
        View view = View.inflate(getActivity(), R.layout.tab_content,null);
        ((TextView)view.findViewById(R.id.tv_content)).setText("首页");
        return view;
    }
}
