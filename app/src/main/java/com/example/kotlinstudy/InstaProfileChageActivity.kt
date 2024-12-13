package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class InstaProfileChageActivity : AppCompatActivity() {
    var imageUri: Uri? = null
    var glide: RequestManager? = null
    lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_profile_chage)

        imageView = findViewById(R.id.insta_profile_change_img)
        glide = Glide.with(this)
        val imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                imageUri = it.data!!.data
                glide!!.load(imageUri).into(imageView)
            }
        imagePickerLauncher.launch(Intent(Intent.ACTION_PICK).apply {
            this.type = MediaStore.Images.Media.CONTENT_TYPE
        })

        findViewById<TextView>(R.id.insta_profile_change_button).setOnClickListener {
            val retrofit = Retrofit.Builder().baseUrl("http://mellowcode.org/")
                .addConverterFactory(GsonConverterFactory.create()).build()
            val retrofitService = retrofit.create(RetrofitService::class.java)

            val file = getRealFile(imageUri!!)
            val requestFile = RequestBody.create(
                MediaType.parse(
                    this.contentResolver.getType(imageUri!!)
                ), file
            )
            val header = HashMap<String, String>()
            val body = MultipartBody.Part.createFormData("image", file!!.name, requestFile)
            val sharedPreference = this.getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", "")
            val userId = sharedPreference.getString("user_id", "")!!.toInt()
            val user = RequestBody.create(MultipartBody.FORM, userId.toString())
            header.put("Authorization", "token " + token!!)

            retrofitService.changeProfile(userId, header, body, user)
                .enqueue(object : Callback<Any> {
                    override fun onResponse(p0: Call<Any>, p1: Response<Any>) {
                        if (p1.isSuccessful) {
                            Toast.makeText(this@InstaProfileChageActivity, "완료", Toast.LENGTH_LONG)
                                .show()
                            onBackPressed()
                        }
                    }

                    override fun onFailure(p0: Call<Any>, p1: Throwable) {
                        Toast.makeText(
                            this@InstaProfileChageActivity,
                            "실패" + p1.toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                })
        }
    }

    private fun getRealFile(uri: Uri): File? {
        var uri: Uri? = uri
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        if (uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        var cursor: Cursor? = this.contentResolver.query(
            uri!!, projection, null, null, MediaStore.Images.Media.DATE_MODIFIED + " desc"
        )
        if (cursor == null || cursor.columnCount < 1) {
            return null
        }
        val columnIndex: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        val path: String = cursor.getString(columnIndex)
        if (cursor != null) {
            cursor.close()
            cursor = null
        }
        return File(path)
    }
}