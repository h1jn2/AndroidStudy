package com.example.kotlinstudy

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        val calText: TextView = findViewById(R.id.calc_text)
        val button1: Button = findViewById(R.id.calc_button1)
        val button2: Button = findViewById(R.id.calc_button2)
        val button3: Button = findViewById(R.id.calc_button3)
        val button4: Button = findViewById(R.id.calc_button4)
        val button5: Button = findViewById(R.id.calc_button5)
        val button6: Button = findViewById(R.id.calc_button6)
        val button7: Button = findViewById(R.id.calc_button7)
        val button8: Button = findViewById(R.id.calc_button8)
        val button9: Button = findViewById(R.id.calc_button9)
        val button0: Button = findViewById(R.id.calc_button0)
        val buttonPlus: Button = findViewById(R.id.calc_button_plus)
        val buttonEq: Button = findViewById(R.id.calc_button_eq)
        val buttonCa: Button = findViewById(R.id.calc_button_ca)
        val text = StringBuilder()
        var result: Int = 0

        fun appendFunc(num: Int) {
            text.append(num)
            calText.text = text.toString()
        }

        val buttonClickListener = object: View.OnClickListener {
            override fun onClick(v: View?) {
                when (v) {
                    button1 -> appendFunc(1)
                    button2 -> appendFunc(2)
                    button3 -> appendFunc(3)
                    button4 -> appendFunc(4)
                    button5 -> appendFunc(5)
                    button6 -> appendFunc(6)
                    button7 -> appendFunc(7)
                    button8 -> appendFunc(8)
                    button9 -> appendFunc(9)
                    button0 -> appendFunc(0)
                    buttonPlus -> {
                        if (!text.toString().equals("")) {
                            result += text.toString().toInt()
                            text.clear()
                            calText.text = ""
                        }
                        else
                            calText.text = ""
                    }
                    buttonCa -> {
                        text.clear()
                        result = 0
                        calText.text = ""
                    }
                    buttonEq -> {
                        if (!text.toString().equals("")) {
                            result += text.toString().toInt()
                            calText.text = result.toString()
                            text.clear()
                        }
                    }
                    else -> return
                }
                Log.d("hj", v.toString())
            }
        }

        button1.setOnClickListener(buttonClickListener)
        button2.setOnClickListener(buttonClickListener)
        button3.setOnClickListener(buttonClickListener)
        button4.setOnClickListener(buttonClickListener)
        button5.setOnClickListener(buttonClickListener)
        button6.setOnClickListener(buttonClickListener)
        button7.setOnClickListener(buttonClickListener)
        button8.setOnClickListener(buttonClickListener)
        button9.setOnClickListener(buttonClickListener)
        button0.setOnClickListener(buttonClickListener)
        buttonPlus.setOnClickListener(buttonClickListener)
        buttonEq.setOnClickListener(buttonClickListener)
        buttonCa.setOnClickListener(buttonClickListener)
    }
}