package com.bccannco.admin.Orders

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.bccannco.admin.BaseFragment
import com.bccannco.admin.Orders.OrderDetail.OrderDetailsActivity
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.GetBatch
import com.bccannco.admin.apis.ApiResponseModel.OrderList
import com.bccannco.admin.comman.*


class OrderFragment : BaseFragment(), OrderFragmentInterface {

    private val TAG = "OrderFragment"
    val NEW_SPINNER_ID = 1
//    var languages = context?.resources?.getStringArray(R.array.array_status)

    lateinit var _context: Context
    lateinit var Rv_OrderList: RecyclerView
    lateinit var Sp_filter: Spinner
    lateinit var _adapter: ViewOrderAdapter
    lateinit var layout: LinearLayoutManager
    lateinit var model: OrderviewModel
    lateinit var pref: SharedPreferences
    lateinit var edit: SharedPreferences.Editor
    lateinit var map: HashMap<String, Int>
    lateinit var jsonToString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_order, container, false)
        model = OrderviewModel(context, this)
        context?.let {
            pref = it.getSharedPreferences(MYPREF, Application.MODE_PRIVATE)
        }
        _context = container?.context as Context
        prefInit(_context)
        loaderInit(view)
        showDialog()
        edit = pref.edit()

        jsonToString = pref.getString(ORDER_STATUS, "ERROR").toString()
        val type = object : TypeToken<HashMap<String?, Int?>?>() {}.type
        map = Gson().fromJson(jsonToString, type)


        adapterInit(view)
        Sp_filter = view.findViewById(R.id.Sp_filter)
        var adapter =
            context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    resources.getStringArray(R.array.array_status)
                )
            }
        Sp_filter.adapter = adapter

        Sp_filter.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                Log.e(TAG, "onItemSelected: " + parent.getItemAtPosition(position).toString())
                showDialog()
                if (parent.getItemAtPosition(position).toString().equals("All Orders")) {
                    if (isNetworkAvailable()) {
                        Log.e(TAG, "onCreateView * : ")
                        model.GetOrdeList(get_StringPref(BEARER_TOKEN, ""))

                    } else {
                        hideDialog()
                        toast("Please check your internet connectivity.")
                    }
                } else {
                    if (isNetworkAvailable()) {
                        model.GetOrdeListFromStatus(
                            get_StringPref(BEARER_TOKEN, ""),
                            map.get(parent.getItemAtPosition(position).toString()) as Int
                        )
                    } else {
                        hideDialog()
                        toast("Please check your internet connectivity.")
                    }
                }
//                Toast.makeText(
//                    context,
//                    "Selected Item: " +
//                            "" + map.get(parent.getItemAtPosition(position).toString()),
//                    Toast.LENGTH_SHORT
//                ).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.e(TAG, "onNothingSelected: ")
                // write code to perform some action
            }
        }
//        if (isNetworkAvailable()) {
//            Log.e(TAG, "onCreateView * : ")
//            model.GetOrdeList(get_StringPref(BEARER_TOKEN, ""))
//
//        } else {
//            toast("Please check your internet connectivity.")
//        }
        return view
    }

    private fun adapterInit(view: View) {
        Rv_OrderList = view.findViewById(R.id.Rv_OrderList)
        layout = LinearLayoutManager(context)
        Rv_OrderList.layoutManager = layout

    }

    override fun OnItemClickEvent(position: Int, status: String) {
        startActivity(
            Intent(context, OrderDetailsActivity::class.java).putExtra(
                ORDER_ID,
                position
            ).putExtra(STATUS, status)
        )
    }

    override fun <T> OnSuccess(result: T, identifier: Int) {
        when (identifier) {
            OrderListapiIdentifier -> {
                Log.e(TAG, "OnSuccess: ")
                hideDialog()
                _adapter = ViewOrderAdapter(context, this, result as OrderList)
                Rv_OrderList.adapter = _adapter
                _adapter.notifyDataSetChanged()
            }
        }
    }

    override fun <T> OnError(result: T) {
        Log.e(TAG, "OnError: ")
        hideDialog()
        toast(result)
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume:---------------------------------------- ")
        if (isNetworkAvailable()) {
            Log.e(TAG, "onCreateView * : ")
            model.GetOrdeList(get_StringPref(BEARER_TOKEN, ""))

        } else {
            toast("Please check your internet connectivity.")
        }
    }
}