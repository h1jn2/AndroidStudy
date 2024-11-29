package com.example.kotlinstudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.zip.Inflater

class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getStudentList().enqueue(object : Callback<ArrayList<StudentFromServer>> {
            override fun onResponse(
                p0: Call<ArrayList<StudentFromServer>>,
                p1: Response<ArrayList<StudentFromServer>>
            ) {
                if (p1.isSuccessful) {
                    val studentList = p1.body()
                    findViewById<RecyclerView>(R.id.recycler_view).apply {
                        this.adapter = StudentListRecyclerViewHolder(studentList!!, LayoutInflater.from(this@RetrofitActivity))
                        this.layoutManager = LinearLayoutManager(this@RetrofitActivity)
                    }
                }
            }

            override fun onFailure(p0: Call<ArrayList<StudentFromServer>>, p1: Throwable) {
            }
        })

        findViewById<TextView>(R.id.create_student).setOnClickListener {
            val student = HashMap<String, Any>()
            student.put("name", "name")
            student.put("age", 30)
            student.put("intro", "intro")
            retrofitService.createStudent(student).enqueue(object : Callback<StudentFromServer> {
                override fun onResponse(
                    p0: Call<StudentFromServer>,
                    p1: Response<StudentFromServer>
                ) {
                    if (p1.isSuccessful) {
                        val student = p1.body()
                        Log.d("testt", "" + student!!.name)
                    }
                }

                override fun onFailure(p0: Call<StudentFromServer>, p1: Throwable) {
                }
            })
        }

        val student = StudentFromServer("name", 300, "intro")
        findViewById<TextView>(R.id.create_easy_student).setOnClickListener {
            retrofitService.createEasyStudent(student).enqueue(object : Callback<StudentFromServer> {
                override fun onResponse(p0: Call<StudentFromServer>, p1: Response<StudentFromServer>) {
                    if (p1.isSuccessful) {
                        val student = p1.body()
                        Log.d("testt", "" + student!!.name)
                    }            }

                override fun onFailure(p0: Call<StudentFromServer>, p1: Throwable) {
                }
            })
        }

    }
}

class StudentListRecyclerViewHolder(
    var studentList: ArrayList<StudentFromServer>,
    var inflater: LayoutInflater
): RecyclerView.Adapter<StudentListRecyclerViewHolder.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val studentName: TextView
        val studentAge: TextView
        val studentIntro: TextView

        init {
            studentName = itemView.findViewById(R.id.student_name)
            studentAge = itemView.findViewById(R.id.student_age)
            studentIntro = itemView.findViewById(R.id.student_intro)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.student_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.studentName.text = studentList.get(position).name
        holder.studentAge.text = studentList.get(position).age.toString()
        holder.studentIntro.text = studentList.get(position).intro
    }

    override fun getItemCount(): Int {
        return studentList.size
    }
}