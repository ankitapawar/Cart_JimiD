package com.bccannco.admin

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.bccannco.admin.apis.ApiClient
import com.bccannco.admin.apis.ApiInterface
import com.bccannco.admin.apis.ApiResponseModel.ErrorBody
import com.bccannco.admin.apis.ApiResponseModel.UnreadMessage
import com.bccannco.admin.comman.BEARER_TOKEN
import com.bccannco.admin.comman.MYPREF
import com.bccannco.admin.comman.ORDER_STATUS
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class App : Application()
{
    lateinit var map: HashMap<String, Int>
    lateinit var pref: SharedPreferences
    lateinit var edit: SharedPreferences.Editor

    lateinit var apiInterface: ApiInterface
    lateinit var callUnread: Call<UnreadMessage>

    override fun onCreate()
    {
        super.onCreate()
        ViewPump.init(ViewPump.builder().addInterceptor(CalligraphyInterceptor(CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/GT-Walsheim-Regular.ttf").build())).build())

        statusSet()
        pref = getSharedPreferences(MYPREF, MODE_PRIVATE)
        edit = pref.edit()
        val gson = Gson().toJson(map)
        edit.putString(ORDER_STATUS, gson).apply()

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable
        {
            override fun run()
            {
                Log.e("callMethod"," ");
                GetUnreadMessageCount(get_StringPref(BEARER_TOKEN, ""), "3")
                mainHandler.postDelayed(this, 5000)
            }
        })
    }
    fun get_StringPref(key: String, DefualtValue: String): String
    {
        return pref.getString(key, DefualtValue).toString()
    }
//    public fun callForMyUnreadMessage( con :Context)
//    {
//        Handler(Looper.getMainLooper()).postDelayed(
//            {
//                if (isNetworkAvailable())
//                {
//                    GetUnreadMessageCount(get_StringPref(BEARER_TOKEN, ""), "3", con)
//                }
//                else
//                {
////                    toast("Please check your internet connectivity.")
//                }
//            }, 5000)
//    }
    fun isNetworkAvailable(): Boolean
    {
        val cm = (this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager)!!
        val activeNetwork = cm!!.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnected)
    }
    private fun statusSet()
    {
        map = HashMap()
        map.put("All Orders", 0)
        map.put("Pending", 1)
        map.put("EMT", 18)
        map.put("To Fill", 19)
        map.put("Filling", 20)
        map.put("To Ship", 21)
        map.put("Shipped", 3)
        map.put("Complete", 5)
        map.put("Canceled", 7)
    }

    fun GetUnreadMessageCount(token:String, userid: String)
    {
        apiInterface = ApiClient().retrofit.create(ApiInterface::class.java)
        callUnread = apiInterface.GetUnreadMessageCount(token, userid)

        Log.e("unreadRequest" , ""+ callUnread.request())
        callUnread.enqueue(object : Callback<UnreadMessage>
        {
            override fun onResponse(call: Call<UnreadMessage>, response: Response<UnreadMessage?>)
            {
                if (response.isSuccessful && response.body()?.success == 1)
                {
                    Log.e("app class on success", ""+ response.body()!!.data.myunread);

                    if(response != null && response.body() != null)
                    {
//                        if(! response.body()!!.data.myunread.equals("0") )
//                        {
                            val i = Intent(applicationContext , MyBroadcastReceiver::class.java)
                            i.putExtra("action","show_notification");
                            i.putExtra("myUnreadCount", response.body()!!.data.myunread)
                            sendBroadcast(i)
//                        }
                    }
//                    _interface.OnSuccess(response.body(), com.bccannco.admin.comman.GetUnreadMessageCount)
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

                        Log.e("app class on error", ""+ errorResponse.Example().error);
//                        _interface.OnError(errorResponse.Example().error)

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
                Log.e("app class exception", ""+ t.message);
//                _interface.OnError(t.message)
            }
        })
    }
}