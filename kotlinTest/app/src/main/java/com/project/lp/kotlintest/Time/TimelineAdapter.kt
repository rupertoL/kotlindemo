package com.project.lp.kotlintest.Time

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.project.lp.kotlintest.MyBaseAdapter
import com.project.lp.kotlintest.R
import java.text.SimpleDateFormat
import java.util.*


class TimelineAdapter @SuppressLint("SimpleDateFormat")
constructor(list: List<Schedule>, context: Context) : MyBaseAdapter<Schedule>(list, context) {

    private val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view : View
        // TODO Auto-generated method stub

        //感觉和java一样不
        var viewHolder: ViewHolder? = null
        if (convertView == null) {
            val inflater = LayoutInflater.from(parent.context)
            view = inflater.inflate(R.layout.listview_item, null)

        }else {
            view = convertView
        }
        viewHolder = ViewHolder.getHolder(view)

        val titleStr = super.list!![position].context

        if (position == 0) {
            viewHolder.view_top_line.setBackgroundColor(Color
                    .parseColor("#00000000"))
        } else {
            viewHolder.view_top_line.setBackgroundColor(Color
                    .parseColor("#A6A6A6"))

        }

        if (null != super.list && super.list!!.size > 0 && position == super.list!!.size - 1) {
            viewHolder.view_bottom_line.setBackgroundColor(Color
                    .parseColor("#00000000"))
        } else {
            viewHolder.view_bottom_line.setBackgroundColor(Color
                    .parseColor("#A6A6A6"))

        }

        viewHolder.title.text = titleStr
        viewHolder.tv_time.text = sdf.format(Date(super.list!![position].Time))

        return view

    }

    internal class ViewHolder(convertView: View) {
        var view_top_line: View
        var view_bottom_line: View
        var title: TextView
        var tv_time: TextView

        init {
            view_top_line = convertView.findViewById(R.id.view_top_line) as View
            view_bottom_line = convertView
                    .findViewById(R.id.view_bottom_line) as View
            title = convertView.findViewById(R.id.title) as TextView
            tv_time = convertView.findViewById(R.id.tv_time) as TextView

        }

        companion object {

            fun getHolder(convertView: View): ViewHolder {
                var holder: ViewHolder? = null
                //判空操作
                if(null != convertView.tag){
                    //没有判空操作直接强转是如果为空就会类型错误
                    holder =   convertView.tag as ViewHolder
                }
                if (holder == null) {
                    holder = ViewHolder(convertView)
                    convertView.tag = holder
                }
                return holder
            }
        }
    }

}
