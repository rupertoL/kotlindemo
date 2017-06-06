package com.project.lp.kotlintest.Time

import android.app.Activity
import android.os.Bundle
import android.widget.ListView
import com.project.lp.kotlintest.R
import java.util.*

/**
 * 横向的进度显示

 * @author Administrator
 */
class VerticalTimeLineActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState)
        setContentView(R.layout.vertical_time_line_activity)
        //声明变量 as后面是数据的类型 kotlin语言是可以省略";"号的
        val listView = this.findViewById(R.id.listview) as ListView
        listView.dividerHeight = 0
        val timelineAdapter = TimelineAdapter(data, this)
        listView.adapter=   timelineAdapter
    }

    private val data: List<Schedule>
        get() {
            val list = ArrayList<Schedule>()

            //kotlin语言中的循环  0..9给的是i的范围
            for (i in 0..9) {
                val schedule = Schedule()
                schedule.context = "测试数据" + i
                val dt = Date()
                val time = dt.time + i * 10000
                schedule.Time = time
                list.add(schedule)

            }

            return list
        }
}

