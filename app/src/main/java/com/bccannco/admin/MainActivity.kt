package com.bccannco.admin

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bccannco.admin.Login.LoginView

class MainActivity : AppCompatActivity(), LoginView {

    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun <T> OnSuccess(result: T,identifier:Int) {

    }

    override fun <T> OnError(result: T) {

    }
}