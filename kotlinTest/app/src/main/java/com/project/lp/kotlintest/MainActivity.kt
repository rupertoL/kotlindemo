package com.project.lp.kotlintest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.project.lp.kotlintest.Time.VerticalTimeLineActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener {
    private var mContext: Context? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this
        //kotlin中可以直接用不用findViewById() 但是使用是需要导入import kotlinx.android.synthetic.main.activity_main.*
        btn_time_line.setOnClickListener(this)
    }


    override fun onClick(v: View) {
        // TODO Auto-generated method stub
        /**
         *  kotlin中的when语句和java比较像但是他必须有else(像java中的default)
         *  但是这个else必须有除非是枚举类型可以不用
         *  kotlin中的when必须是所有类型都包括 所有非枚举类型就必须要else分支
         */

        when (v.id) {
            R.id.btn_time_line -> {
                //声明一个变量
                intent = Intent(mContext, VerticalTimeLineActivity::class.java)
                startActivity(intent)
            }

            else -> {
            }
        }
    }


}
