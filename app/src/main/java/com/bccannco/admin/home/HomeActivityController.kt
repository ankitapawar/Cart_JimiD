package com.bccannco.admin.home

import android.content.Context
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.ClientCredential
import com.bccannco.admin.apis.ApiResponseModel.LoginResponseModel
import com.bccannco.admin.apis.ApiResponseModel.logout
import com.bccannco.admin.comman.LogoutapiIdentifier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class HomeActivityController(var context: Context, var _interface: HomeActivityInterface) {

    fun logoutApiCalling(token : String) {
        val retrofit: ApiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        val call: Call<logout> = retrofit.logout(token)
        call.enqueue(object : Callback<logout> {
            override fun onResponse(
                call: Call<logout>,
                response: Response<logout>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), LogoutapiIdentifier)
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
                        Toast.makeText(context, "" + errorResponse.error.get(0).toString(), Toast.LENGTH_SHORT).show()
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<logout>, t: Throwable) {
                _interface.OnError(t.message.toString())
            }
        })
    }
}