package com.project.lp.hometabdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.project.lp.hometabdemo.HomeFragment.FragmentFour;
import com.project.lp.hometabdemo.HomeFragment.FragmentOne;
import com.project.lp.hometabdemo.HomeFragment.FragmentThree;
import com.project.lp.hometabdemo.HomeFragment.FragmentTow;
import com.project.lp.tabviewpager.ViewPager.Tab.TabBaseViewPagerAdapter;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FixedIndicatorView tabmain_indicator;
    private View centerView;
    private IndicatorViewPager indicatorViewPager;
    private String[] tabNames = {"主页", "消息", "发现", "我"};
    private int[] tabIcons = {R.drawable.btn_business_selector, R.drawable.btn_activity_selector, R.drawable.btn_count_selector,
            R.drawable.btn_us_selector};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        tabmain_indicator = (FixedIndicatorView) findViewById(R.id.tabmain_indicator);
        centerView = getLayoutInflater().inflate(R.layout.tab_main_center, tabmain_indicator, false);
        tabmain_indicator.setCenterView(centerView);
        indicatorViewPager = new IndicatorViewPager(tabmain_indicator, viewPager);
        indicatorViewPager.setAdapter(new TabBaseViewPagerAdapter(getSupportFragmentManager(),tabNames,tabIcons,this) {
            @Override
            public Fragment setFragment(int position) throws InstantiationException, IllegalAccessException {
                return FragmentFactory.setMyFragment(intData()).create(position);
            }
        });
        // 设置viewpager保留界面不重新加载的页面数量
        viewPager.setOffscreenPageLimit(tabNames.length);
        centerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeActivity.this,"你点啥！",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Class> intData() {
        List<Class> data = new ArrayList<>();
        data.add(FragmentOne.class);
        data.add(FragmentTow.class);
        data.add(FragmentThree.class);
        data.add(FragmentFour.class);
        return data;
    }
}
