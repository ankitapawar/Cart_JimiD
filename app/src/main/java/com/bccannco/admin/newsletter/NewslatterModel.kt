package com.bccannco.admin.newsletter

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
import com.bccannco.admin.apis.ApiResponseModel.Newslatter
import com.bccannco.admin.apis.ApiResponseModel.StaffLogin
import com.bccannco.admin.comman.NewslatterapiIdentifier
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class NewslatterModel(val context: Context, val _interface: NewsletterInterface) {

    private val TAG = "NewslatterModel"
    lateinit var apiInterface: ApiInterface
    lateinit var call: Call<Newslatter>

    fun NewslatterApiCalling(token: String, subject: String, body: String) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call = apiInterface.SendNewesLetter(token, subject, body)
        call.enqueue(object : Callback<Newslatter> {
            override fun onResponse(
                call: Call<Newslatter>,
                response: Response<Newslatter?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    Log.e(TAG, "onResponse: " + token)
                    _interface.OnSuccess(
                        response.body(),
                        NewslatterapiIdentifier
                    )
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

            override fun onFailure(call: Call<Newslatter>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

}