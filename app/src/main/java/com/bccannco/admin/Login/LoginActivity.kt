package com.bccannco.admin.Login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.bccannco.admin.BaseActivity
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.ClientCredential
import com.bccannco.admin.apis.ApiResponseModel.StaffLogin
import com.bccannco.admin.comman.BEARER_TOKEN
import com.bccannco.admin.comman.IS_LOGIN
import com.bccannco.admin.comman.USERNAME
import com.bccannco.admin.comman.USER_ID
import com.bccannco.admin.home.HomeActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : BaseActivity(), LoginView {
    private val TAG = "LoginActivity"
    lateinit var model: LoginModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        model = LoginModel(this, this)
    }

    fun OnLoginClickListner(view: View) {
        if (!dialog.isShowing)
            dialog.show()
        if (!EmptyFielValidation(Et_Username, "Username")) {
            YoYo.with(Techniques.StandUp)
                .duration(200)
                .repeat(1)
                .playOn(Et_Username);
            return
        }
        if (!EmptyFielValidation(Et_Password, "Password")) {

            YoYo.with(Techniques.StandUp)
                .duration(200)
                .repeat(1)
                .playOn(Et_Password);
            return
        }
        if (isNetworkAvailable()) {
            model.GetClientCredential("Basic YXBpLWFkbWluOllYVjBhRjlqYkdsbGJuUTZaR1Z0YjE5")
        } else {
            if (dialog.isShowing)
                dialog.dismiss()
            toast("Please check your internet connectivity.")
        }
    }

    fun PasswordVisibile(view: View) {
        Iv_pass_visible_off.visibility = View.VISIBLE
        Iv_pass_visible.visibility = View.GONE
        Et_Password.inputType = InputType.TYPE_CLASS_TEXT
    }

    fun Passwordvisibility_off(view: View) {
        Iv_pass_visible.visibility = View.VISIBLE
        Iv_pass_visible_off.visibility = View.GONE
        Et_Password.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    }

    override fun <T> OnSuccess(result: T, identifier: Int) {
//        model.LoginApiCalling()

        when (identifier) {
            0 -> {
                val data: ClientCredential = result as ClientCredential
                Log.e(TAG, "OnSuccess: " + data.data.tokenType + " " + data.data.accessToken)
                set_StringPref(BEARER_TOKEN, data.data.tokenType + " " + data.data.accessToken)
                model.LoginApiCalling(
                    data.data.tokenType + " " + data.data.accessToken,
                    Et_Username.text.toString(),
                    Et_Password.text.toString()
                )
            }
            1 -> {
                val loginstaf: StaffLogin = result as StaffLogin
                Log.e(TAG, "OnSuccess login api : " + loginstaf.data)
                set_StringPref(USERNAME, Et_Username.text.toString())
                set_StringPref(USER_ID, loginstaf.data.userId)
                set_BooleanPref(IS_LOGIN, true)
                if (dialog.isShowing)
                    dialog.dismiss()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
        }

//        startActivity(Intent(this, HomeActivity::class.java))
//        finish()
    }

    override fun <T> OnError(result: T) {
        if (dialog.isShowing)
            dialog.dismiss()
        Log.e(TAG, "OnError: " + result)
    }
}