package com.example.kotlinstudy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        var carList = mutableListOf<Car>()
        for (i in 0..30) {
            carList.add(Car("" + i + "번째 자동차", "" + i + "번째 엔진"))
        }
        val adapter = ListViewAdapter(carList, LayoutInflater.from(this), this)

        // 어댑터 장착 방법
        val listView = findViewById<ListView>(R.id.list_view)
        listView.adapter = adapter

        // 리스너 장착 방법
        listView.setOnItemClickListener { parent, view, position, id ->
            val car: Car = adapter.carList.get(position)
            val nthCar = car.nthCar
            val nthEngine = car.nthEngine

            Toast.makeText(this, nthCar + "  " + nthEngine, Toast.LENGTH_LONG).show()
        }

        // 데이터 갱신 방법
        findViewById<TextView>(R.id.add_car).setOnClickListener {
            adapter.carList.add(Car("addCar", "addEngine"))
            adapter.notifyDataSetChanged()
        }
    }
}

class ListViewAdapter(
    val carList: MutableList<Car>,
    val layoutInflater: LayoutInflater,
    val context: Context
) :
    BaseAdapter() {
    // 데이터 개수 리턴
    override fun getCount(): Int {
        return carList.size
    }

    // 해당 번째(position)의 데이터 리턴
    override fun getItem(position: Int): Any {
        return carList.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    // 해당 번째 뷰를 리턴
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            // 재활용 불가능 -> 만들어 진 view가 없을 때
            view = layoutInflater.inflate(R.layout.car_item, null)  // view 생성
            holder = ViewHolder()   // holder에 ViewHolder 할당
            holder.nthImage = view.findViewById(R.id.car_image)     // holder에 item의 id 지정
            holder.nthCar = view.findViewById(R.id.nthCar)
            holder.nthEngine = view.findViewById(R.id.nthEngine)

            view.tag = holder   // view를 재홯용하기 위해 tag에 holder 할당
        } else {
            // 재활용 가능 -> 만들어 진 view가 있을 때
            holder = convertView.tag as ViewHolder  // 재활용 할 view를 viewholder에서 tag로 가져옴
            view = convertView
        }
        val car = carList.get(position)
        holder.nthImage?.setImageDrawable(
            context.resources.getDrawable(
                R.drawable.logo_kt,
                context.theme
            )
        )
        holder.nthCar?.text = car.nthCar
        holder.nthEngine?.text = car.nthEngine


//        val view = layoutInflater.inflate(R.layout.car_item, null)
//        val carImage = view.findViewById<ImageView>(R.id.car_image)
//        val nthCar = view.findViewById<TextView>(R.id.nthCar)
//        val nthEngine = view.findViewById<TextView>(R.id.nthEngine)
//
//        val car = carList.get(position)
//        carImage.setImageDrawable(context.resources.getDrawable(R.drawable.logo_kt, context.theme))
//        nthCar.text = car.nthCar
//        nthEngine.text = car.nthEngine

        return view
    }
}

class ViewHolder {
    var nthImage: ImageView? = null
    var nthCar: TextView? = null
    var nthEngine: TextView? = null
}