package com.bccannco.admin.chats

import android.content.Context
import android.util.Log
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.CustomerList
import com.bccannco.admin.apis.ApiResponseModel.ErrorBody
import com.bccannco.admin.apis.ApiResponseModel.LoginResponseModel
import com.bccannco.admin.apis.ApiResponseModel.UnreadMessage
import com.bccannco.admin.comman.CustomerListapiIdentifier
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.lang.reflect.Type


class ChatlistModel(val context: Context, val _interface: ChatlistInterface)
{

    lateinit var apiInterface: ApiInterface
    lateinit var call: Call<CustomerList>
    lateinit var callUnread: Call<UnreadMessage>

    fun GetCustomerList(token:String,limit: Int, page: Int)
    {

        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call = apiInterface.GetCustomerList(token, 10, 1)

        call.enqueue(object : Callback<CustomerList> {
            override fun onResponse(
                call: Call<CustomerList>,
                response: Response<CustomerList?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), CustomerListapiIdentifier)
                } else {

                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
//                        Toast.makeText(
//                            context,
//                            "" + errorResponse.error.get(0).toString(),
//                            Toast.LENGTH_SHORT
//                        ).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                    }
                }
            }

            override fun onFailure(call: Call<CustomerList>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun GetUnreadMessageCount(token:String, userid: String)
    {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        callUnread = apiInterface.GetUnreadMessageCount(token, userid)

        Log.e("unreadRequest" , ""+ callUnread.request())
        callUnread.enqueue(object : Callback<UnreadMessage>
        {
            override fun onResponse(
                call: Call<UnreadMessage>,
                response: Response<UnreadMessage?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1)
                {
                    _interface.OnSuccess(response.body(), com.bccannco.admin.comman.GetUnreadMessageCount)
                }
                else
                {
                    val parser = JsonParser()
                    var mJson: JsonElement? = null

                    try
                    {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: ErrorBody = gson.fromJson(mJson, ErrorBody::class.java)
                        _interface.OnError(errorResponse.Example().error)

//                        val collectionType: Type = object : TypeToken<List<UnreadMessage?>?>() {}.type
//                        val lcs: UnreadMessage = Gson().fromJson(mJson , collectionType) as UnreadMessage
//                        _interface.OnError(lcs)
                    }
                    catch (ex: IOException)
                    {
                        ex.printStackTrace()
                    }
                }
            }
            override fun onFailure(call: Call<UnreadMessage>, t: Throwable)
            {
                _interface.OnError(t.message)
            }
        })
    }
}