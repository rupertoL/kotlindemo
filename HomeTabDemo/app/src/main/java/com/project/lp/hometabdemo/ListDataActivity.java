package com.project.lp.hometabdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.project.lp.hometabdemo.order.FragmentOrder;
import com.project.lp.hometabdemo.order.SameFragmentFactory;
import com.project.lp.tabviewpager.ViewPager.Tab.TabBaseViewPagerAdapter;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;

import java.util.ArrayList;
import java.util.List;

public class ListDataActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FixedIndicatorView indicator;
    private IndicatorViewPager indicatorViewPager;
    private String[] tabNames = {"待支付", "待配送", "配送中", "已完成"};
    private int type = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);
        type = getIntent().getIntExtra("type",-1);
        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById( R.id.viewPager);
        indicator = (FixedIndicatorView) findViewById(R.id.fragment_tabmain_indicator);
        indicator.setScrollBar(new ColorBar(getApplicationContext(), Color.RED, 5));
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        indicatorViewPager.setAdapter(new TabBaseViewPagerAdapter(getSupportFragmentManager(),tabNames,null,this) {
            @Override
            public Fragment setFragment(int position) throws InstantiationException, IllegalAccessException {
                return SameFragmentFactory.setMyFragment(intData(),true).create(position);
            }
        });
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(tabNames.length);
        if(type!= -1&& type!=0){
            viewPager.setCurrentItem(type);
        }
    }
    private List<Class> intData() {
        List<Class> data = new ArrayList<>();
        data.add(FragmentOrder.class);
        return data;
    }
}
