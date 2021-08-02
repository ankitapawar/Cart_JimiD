package com.bccannco.admin.home.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.OrderList
import java.util.*

class OrderListAdapter(var mcontext: Context?, var minterface: HomeFragInterface, val list: List<OrderList>) :
    RecyclerView.Adapter<OrderListAdapter.Holder>()
{
    class Holder(view: View) : RecyclerView.ViewHolder(view)
    {
        val Tv_price = view.findViewById<TextView>(R.id.Tv_Price)
        val Tv_Status = view.findViewById<TextView>(R.id.Tv_Status)
        val Tv_OrderId = view.findViewById<TextView>(R.id.Tv_OrderId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder
    {
        val view = LayoutInflater.from(mcontext).inflate(R.layout.raw_dashbord_order, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int)
    {
//        Collections.reverse(list)
        val orderList: OrderList.Datum = list.get(0).data.get(position)
        holder.Tv_OrderId.text = orderList.name + " " + orderList.orderId
        holder.Tv_Status.text = orderList.status
        holder.Tv_price.text = "$" + orderList.total
        holder.itemView.setOnClickListener({ minterface.OnOrderListClick(orderList.orderId, orderList.status) })
    }

    override fun getItemCount(): Int
    {
        return list.get(0).data.size
    }
}