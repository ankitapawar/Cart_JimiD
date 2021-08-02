package com.bccannco.admin.inventory

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.*
import com.bccannco.admin.comman.BatchApiIdentifier
import com.bccannco.admin.comman.GetCategoryApiIdentifier
import com.bccannco.admin.comman.GetProductByIdApiIdentifier
import com.bccannco.admin.comman.PostInventorydApiIdentifier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class InventoryModel(val context: Context?, val _interface: Inventoryinterface) {

    private val TAG = "InventoryModel"
    lateinit var apiInterface: ApiInterface
    lateinit var callBatch : Call<GetBatch>
    lateinit var call: Call<GetCategory>
    lateinit var callGetProduct: Call<GetProduct>
    lateinit var callPostInveentory: Call<PostInventory>

    fun GetCategoryApiCalling(token: String) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call = apiInterface.GetCatogory(token)
        call.enqueue(object : Callback<GetCategory> {
            override fun onResponse(
                call: Call<GetCategory>,
                response: Response<GetCategory?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    Log.e(TAG, "onResponse: " + token)
                    _interface.OnSuccess(response.body(), GetCategoryApiIdentifier)
                } else {
//                    var loginModel1: LoginResponseModel
//                    loginModel1= response.errorBody() as LoginResponseModel
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
                        Toast.makeText(
                            context,
                            "" + errorResponse.error.get(0).toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetCategory>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }
    fun GetBatchApiCalling(token: String) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        callBatch = apiInterface.GetBatch(token)

        callBatch.enqueue(object : Callback<GetBatch>
        {
            override fun onResponse(
                call: Call<GetBatch>,
                response: Response<GetBatch?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    Log.e(TAG, "onResponse: " + token)
                    _interface.OnSuccess(response.body(), BatchApiIdentifier)
                } else {
//                    var loginModel1: LoginResponseModel
//                    loginModel1= response.errorBody() as LoginResponseModel
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
                        Toast.makeText(
                            context,
                            "" + errorResponse.error.get(0).toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetBatch>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun GetProductApiCalling(token: String, batch_id : Int) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        callGetProduct = apiInterface.GetProductByCategory(token, batch_id)
        Log.e("callBatchProduct",""+callGetProduct.request());
        callGetProduct.enqueue(object : Callback<GetProduct> {
            override fun onResponse(
                call: Call<GetProduct>,
                response: Response<GetProduct?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    Log.e(TAG, "onResponse: " + token)
                    _interface.OnSuccess(response.body(), GetProductByIdApiIdentifier)
                } else {
                    Log.e("responsebatch",""+
                    response.body())
//                    var loginModel1: LoginResponseModel
//                    loginModel1= response.errorBody() as LoginResponseModel
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
                        Toast.makeText(
                            context,
                            "" + errorResponse.error.get(0).toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<GetProduct>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun PostInventoryData(token: String, batch_id: Int, count_qty: Int, product_id: Int) {

        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        callPostInveentory = apiInterface.PostInventory(token,batch_id,count_qty,product_id)
        callPostInveentory.enqueue(object :Callback<PostInventory>{

            override fun onResponse(call: Call<PostInventory>, response: Response<PostInventory>) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    Log.e(TAG, "onResponse: " + token)
                    _interface.OnSuccess(response.body(), PostInventorydApiIdentifier)
                } else {
//                    var loginModel1: LoginResponseModel
//                    loginModel1= response.errorBody() as LoginResponseModel
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
                        Toast.makeText(
                            context,
                            "" + errorResponse.error.get(0).toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<PostInventory>, t: Throwable) {
                _interface.OnError(t.message)
            }

        })
    }
}