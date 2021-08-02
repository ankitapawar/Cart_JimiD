package com.bccannco.admin.chats.personalChat

import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.*
import com.bccannco.admin.comman.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class PersonalChatModel(val context: Context, val _interface: PersonalChatInterface) {

    lateinit var apiInterface: ApiInterface
    lateinit var call: Call<GetChat>
    lateinit var callPrimaryStaff : Call<PrimaryStaff>
    lateinit var call_getStaff: Call<GetStaff>
    lateinit var send_msg_call: Call<SentMessage>
    lateinit var StaffAssign_call: Call<StaffAssign>
    lateinit var send_image_call: Call<SendImageIntoChat>

    fun GetChats(token: String, id: Int) {

        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call = apiInterface.getAllMessages(token, id)

        call.enqueue(object : Callback<GetChat> {
            override fun onResponse(
                call: Call<GetChat>,
                response: Response<GetChat?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), GetChatapiIdentifier)
                } else {

                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
//                        Toast.makeText(context, "" + errorResponse, Toast.LENGTH_SHORT).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                        _interface.OnError(ex.message)
                    }
                }
            }

            override fun onFailure(call: Call<GetChat>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun GetAssignedStaffFromCustomerID(token: String, id: Int)
    {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)

        callPrimaryStaff = apiInterface.getAssignedStaffFromCustomerID(token, id)

        callPrimaryStaff.enqueue(object : Callback<PrimaryStaff>
        {
            override fun onResponse(call: Call<PrimaryStaff>, response: Response<PrimaryStaff?>)
            {
                if (response.isSuccessful && response.body()?.success == 1)
                {
                    _interface.OnSuccess(response.body(), PrimaryStaffAPIdentifier)
                }
                else
                {
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try
                    {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: PrimaryStaff = gson.fromJson(mJson, PrimaryStaff::class.java)
//                        Toast.makeText(context, "" + errorResponse, Toast.LENGTH_SHORT).show()
                        _interface.OnError(errorResponse.error.get(0))
                    }
                    catch (ex: IOException)
                    {
                        ex.printStackTrace()
                        _interface.OnError(ex.message)
                    }
                }
            }
            override fun onFailure(call: Call<PrimaryStaff>, t: Throwable)
            {
                _interface.OnError(t.message)
            }
        })
    }

    fun GetStaff(token: String)
    {

        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        call_getStaff = apiInterface.GetActiveStaff(token)

        call_getStaff.enqueue(object : Callback<GetStaff> {
            override fun onResponse(
                call: Call<GetStaff>,
                response: Response<GetStaff?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), getstaffApiIdentifier)
                } else {
                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
//                        Toast.makeText(context, "" + errorResponse, Toast.LENGTH_SHORT).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                        _interface.OnError(ex.message)
                    }
                }
            }

            override fun onFailure(call: Call<GetStaff>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun SendMessage(
        token: String,
        customer_id: Int,
        message: String,
        staff_id: Int
    ) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        send_msg_call = apiInterface.SendMessages(token, customer_id, message, staff_id)

        send_msg_call.enqueue(object : Callback<SentMessage> {
            override fun onResponse(
                call: Call<SentMessage>,
                response: Response<SentMessage?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), SendMessageidentifier)
                } else {

                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
//                        Toast.makeText(context, "" + errorResponse, Toast.LENGTH_SHORT).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                        _interface.OnError(ex.message)
                    }
                }
            }

            override fun onFailure(call: Call<SentMessage>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun StaffAssign(
        token: String,
        customer_id: Int,
        staff_id: Int
    ) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        StaffAssign_call = apiInterface.StaffAssign(token, customer_id, staff_id)

        StaffAssign_call.enqueue(object : Callback<StaffAssign> {
            override fun onResponse(
                call: Call<StaffAssign>,
                response: Response<StaffAssign?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), StaffAssignApiIdentifier)
                } else {

                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
//                        Toast.makeText(context, "" + errorResponse, Toast.LENGTH_SHORT).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                        _interface.OnError(ex.message)
                    }
                }
            }

            override fun onFailure(call: Call<StaffAssign>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }

    fun SendImage(
        token: String,
        customer_id: Int,
        image: MultipartBody.Part,
        staff_id: Int
    ) {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        send_image_call = apiInterface.SendImages(token, customer_id, image, staff_id)

        send_image_call.enqueue(object : Callback<SendImageIntoChat> {
            override fun onResponse(
                call: Call<SendImageIntoChat>,
                response: Response<SendImageIntoChat?>
            ) {
                if (response.isSuccessful && response.body()?.success == 1) {
                    _interface.OnSuccess(response.body(), SendImageApiIdentifier)
                } else {

                    val parser = JsonParser()
                    var mJson: JsonElement? = null
                    try {
                        mJson = parser.parse(response.errorBody()!!.string())
                        val gson = Gson()
                        val errorResponse: LoginResponseModel =
                            gson.fromJson(mJson, LoginResponseModel::class.java)
//                        Toast.makeText(context, "" + errorResponse, Toast.LENGTH_SHORT).show()
                        _interface.OnError(errorResponse.error.get(0))
                    } catch (ex: IOException) {
                        ex.printStackTrace()
                        _interface.OnError(ex.message)
                    }
                }
            }

            override fun onFailure(call: Call<SendImageIntoChat>, t: Throwable) {
                _interface.OnError(t.message)
            }
        })
    }
}