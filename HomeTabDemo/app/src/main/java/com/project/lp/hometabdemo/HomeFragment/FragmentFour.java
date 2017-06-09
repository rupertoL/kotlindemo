package com.project.lp.hometabdemo.HomeFragment;

import android.view.View;
import android.widget.TextView;

import com.project.lp.hometabdemo.R;
import com.project.lp.tabviewpager.ViewPager.BaseFragment;

/**
 * Created by jz on 2017/6/8.
 */

public class FragmentFour extends BaseFragment {

    @Override
    protected Object loadData() {
        return "FragmentFour";
    }

    @Override
    protected View getSucessView() {
        View view = View.inflate(getActivity(), R.layout.tab_content,null);
        ((TextView)view.findViewById(R.id.tv_content)).setText("我的");

        return view;
    }
}
