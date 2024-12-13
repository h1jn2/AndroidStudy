package com.example.kotlinstudy

import android.media.session.MediaSession.Token
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

class StudentFromServer (val id: Int, val name: String, val age: Int, val intro: String) {
    constructor(name: String, age: Int, intro: String): this(0, name, age, intro)
}

class YoutubeFromServer (val id: Int, val title: String, val content: String, val video: String, val thumbnail: String)

class MelonFromServer (val id: Int, val title: String, val song: String, val thumbnail: String)

class User(val username: String, val token: String, val id: Int)

class InstaPost(val id: Int, val content: String, val image: String, val owner_profile: OwnerProfile)
class OwnerProfile(val username: String, val image: String)

class UserInfo(val id: Int, val userName: String, val profile: OwnerProfile)

interface RetrofitService {
    @GET("user/userInfo/")
    fun getUserInfo(@HeaderMap header: Map<String, String>): Call<UserInfo>

    @Multipart
    @PUT("user/profile/{user_id}/")
    fun changeProfile(
        @Path("user_id") userId: Int,
        @HeaderMap headers: Map<String, String>,
        @Part image: MultipartBody.Part,
        @Part ("user") user: RequestBody
    ): Call<Any>

    @Multipart
    @POST("instagram/post/")
    fun uploadPost(
        @HeaderMap headers: Map<String, String>,
        @Part image: MultipartBody.Part,
        @Part("content") content: RequestBody
    ): Call<Any>

    @POST("instagram/post/like/{post_id}/")
    fun postLikeClick(@Path("post_id") post_id: Int): Call<Any>

    @GET("instagram/post/list/all/")
    fun getInstaPosts(): Call<ArrayList<InstaPost>>

    @POST("user/login/")
    @FormUrlEncoded
    fun loginInstaUser(@FieldMap params: HashMap<String, Any>): Call<User>

    @POST("user/signup/")
    @FormUrlEncoded
    fun createInstaUser(@FieldMap params: HashMap<String, Any>): Call<User>

    @GET("youtube/list/")
    fun getYoutubeList(): Call<ArrayList<YoutubeFromServer>>

    @GET("melon/list/")
    fun getMelonList(): Call<ArrayList<MelonFromServer>>

    @GET("json/students/")
    fun getStudentList(): Call<ArrayList<StudentFromServer>>

    @POST("json/students/")
    fun createStudent(@Body params: HashMap<String, Any>): Call<StudentFromServer>

    @POST("json/students/")
    fun createEasyStudent(@Body student: StudentFromServer): Call<StudentFromServer>
}