package com.bccannco.admin.Orders.OrderDetail

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.BasicOrderDetails
import com.bccannco.admin.apis.ApiResponseModel.LoginResponseModel
import com.bccannco.admin.apis.ApiResponseModel.OrderDetails
import com.bccannco.admin.apis.ApiResponseModel.OrderList
import com.bccannco.admin.comman.BasicOrderDetailapiIdentifier
import com.bccannco.admin.comman.OrderDetailapiIdentifier
import com.bccannco.admin.comman.OrderStatusUpdateapiIdentifier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class OrderDetailModel(val context: Context, val _interface: OrderDetailInterface)
{
    lateinit var apiInterface: ApiInterface
    lateinit var call: Call<BasicOrderDetails>
    lateinit var call_UpdateStatus: Call<OrderList>

    fun OrderDetailApiCalling(token: String, orderId: Int)
    {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call = apiInterface.BasicOrderDetails(token, orderId)

        call.enqueue(object : Callback<BasicOrderDetails>
        {
            override fun onResponse(call: Call<BasicOrderDetails>, response: Response<BasicOrderDetails?>)
            {
                if (response.isSuccessful && response.body()?.success == 1)
                {
                    _interface.OnSuccess(response.body(), BasicOrderDetailapiIdentifier)
                }
                else
                {
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try
                    {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
                        _interface.OnError(errorResponse.error.get(0))
                    }
                    catch (ex: IOException)
                    {
                        ex.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<BasicOrderDetails>, t: Throwable)
            {
                _interface.OnError(t.message)
            }
        })
    }

    fun UpdateOrderStatusApiCalling(token: String, orderId: Int, status: String)
    {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call_UpdateStatus = apiInterface.UpdateOrderStatus(token, orderId, status)

        call_UpdateStatus.enqueue(object : Callback<OrderList>
        {
            override fun onResponse(call: Call<OrderList>, response: Response<OrderList?>)
            {
                if (response.isSuccessful && response.body()?.success == 1)
                {
                    println("status update" +" "+Gson().toJson(response.body()))
                    _interface.OnSuccess(status, OrderStatusUpdateapiIdentifier)
                }
                else
                {
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try
                    {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
                        _interface.OnError(errorResponse.error.get(0))
                    }
                    catch (ex: IOException)
                    {
                        ex.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<OrderList>, t: Throwable)
            {
                _interface.OnError(t.message)
            }
        })
    }

//    fun ProductListApiCalling() {
//
//        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
//        call = apiInterface.ProductList("")
//
//        call.enqueue(object : Callback<OrderDetails> {
//            override fun onResponse(
//                call: Call<OrderDetails>,
//                response: Response<OrderDetails?>
//            ) {
//                if (response.isSuccessful && response.body()?.success == 0) {
//                    _interface.OnSuccess(response.body())
//                } else {
//
////                    val parser = JsonParser()
////                    var mJson: JsonElement? = null
////                    try {
////                        mJson = parser.parse(response.errorBody()!!.string())
////                        val gson = Gson()
////                        val errorResponse: LoginResponseModel =
////                            gson.fromJson(mJson, LoginResponseModel::class.java)
////                        Toast.makeText(context, "" + errorResponse, Toast.LENGTH_SHORT).show()
////                    } catch (ex: IOException) {
////                        ex.printStackTrace()
////                    }
//                }
//            }
//
//            override fun onFailure(call: Call<OrderDetails>, t: Throwable) {
//                _interface.OnError(t.message)
//            }
//        })
//    }
}