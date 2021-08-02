package com.bccannco.admin

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bccannco.admin.R
import com.bccannco.admin.comman.MYPREF

open class BaseFragment : Fragment() {

    lateinit var mypref: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    lateinit var loadder: LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_base, container, false)
        return view
    }

    fun prefInit(context: Context) {
        mypref = context.getSharedPreferences(MYPREF, AppCompatActivity.MODE_PRIVATE)
        editor = mypref.edit()
    }

    fun set_IntPref(key: String, value: Int) {
        editor.putInt(key, value as Int).apply()
    }

    fun set_StringPref(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    fun set_BooleanPref(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    fun <T> toast(msg: T) {
        Toast.makeText(context, "" + msg, Toast.LENGTH_SHORT).show()
    }

    fun get_IntPref(key: String, DefualtValue: Int): Int {
        return mypref.getInt(key, DefualtValue as Int)
    }

    fun get_StringPref(key: String, DefualtValue: String): String {
        return mypref.getString(key, DefualtValue).toString()
    }

    fun get_BooleanPref(key: String, Defval: Boolean): Boolean {
        return mypref.getBoolean(key, Defval)
    }

    fun loaderInit(view: View) {
        loadder = view.findViewById(R.id.LoaderLayout)
    }

    fun showDialog() {
        loadder.visibility = View.VISIBLE
    }

    fun hideDialog() {
        loadder.visibility = View.GONE
    }
    fun EmptyFielValidation(edittext: EditText, msg: String): Boolean {
        if (edittext.text.isEmpty()) {
            toast(msg + " is empty.")
            return false
        }
        return true
    }
    //check internet connection
    fun isNetworkAvailable(): Boolean {
        val cm = (context
            ?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetwork = cm.activeNetworkInfo
        return (activeNetwork != null
                && activeNetwork.isConnected)
    }
}