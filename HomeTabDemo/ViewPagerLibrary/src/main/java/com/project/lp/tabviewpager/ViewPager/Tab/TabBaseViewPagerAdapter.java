package com.project.lp.tabviewpager.ViewPager.Tab;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.lp.tabviewpager.R;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * Created by jz on 2017/6/8.
 */

public abstract class TabBaseViewPagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {

    private LayoutInflater inflater;
    private String[] tabNames ;
    private int[] tabIcons ;
    private  Context mContext ;
    private  int tabLayoutId ;
    public TabBaseViewPagerAdapter(FragmentManager fragmentManager, String[] tabNames, int[] tabIcons, Context context ) {
        super(fragmentManager);
        this.tabLayoutId = R.layout.tab_main;
        init(tabNames,tabIcons,context);
    }
    public TabBaseViewPagerAdapter(FragmentManager fragmentManager, String[] tabNames, int[] tabIcons, Context context, int tabLayoutId ) {

        super(fragmentManager);
        init(tabNames,tabIcons,context);
        this.tabLayoutId = tabLayoutId;
    }

    private void  init(String[] tabNames, int[] tabIcons, Context context ){
        this.tabNames= tabNames;
        this.tabIcons= tabIcons;
        this.mContext = context;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return tabNames.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = inflater.inflate(tabLayoutId, container, false);
        }
        TextView textView = (TextView) convertView;
        textView.setText(tabNames[position]);
        if(null != tabIcons && tabIcons.length>0){
            textView.setCompoundDrawablesWithIntrinsicBounds(0, tabIcons[position], 0, 0);
        }
        return textView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        try {
            return setFragment(position);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public abstract Fragment setFragment(int position) throws InstantiationException, IllegalAccessException;

}