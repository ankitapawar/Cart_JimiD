package com.bccannco.admin.chats

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.CustomerList
import com.squareup.picasso.Picasso

class ChatlistAdapter(
    val _context: Context?,
    val _interface: ChatlistInterface,
    val customerList: CustomerList
) :
    RecyclerView.Adapter<ChatlistAdapter.Holder>() {

    class Holder(view: View) : RecyclerView.ViewHolder(view) {

        val Iv_new_badge = view.findViewById<ImageView>(R.id.Iv_new_badge)
        val Iv_Right = view.findViewById<ImageView>(R.id.Iv_Right)
        val Tv_CustomerName = view.findViewById<TextView>(R.id.Tv_CustomerName)
        val Tv_Time = view.findViewById<TextView>(R.id.Tv_Time)
        val Tv_StaffName = view.findViewById<TextView>(R.id.Tv_StaffName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(_context).inflate(R.layout.raw_chatlist, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val list = customerList.data.get(position)

        holder.Tv_CustomerName.text = list.name
        if (list.approved.isEmpty()) {
            holder.Iv_Right.visibility = View.GONE
            holder.Iv_new_badge.visibility = View.VISIBLE
        } else {
            holder.Iv_Right.visibility = View.VISIBLE
            holder.Iv_new_badge.visibility = View.GONE
        }
        holder.Tv_Time
        holder.itemView.setOnClickListener({
            _interface.OnItemClickEvent(list.customerId,list.name.toString())
        })
    }

    override fun getItemCount(): Int {
        return customerList.data.size
    }
}