package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.collection.arrayMapOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import kotlin.io.path.Path

class InstaPostFragment : Fragment() {
    var imageUri: Uri? = null
    var contentInput: String = ""
    lateinit var selectedContent: EditText
    lateinit var selectedImageView: ImageView
    lateinit var upload: TextView
    lateinit var imagePickerLauncher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_insta_post, container, false)

    }

    fun makePost() {
        val retrofit = Retrofit.Builder().baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        imagePickerLauncher.launch(Intent(Intent.ACTION_PICK).apply {
            this.type = MediaStore.Images.Media.CONTENT_TYPE
        })

        selectedContent.doAfterTextChanged {
            contentInput = it.toString()
        }

        upload.setOnClickListener {
            val file = getRealFile(imageUri!!)
            val requestFile = RequestBody.create(
                MediaType.parse(
                    (activity as InstaMainActivity).contentResolver.getType(imageUri!!)
                ), file
            )
            val content = RequestBody.create(MultipartBody.FORM, contentInput)
            val header = HashMap<String, String>()
            val body = MultipartBody.Part.createFormData("image", file!!.name, requestFile)
            val sharedPreference = (activity as InstaMainActivity).getSharedPreferences("user_info", Context.MODE_PRIVATE)
            val token = sharedPreference.getString("token", "")
            Log.d("instaa", token!!)
            header.put("Authorization", "token " + token!!)

            retrofitService.uploadPost(header, body, content).enqueue(object: Callback<Any> {
                override fun onResponse(p0: Call<Any>, p1: Response<Any>) {

                }

                override fun onFailure(p0: Call<Any>, p1: Throwable) {
                }
            })
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedContent = view.findViewById(R.id.insta_post_edit)
        selectedImageView = view.findViewById(R.id.insta_post_image)
        upload = view.findViewById(R.id.insta_post_button)

        val glide = Glide.with(activity as InstaMainActivity)
        imagePickerLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                imageUri = it.data!!.data
                glide.load(imageUri).into(selectedImageView)
            }

    }

    private fun getRealFile(uri: Uri): File? {
        var uri: Uri? = uri
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        if (uri == null) {
            uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        var cursor: Cursor? = (activity as InstaMainActivity).contentResolver.query(
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