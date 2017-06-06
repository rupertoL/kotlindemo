package com.project.lp.kotlintest

import android.content.Context
import android.widget.BaseAdapter

abstract class MyBaseAdapter<T>(protected var list: List<T>?, protected var context: Context) : BaseAdapter() {
    override fun getCount(): Int {
        // 重要：
        if (list != null && list!!.size > 0) {
            return list!!.size
        } else {
            return 0
        }
    }

    override fun getItem(position: Int): T {
        return list!![position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}
