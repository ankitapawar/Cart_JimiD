package com.bccannco.admin.Orders.OrderDetail

import android.app.Application
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.bccannco.admin.BaseActivity
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.BasicOrderDetails
import com.bccannco.admin.comman.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_order_details.*
import kotlinx.android.synthetic.main.toolbar.*


class OrderDetailsActivity : BaseActivity(), OrderDetailInterface, View.OnClickListener
{
    //    lateinit var Sp_filter: Spinner
    private val TAG = "OrderDetailsActivity"
    lateinit var layout: LinearLayoutManager
    lateinit var adapter: ProductListAdapter

    lateinit var pref: SharedPreferences
    lateinit var edit: SharedPreferences.Editor
    lateinit var map: HashMap<String, Int>
    lateinit var jsonToString: String
    lateinit var model: OrderDetailModel
    lateinit var status: String
    var order_id: Int = 0

    var IsFirstTimeSet = true

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_details)

//        supportActionBar?.title = "Order Details"
        order_id = intent.getIntExtra(ORDER_ID, 0)
        status = intent.getStringExtra(STATUS).toString()

        Iv_Back.setOnClickListener(this)

        Tv_Title.text = "Order Details"

        model = OrderDetailModel(this, this)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.array_status_detail))

        pref = getSharedPreferences(MYPREF, Application.MODE_PRIVATE)

        edit = pref.edit()

        jsonToString = pref.getString(ORDER_STATUS, "ERROR").toString()
        val type = object : TypeToken<HashMap<String?, Int?>?>() {}.type
        map = Gson().fromJson(jsonToString, type)

        et_order_notes.setOnTouchListener(OnTouchListener
        { v, event -> if (et_order_notes.hasFocus())
            {
                v.parent.requestDisallowInterceptTouchEvent(true)
                when (event.action and MotionEvent.ACTION_MASK)
                {
                    MotionEvent.ACTION_SCROLL ->
                    {
                        v.parent.requestDisallowInterceptTouchEvent(false)
                        return@OnTouchListener true
                    }
                }
            }
            false
        })

        Sp_filter.adapter = adapter

        Sp_filter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
            {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long)
                {
//                    Toast.makeText(
//                        this@OrderDetailsActivity,
//                        "Selected Item: " +
//                                "" + map.get(parent.getItemAtPosition(position).toString()),
//                        Toast.LENGTH_SHORT
//                    ).show()
                    Log.e(TAG, "onItemSelected: " + parent.getItemAtPosition(position).toString())

                    if (!IsFirstTimeSet)
                    {
                        model.UpdateOrderStatusApiCalling(get_StringPref(BEARER_TOKEN, ""), order_id,
                            parent.getItemAtPosition(position).toString())
//                        Tv_Spinner.text = parent.getItemAtPosition(position).toString()
                    }
                    IsFirstTimeSet = false
                    Log.e(TAG, "onItemSelected IsFirstTimeSet : "+IsFirstTimeSet )
                }
                override fun onNothingSelected(parent: AdapterView<*>)
                {
                    // write code to perform some action
                }
            }
        showDialog()
        model.OrderDetailApiCalling(get_StringPref(BEARER_TOKEN, ""), order_id)
        adapterInit()
    }

    private fun adapterInit()
    {
        layout = LinearLayoutManager(this)
        Rv_ProductList.layoutManager = layout
    }

    override fun OnItemClickEvent(position: Int)
    {
//        toast("" + position)
    }

    private fun SetOrderDetails(orderDetials: BasicOrderDetails)
    {
        Log.e("BasicOrderDetails", Gson().toJson(orderDetials))

        Tv_OrderId.text = "Order " + orderDetials.data.order.get(0).orderId
        Tv_Status.text = status
        Tv_Name.text = orderDetials.data.order.get(0).firstname + " " + orderDetials.data.order.get(0).lastname

        val addressBuilder = StringBuilder()

        if((! orderDetials.data.order.get(0).shippingAddress1.isNullOrEmpty()) ||
            (! orderDetials.data.order.get(0).shippingAddress1.isNullOrBlank()))
        {
            addressBuilder.append(orderDetials.data.order.get(0).shippingAddress1)
            addressBuilder.append(", ")
        }

        if((! orderDetials.data.order.get(0).shippingAddress2.isNullOrEmpty()) ||
            (! orderDetials.data.order.get(0).shippingAddress2.isNullOrBlank()))
        {
            addressBuilder.append(orderDetials.data.order.get(0).shippingAddress2)
            addressBuilder.append(", ")
        }

        if((! orderDetials.data.order.get(0).shippingCity.isNullOrEmpty()) ||
            (! orderDetials.data.order.get(0).shippingCity.isNullOrBlank()))
        {
            addressBuilder.append(orderDetials.data.order.get(0).shippingCity)
            addressBuilder.append(", ")
        }
        if((! orderDetials.data.order.get(0).shippingPostcode.isNullOrEmpty()) ||
            (! orderDetials.data.order.get(0).shippingPostcode.isNullOrBlank()))
        {
            addressBuilder.append(orderDetials.data.order.get(0).shippingPostcode)
            addressBuilder.append(", ")
        }
        if((! orderDetials.data.order.get(0).shippingZone.isNullOrEmpty()) ||
            (! orderDetials.data.order.get(0).shippingZone.isNullOrBlank()))
        {
            addressBuilder.append(orderDetials.data.order.get(0).shippingZone)
        }

        Tv_Address.text = addressBuilder.toString()

        if((! orderDetials.data.order.get(0).shippingMethod.isNullOrEmpty()) ||
            (! orderDetials.data.order.get(0).shippingMethod.isNullOrBlank()))
        {
            layout_shipping_method.visibility = View.VISIBLE
            Tv_shipping_method.setText(""+orderDetials.data.order.get(0).shippingMethod)
        }
        else
        {
            layout_shipping_method.visibility = View.GONE
        }


        if((! orderDetials.data.order.get(0).comment.isNullOrEmpty()) ||
            (! orderDetials.data.order.get(0).comment.isNullOrBlank()))
        {
            layout_comment.visibility = View.VISIBLE
            Tv_Comment.setText(""+orderDetials.data.order.get(0).comment)
        }
        else
        {
            layout_comment.visibility = View.GONE
        }

        Tv_Email.text = orderDetials.data.order.get(0).email
//        val array: Array<Pair<String, Int>> = map.toList().toTypedArray();
        val status = resources.getStringArray(R.array.array_status_detail).toList()

        Log.e(TAG, "SetOrderDetails: " + status)

        IsFirstTimeSet = true

        Sp_filter.setSelection(status.indexOf(this.status))

        Tv_Spinner.text = this.status

        Log.e("ProductDetails", Gson().toJson(orderDetials.data.products))

        if (orderDetials.data.products.size > 0)
        {
            adapter = ProductListAdapter(this, this, orderDetials.data.products)
            Rv_ProductList.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
    override fun <T> OnSuccess(result: T, identifier: Int)
    {
        Log.e(TAG, "OnSuccess: ")
        when (identifier)
        {
            BasicOrderDetailapiIdentifier ->
            {
                SetOrderDetails(result as BasicOrderDetails)
                hideDialog()
            }
            OrderStatusUpdateapiIdentifier ->
            {
                toast("Order status is updated.")
                Tv_Status.text = result as String
                Tv_Spinner.text = result as String
            }
        }
    }
    override fun <T> OnError(result: T)
    {
        hideDialog()
        Log.e(TAG, "OnError: " + result)
    }
    override fun onClick(p0: View?)
    {
        when (p0)
        {
            Iv_Back ->
            {
                onBackPressed()
            }
        }
    }
}