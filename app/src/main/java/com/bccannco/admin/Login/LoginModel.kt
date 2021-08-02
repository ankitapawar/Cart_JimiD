package com.bccannco.admin.Login

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.ClientCredential
import com.bccannco.admin.apis.ApiResponseModel.LoginResponseModel
import com.bccannco.admin.apis.ApiResponseModel.StaffLogin
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class LoginModel(val context: Context, val _interface: LoginView) {

    private val TAG = "LoginModel"
    lateinit var apiInterface: ApiInterface
    lateinit var call: Call<StaffLogin>
    lateinit var call_Client_Cred: Call<ClientCredential>

    fun LoginApiCalling(token:String,username: String, password: String) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call = apiInterface.Authentication(token,username, password)
        call.enqueue(object : Callback<StaffLogin> {
            override fun onResponse(
                call: Call<StaffLogin>,
                response: Response<StaffLogin?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    Log.e(TAG, "onResponse: "+token )
                    _interface.OnSuccess(response.body(), 1)
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

            override fun onFailure(call: Call<StaffLogin>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun GetClientCredential(Apikey: String) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call_Client_Cred = apiInterface.Client_Credentials(Apikey)
        call_Client_Cred.enqueue(object : Callback<ClientCredential> {
            override fun onResponse(
                call: Call<ClientCredential>,
                response: Response<ClientCredential?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(),0)
                } else {
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
                        Toast.makeText(context, "" + errorResponse, Toast.LENGTH_SHORT).show()
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<ClientCredential>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }
}