package com.bccannco.admin.home.ui.home

import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.LoginResponseModel
import com.bccannco.admin.apis.ApiResponseModel.OrderList
import com.bccannco.admin.apis.ApiResponseModel.SalesGraph
import com.bccannco.admin.comman.OrderListapiIdentifier
import com.bccannco.admin.comman.SalesGraphapiIdentifier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeViewModel(val _interface: HomeFragInterface) {
    private val TAG = "HomeViewModel"
    lateinit var apiInterface: ApiInterface
    lateinit var call: Call<OrderList>
    lateinit var graphApiAall: Call<SalesGraph>

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
//                    Log.e(TAG, "onResponse: " + response)
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
                        _interface.OnError(errorResponse.error.get(0))
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

    fun GetSalesGraph(token: String) {

        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        graphApiAall = apiInterface.DashbordGraph(token)

        graphApiAall.enqueue(object : Callback<SalesGraph>
        {
            override fun onResponse(call: Call<SalesGraph>, response: Response<SalesGraph?>)
            {
                if (response.isSuccessful && response.body()?.success == 1)
                { _interface.OnSuccess(response.body(), SalesGraphapiIdentifier)
                }
                else
                {
//                    Log.e(TAG, "onResponse: " + response)
                    val parser = JsonParser()
                    var mJson: JsonElement? = null

                    try
                    {
//                        mJson = parser.parse(response.errorBody()?.charStream())
//                        mJson = parser.parse(response.errorBody()!!.string())
//                        val gson = Gson()

//                        val errorResponse: LoginResponseModel = gson.fromJson(mJson, LoginResponseModel::class.java)
//                        _interface.OnError(errorResponse.error.get(0))


//                        val gson = Gson()
//                        val type = object : TypeToken<LoginResponseModel>() {}.type
//                        var errorResponse: LoginResponseModel? = gson.fromJson(response.errorBody()!!.charStream(), type)
//                        if (errorResponse != null) {
//                            _interface.OnError(errorResponse.error.get(0))
//                        }
                    }
                    catch (ex: IOException)
                    {
                        ex.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<SalesGraph>, t: Throwable)
            {
                _interface.OnError(t.message)
            }
        })
    }
}

