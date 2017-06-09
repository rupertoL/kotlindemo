package com.project.lp.hometabdemo.order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.project.lp.hometabdemo.R;
import com.project.lp.tabviewpager.ViewPager.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jz on 2017/6/8.
 */


public class FragmentOrder extends BaseFragment {
    private int type = -1;

    private List<String> myData = new ArrayList<>();
    private MyAdapter myAdapter;

    //如果使用module中的BaseFragmen那么这里就必须有返回 因为通过有无数据判断该页面是否是正常页面
    @Override
    protected Object loadData() {
        Object object = null;
        if (type != -1) {

            switch (type) {
                case 0:
                    object  =  initData(type,10);
                    break;
                case 1:

                    break;
                case 2:
                    object =  initData(type,30);
                    break;
                case 3:
                    object =  initData(type,0);
                    break;
            }

        }
        myData = (List<String>) object;
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //UI线程更新
                myAdapter.getUpdate(myData);
                Toast.makeText(getContext(),"页面收据更新"+type,Toast.LENGTH_SHORT).show();
            }
        });


        return object;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    protected View getSucessView() {
        View view = View.inflate(getActivity(), R.layout.list_data_content, null);
        ListView listview = (ListView) view.findViewById(R.id.listview);
        myAdapter = new MyAdapter(myData,getActivity());
        listview.setAdapter(myAdapter);
        //重新加载调用该方法 contentPage.loadDataAndRefreshView();
        return view;
    }


    private List<String> initData(int type,int count) {
        List<String> lists = new ArrayList<>();
        if (count <= 0) {
            return lists;
        }
        for (int i = 0; i < count; i++) {
            lists.add(type+"测试数据" + i);
        }

        return lists;
    }

    class MyAdapter extends BaseAdapter{

        private List<String> datas ;
        private Context mConext;
        public  MyAdapter(List<String > data, Context context){
            this.datas = data;
            this.mConext = context;

        }

        public void getUpdate(List<String > data) {

            this.datas = data;
            this.notifyDataSetChanged();
        }

        @Override
        public int getCount() {

            return null == datas?0:datas.size();
        }

        @Override
        public Object getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(null ==convertView ){
               TextView textView = new TextView(mConext);
                textView.setPadding(20,20,20,20);
                convertView = textView;
            }
            ((TextView)convertView).setText((String)this.getItem(position));
            return convertView;
        }
    }


}
