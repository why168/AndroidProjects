package com.github.why168.kotlinlearning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hello.text = "Hello, world!"


    }

    fun demo(source: List<Int>) {
        val list = ArrayList<Int>()
        // 'for'-loops work for Java collections:
        for (item in source) {
            list.add(item)
        }
        // Operator conventions work as well:
        for (i in 0..source.size - 1) {
            list[i] = source[i] // get and set are called
        }
    }

    fun calendarDemo() {
        val calendar = Calendar.getInstance()
        if (calendar.firstDayOfWeek == Calendar.SUNDAY) {  // call getFirstDayOfWeek()
            calendar.firstDayOfWeek = Calendar.MONDAY       // call setFirstDayOfWeek()
        }
    }
}