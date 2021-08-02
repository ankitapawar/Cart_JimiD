package com.bccannco.admin.Orders

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.OrderList

class ViewOrderAdapter(
    val context: Context?,
    val _interface: OrderFragmentInterface,
    val orderData: OrderList
) :
    RecyclerView.Adapter<ViewOrderAdapter.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val Tv_OrderId = view.findViewById<TextView>(R.id.Tv_OrderId)
        val Tv_Status = view.findViewById<TextView>(R.id.Tv_Status)
        val Tv_Price = view.findViewById<TextView>(R.id.Tv_Price)
        val Tv_Times = view.findViewById<TextView>(R.id.Tv_Times)
        val Tv_Address = view.findViewById<TextView>(R.id.Tv_Address)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.raw_view_order, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val order: OrderList.Datum = orderData.data.get(position)

        holder.Tv_OrderId.text = order.name + " " + order.orderId
        holder.Tv_Price.text = ""+order.total.substring(0,(order.total.indexOf(".")+3))
        holder.Tv_Status.text = order.status

        holder.itemView.setOnClickListener({
            _interface.OnItemClickEvent(order.orderId,order.status)
        })
    }

    override fun getItemCount(): Int {
//        return orderData.data.size
        return orderData.data.size
    }
}