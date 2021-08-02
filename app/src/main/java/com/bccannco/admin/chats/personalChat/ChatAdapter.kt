package com.bccannco.admin.chats.personalChat

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.GetChat
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*

class ChatAdapter(
    var context: Context,
    var _interface: PersonalChatInterface,
    var list: List<GetChat.UserMsgDatum>
) :
    RecyclerView.Adapter<ChatAdapter.Holder>() {

    var type = 0

    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val message = view.findViewById<TextView>(R.id.Tv1)
        val Tv2 = view.findViewById<TextView>(R.id.Tv2)
        val img = view.findViewById<ImageView>(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatAdapter.Holder {

        val view = LayoutInflater.from(context).inflate(
            (if (type == 0) {
                R.layout.raw_mine
            } else {
                R.layout.raw_other
            }), parent, false
        )
//        val view_other = LayoutInflater.from(context).inflate(R.layout.raw_other, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: ChatAdapter.Holder, position: Int) {
        var model: GetChat.UserMsgDatum = list.get(position)
//        if (model.sentBy.equals("customer")) {
//            type = 0
//        } else {
//            type = 1
//        }
        val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
        val currentDate = sdf.format(Date())
        Log.e("TAG", "onBindViewHolder currentDate : " + currentDate.substring(0, 10))
        Log.e("TAG", "onBindViewHolder model.sentAt : " + model.sentAt.substring(0, 10))
        if (currentDate.substring(0, 10).equals(model.sentAt.substring(0, 10))) {
            Log.e("TAG", "\n\n\n onBindViewHolder length: " + model.sentAt)
            Log.e("TAG", "onBindViewHolder length: " + model.sentAt.substring(12, 16) + " \n\n\n")
            holder.Tv2.text = model.sentAt.substring(12, 16)
        } else {
//            val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
//            val past: Date = sdf.parse(model.sentAt.substring(0, 10))
            holder.Tv2.text = model.sentAt.substring(0, 16)
        }
        if (model.message.equals("")) {
            holder.img.visibility = View.VISIBLE
            holder.message.visibility = View.GONE
            Picasso.get()
                .load(model.image)
                .into(holder.img)
        } else {
            holder.img.visibility = View.GONE
            holder.message.visibility = View.VISIBLE
            holder.message.text = model.message
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        if (list.get(position).sentBy.equals("customer")) {
            type = 1
        } else {
            type = 0
        }
        return type
    }
}