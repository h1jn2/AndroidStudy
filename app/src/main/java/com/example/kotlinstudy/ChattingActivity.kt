package com.example.kotlinstudy

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChattingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chatting)

        var chatList = mutableListOf<Chatting>()
        val recyclerView = findViewById<RecyclerView>(R.id.chatting_recyclerview)
        for (i in 1..8) {
            chatList.add(Chatting(false, "안녕하세요"))
            chatList.add(
                Chatting(
                    true,
                    "네 안녕하세요"
                )
            )
        }
        recyclerView.adapter = ChattingRecyclerAdapter(chatList, LayoutInflater.from(this))
        recyclerView.layoutManager = GridLayoutManager(this@ChattingActivity, 1)
    }
}

class Chatting(val isMine: Boolean, val text: String)

class ChattingRecyclerAdapter(var chatList: MutableList<Chatting>, var inflater: LayoutInflater) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class MineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatText: TextView

        init {
            chatText = itemView.findViewById(R.id.mine_text)
        }
    }

    inner class NonMineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val chatText: TextView

        init {
            chatText = itemView.findViewById(R.id.non_mine_text)
        }
    }

    override fun getItemViewType(position: Int): Int {
        when (chatList.get(position).isMine) {
            true -> return 0
            false -> return 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val mineView = inflater.inflate(R.layout.chatting_mine_item, null)
        val nonMineView = inflater.inflate(R.layout.chatting_non_mine_item, null)
        when (viewType) {
            0 -> return MineViewHolder(mineView)
            else -> return NonMineViewHolder(nonMineView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val chat = chatList.get(position)

        when (chat.isMine) {
            true -> (holder as MineViewHolder).chatText.text = chat.text
            false -> (holder as NonMineViewHolder).chatText.text = chat.text
        }

    }

    override fun getItemCount(): Int {
        return chatList.size
    }
}