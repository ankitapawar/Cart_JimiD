package com.bccannco.admin

import android.app.Activity
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.DatePicker
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bccannco.admin.apis.ApiResponseModel.UnreadMessage
import com.bccannco.admin.comman.BEARER_TOKEN
import com.bccannco.admin.comman.GetUnreadMessageCount
import com.bccannco.admin.comman.MYPREF
import com.google.gson.Gson
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import kotlinx.android.synthetic.main.fragment_chat_list.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


open class BaseActivity : AppCompatActivity() , BaseInterface
{
    lateinit var basemodel: BaseModel

    private val REQUEST_CODE = 111
    lateinit var mypref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var dialog: Dialog
    val EMAIL_ADDRESS_PATTERN: Pattern = Pattern.compile(
        "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+"
    )

    override fun attachBaseContext(newBase: Context?)
    {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase!!))
    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        supportActionBar?.hide()
        mypref = getSharedPreferences(MYPREF, MODE_PRIVATE)
        editor = mypref.edit()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        dialog = createCustomLoader(false)

        basemodel = BaseModel(this, this)
        callForMyUnreadMessage()

    }

    public fun callForMyUnreadMessage()
    {
        Handler(Looper.getMainLooper()).postDelayed(
            {
//            val mainIntent = Intent(this, Menu::class.java)
//            startActivity(mainIntent)
//            finish()

                if (isNetworkAvailable())
                {
                    basemodel.GetUnreadMessageCount(get_StringPref(BEARER_TOKEN, ""), "3")
                }
                else
                {
                    hideDialog()
                    toast("Please check your internet connectivity.")
                }
        }, 5000)
    }
    fun EmptyFielValidation(edittext: EditText, msg: String): Boolean
    {
        if (edittext.text.isEmpty())
        {
            toast(msg + " is empty.")
            return false
        }
        return true
    }

    fun EmailValidation(edittext: EditText, msg: String): Boolean
    {
        if (!EMAIL_ADDRESS_PATTERN.matcher(edittext.text).matches())
        {
            toast(msg + "")
            return false
        }
        return true
    }

    fun set_IntPref(key: String, value: Int)
    {
        editor.putInt(key, value as Int).apply()
    }

    fun set_StringPref(key: String, value: String)
    {
        editor.putString(key, value).apply()
    }

    fun set_BooleanPref(key: String, value: Boolean)
    {
        editor.putBoolean(key, value).apply()
    }

    fun <T> toast(msg: T)
    {
        Toast.makeText(this, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun get_IntPref(key: String, DefualtValue: Int): Int
    {
        return mypref.getInt(key, DefualtValue as Int)
    }

    fun get_StringPref(key: String, DefualtValue: String): String
    {
        return mypref.getString(key, DefualtValue).toString()
    }

    fun get_BooleanPref(key: String, Defval: Boolean): Boolean
    {
        return mypref.getBoolean(key, Defval)
    }

    fun datePicker(): String
    {
        val myCalendar: Calendar = Calendar.getInstance()
        lateinit var mdate: DatePickerDialog.OnDateSetListener
        var _mdate: String = "No Date Selected"

        mdate = DatePickerDialog.OnDateSetListener()
        {
                view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            myCalendar.set(Calendar.MONTH, month)

            val myFormat = "MM/dd/YYYY" //In which you need put here
            val sdf = SimpleDateFormat(myFormat)
            _mdate = sdf.format(myCalendar.time) as String
        }
        return _mdate
    }

    fun openGalleryForImage()
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE)
        {
//            Iv_Productimage.setImageURI(data?.data) // handle chosen image
        }
    }

    fun createCustomLoader(isCancelable: Boolean): Dialog
    {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(isCancelable)
        dialog.setContentView(R.layout.custom_loader)
        val layout = dialog.findViewById<LinearLayout>(R.id.LoaderLayoutForActivity)
        layout.visibility = View.VISIBLE
        val lp = WindowManager.LayoutParams()
        val window: Window? = dialog.getWindow()
        lp.copyFrom(window?.getAttributes())
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window?.setAttributes(lp)
        return dialog
    }

    fun showDialog()
    {
        if (!dialog.isShowing)
        {
            dialog.show()
        }
    }

    fun hideDialog()
    {
        if (dialog.isShowing)
        {
            dialog.hide()
        }

    }

    //check internet connection
    fun isNetworkAvailable(): Boolean
    {
        val cm = (this.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager)!!
        val activeNetwork = cm!!.activeNetworkInfo
        return (activeNetwork != null && activeNetwork.isConnected)
    }

    override fun <T> OnSuccess(result: T, identifier: Int)
    {
        Log.e("baseActivity", "OnSuccess: ")

        when (identifier)
        {
            GetUnreadMessageCount ->
            {
                Log.e("baseActivity", "OnSuccess: "+"GetUnreadMessageCount")
                val unreadresponse = result as UnreadMessage

                Log.e("1111unreadResponse", ""+Gson().toJson(unreadresponse))

                if(unreadresponse.data.allunread.toString() != null)
                {
                    Log.e("1111allunread", ""+unreadresponse.data.allunread.toString())
//                    Tv_AllUnread.text = unreadresponse.data.allunread.toString() +" All Unread Messages"
                }
                if(unreadresponse.data.myunread.toString() != null)
                {
                    Log.e("1111myunread", ""+unreadresponse.data.myunread.toString())
//                    Tv_MyUnread.text = unreadresponse.data.myunread.toString()  +" My Unread Messages"
                }
            }
        }
    }

    override fun <T> OnError(result: T)
    {
        Log.e("baseActivity", "OnError: " + result)
        hideDialog()
        toast(result)
    }

}