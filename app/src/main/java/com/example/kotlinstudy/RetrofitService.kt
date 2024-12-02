package com.example.kotlinstudy

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class StudentFromServer (val id: Int, val name: String, val age: Int, val intro: String) {
    constructor(name: String, age: Int, intro: String): this(0, name, age, intro)
}

class YoutubeFromServer (val id: Int, val title: String, val content: String, val video: String, val thumbnail: String)

interface RetrofitService {
    @GET("youtube/list/")
    fun getYoutubeList(): Call<ArrayList<YoutubeFromServer>>

    @GET("json/students/")
    fun getStudentList(): Call<ArrayList<StudentFromServer>>

    @POST("json/students/")
    fun createStudent(@Body params: HashMap<String, Any>): Call<StudentFromServer>

    @POST("json/students/")
    fun createEasyStudent(@Body student: StudentFromServer): Call<StudentFromServer>
}