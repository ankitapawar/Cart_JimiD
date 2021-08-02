package com.bccannco.admin.Orders

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.LoginResponseModel
import com.bccannco.admin.apis.ApiResponseModel.OrderList
import com.bccannco.admin.comman.OrderListapiIdentifier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class OrderviewModel(val context: Context?, val _interface: OrderFragmentInterface) {

    lateinit var apiInterface: ApiInterface
    lateinit var call: Call<OrderList>

    fun GetOrdeList(token: String) {

        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call = apiInterface.OrderList(token)

        call.enqueue(object : Callback<OrderList> {
            override fun onResponse(
                call: Call<OrderList>,
                response: Response<OrderList?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), OrderListapiIdentifier)
                } else {
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)

                        _interface.OnError(errorResponse)
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<OrderList>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun GetOrdeListFromStatus(token: String, statusId: Int) {

        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call = apiInterface.OrderListFormStatus(token, statusId)

        call.enqueue(object : Callback<OrderList> {
            override fun onResponse(
                call: Call<OrderList>,
                response: Response<OrderList?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), OrderListapiIdentifier)
                } else {
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)

                        _interface.OnError(errorResponse)
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<OrderList>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }
}