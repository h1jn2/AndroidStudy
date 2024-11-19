package com.example.kotlinstudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val carList = mutableListOf<Car>()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        for (i in 1..30) {
            carList.add(Car("" + i + "번째 자동차", "" + i + "번째 엔진"))
        }
        // 리사이클러뷰에 어댑터 장착 
        recyclerView.adapter = RecyclerViewAdapter(carList, LayoutInflater.from(this))
        // 리사이클러뷰에 레이아웃 매니저 장착
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}

class RecyclerViewAdapter(var carList: MutableList<Car>, var inflater: LayoutInflater) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    // itemView의 상세 view 컴포넌트 홀드
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carImage: ImageView
        val nthCar: TextView
        val nthEngine: TextView

        init {
            carImage = itemView.findViewById(R.id.car_image)
            nthCar = itemView.findViewById(R.id.nthCar)
            nthEngine = itemView.findViewById(R.id.nthEngine)
            itemView.setOnClickListener {
                val position: Int = adapterPosition
                val car = carList.get(position)
                Log.d("testt", car.nthCar)
            }
        }
    }
    // itemView 리턴
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.car_item, parent, false)
        return ViewHolder(view)
    }
    // 데이터와 아이템 뷰 컴포넌트 바인딩
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nthCar.text = carList.get(position).nthCar
        holder.nthEngine.text = carList.get(position).nthEngine
    }

    override fun getItemCount(): Int {
        return carList.size
    }


}