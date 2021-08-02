package com.bccannco.admin.Orders.OrderDetail

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.BasicOrderDetails
import com.bccannco.admin.apis.ApiResponseModel.OrderDetails
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ProductListAdapter(
    val context: Context,
    val _interface: OrderDetailInterface,
    val product: MutableList<BasicOrderDetails.Data.Product>
) :
    RecyclerView.Adapter<ProductListAdapter.Holder>() {
    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        val Tv_PoductName = view.findViewById<TextView>(R.id.Tv_ProductName)
        val Iv_ProductImage = view.findViewById<ImageView>(R.id.Iv_ProductImage)
        val Tv_Category = view.findViewById<TextView>(R.id.Tv_Category)
        val Tv_Qty = view.findViewById<TextView>(R.id.Tv_Qty)
        val Tv_Option = view.findViewById<TextView>(R.id.Tv_Option)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.raw_product, parent, false)

        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.Tv_PoductName.text = product.get(position).name
        holder.Tv_Qty.text = context.getString(R.string.qty_bold) +" "+ product.get(position).quantity
        Log.e("ProductDetailsop", ""+product.get(position).option)
        if(product.get(position).option != null)
        {
            holder.Tv_Option.text = ""+context.getString(R.string.option_bold) +" "+ product.get(position).option
        }

        Picasso.get().load(product.get(position).image).into(holder.Iv_ProductImage)
//        holder.Tv_Category.text = product.get(position).
        holder.itemView.setOnClickListener({ _interface.OnItemClickEvent(position) })
    }

    override fun getItemCount(): Int {
        return product.size
    }
}