package com.example.kotlinstudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class FragmentOne: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // inflater: XML을 화면에서 사용할 준비를 함 (xml -> view)
        // container: fragment에서 사용할 XML의 부모뷰
        val view =  inflater.inflate(R.layout.one_fragment, container, false)
        (view.findViewById<TextView>(R.id.call_activity)).setOnClickListener {
            (activity as FragmentActivity).printTestLog()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val data: String? = arguments?.getString("key")
        Log.d("dataa", ""+data)
    }

    fun printTestLog() {
        Log.d("dataa", "printTestLog()")
    }

}