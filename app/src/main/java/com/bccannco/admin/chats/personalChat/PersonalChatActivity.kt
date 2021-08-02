package com.bccannco.admin.chats.personalChat

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.gson.Gson
import com.bccannco.admin.BaseActivity
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.*
import com.bccannco.admin.comman.*
import kotlinx.android.synthetic.main.activity_personal_chat.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class PersonalChatActivity : BaseActivity(), PersonalChatInterface, View.OnClickListener {

    private val TAG = "PersonalChatActivity"
    var customerId: Int = 0
    private val REQUEST_CODE = 101
    lateinit var customername: String
    lateinit var layout: LinearLayoutManager
    lateinit var adapter: ChatAdapter
    lateinit var model: PersonalChatModel
    lateinit var jsonToString: String
    lateinit var staff: GetStaff
    lateinit var primary_staff: PrimaryStaff
    var primaryStaffList: MutableList<String> = ArrayList()
//    lateinit var  staffArray : Array<String>
    lateinit var mapstaff: HashMap<String, Int>
    var user_id = 0
    var spiner_defualt_position: Int = 0
    var is_FirstTime = true;



    override fun onCreate(savedInstanceState: Bundle?) {
        this.supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_personal_chat)
        customerId = intent.getIntExtra(CHAT_ID, 0)
        customername = intent.getStringExtra(CUSTOMER_NAME).toString()
        Tv_Title.text = getString(R.string.Messaging)
        Iv_Back.setOnClickListener(this)
        model = PersonalChatModel(this, this)
//        adapterInit()
        if (isNetworkAvailable()) {
            showDialog()
            model.GetStaff(get_StringPref(BEARER_TOKEN, ""))
            model.GetChats(get_StringPref(BEARER_TOKEN, ""), customerId)

            model.GetAssignedStaffFromCustomerID(get_StringPref(BEARER_TOKEN, ""), customerId)
        } else {
            toast("Please check your internet connectivity.")
        }

        Tv_CustomerName.text = customername



        Sp_Staff.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                user_id = songs[position].toInt()
//                 user_id = Integer.parseInt(primaryStaffList.get(parent.getItemAtPosition(position) as Int))

                Log.e(TAG, "onItemSelected user_id: " + user_id)
                if (!is_FirstTime) {
                    if (isNetworkAvailable()) {
                        showDialog()
                        model.StaffAssign(get_StringPref(BEARER_TOKEN, ""), customerId, user_id)
                    } else {
                        toast("Please check your internet connectivity.")
                    }
                }
                is_FirstTime =  false;
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
    }

    private fun adapterInit(data: GetChat) {
        layout = LinearLayoutManager(this)
        adapter = ChatAdapter(this, this, data.userMsgData)
        RvChats.layoutManager = layout
        RvChats.adapter = adapter
        adapter.notifyDataSetChanged()
        RvChats.scrollToPosition(data.userMsgData.size - 1)
    }

    fun SendMessage(view: View) {

        if (!Et_Message.text.toString().equals("")) {
            if (isNetworkAvailable()) {
                showDialog()
                model.SendMessage(
                    get_StringPref(BEARER_TOKEN, ""),
                    customerId,
                    Et_Message.text.toString(),
                    get_StringPref(USER_ID, "").toInt()
                )
                Log.e(TAG, "SendMessage USERID:- " + get_StringPref(USER_ID, "").toInt())
            } else {
                toast("Please check your internet connectivity.")
            }
        }
    }

    fun SendImage(view: View) {
        ImagePicker.with(this)
            .crop()                    //Crop image(Optional), Check Customization for more option
            .compress(1024)            //Final image size will be less than 1 MB(Optional)
            .maxResultSize(
                1080,
                1080
            )    //Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!
            // Use Uri object instead of File to avoid storage permissions
//            imgProfile.setImageURI(fileUri)
            Log.e(TAG, "onActivityResult path: " + uri.path)
            val file = File(uri.path)
            val requestFile: RequestBody =
                RequestBody.create(MediaType.parse("multipart/form-data"), file)
            val body =
                MultipartBody.Part.createFormData("file", file.getName(), requestFile)


            if (isNetworkAvailable()) {
                showDialog()
                model.SendImage(
                    get_StringPref(BEARER_TOKEN, ""),
                    customerId,
                    body,
                    user_id
                )
            } else {
                if (dialog.isShowing)
                    dialog.dismiss()
                toast("Please check your internet connectivity.")
            }

        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    fun staffSpinnerAdapterInit(array: Array<String>) {
        val Staffadapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                array
            )
        Sp_Staff.adapter = Staffadapter
        Sp_Staff.setSelection(spiner_defualt_position)
    }

    override fun onClickListner(view: View) {
        TODO("Not yet implemented")
    }

    override fun <T> OnSuccess(result: T, identifier: Int)
    {
        Log.e(TAG, "OnSuccess: ")

        when (identifier)
        {
            GetChatapiIdentifier ->
            {
                val getchat: GetChat = result as GetChat
                adapterInit(getchat)

                val sdf = SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                val size = getchat.userMsgData.size - 1

                Log.e(TAG, "OnSuccess size: " + size)
                Log.e(TAG, "OnSuccess time : " + getchat.userMsgData.get(size).message)

                val past: Date = sdf.parse(getchat.userMsgData.get(size).sentAt)
                val now = Date()

                Log.e(TAG, "OnSuccess past time : " + past.time)
                Log.e(TAG, "OnSuccess now time: " + now.time)
                Log.e(TAG, "OnSuccess: " + TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()) + " hours ago")
                Log.e(TAG, "\n\n\n\n ")

//                TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime())
                Tv_Time.text = TimeUnit.MILLISECONDS.toHours(now.getTime() - past.getTime()).toString() + " hours ago"
//                if (mapstaff != null){
//                    for(i in 0..mapstaff.values.size-1){
//                        Log.e(TAG, "OnSuccess mapstaff.values : "+mapstaff.values.toList().get(i) )
//                    }
////                    if (getchat.data.get(getchat.data.size-1).equals(mapstaff.values)) {
////                        spiner_defualt_position = i
////                    }
//                }
                hideDialog()
            }

            PrimaryStaffAPIdentifier ->
            {
                primary_staff = result as PrimaryStaff

                Log.e("primaryStaffResponse", Gson().toJson(result))
//                val staffArray : Array<String> = emptyArray()

                val staffid = primary_staff.staffAssigned.staffId

                if(staffid != null)
                {
//                    var array = Array

                    tv_primary_staff.text = staffid
//                    add(staffid)
//                    staffSpinnerAdapterInit(array = songs)

//                    primaryStaffList.add(staffid)
//
//                    if(primaryStaffList != null && primaryStaffList.size > 0)
//                    {
//                        var  staffArray = arrayListOf(primaryStaffList)
//
//                        Log.e("staffArray",""+Gson().toJson(staffArray))
//
//
//                    }
//                    Log.e("staffid",""+staffid)
//                    staffArray[0] = staffid
                }
                hideDialog()
            }

            getstaffApiIdentifier ->
            {
                staff = result as GetStaff
                mapstaff = HashMap()
                val productarray = arrayOfNulls<String>(staff.data.size)
//                val str = arrayOfNulls<String>(10)
                for (i in 0..staff.data.size - 1)
                {
                    Log.e(TAG, "OnSuccess batcharray: " + staff.data.get(i).username)
//                    Log.e(TAG, "OnSuccess: i = " + i + "  size => " + productlist.data.size)
                    productarray[i] = staff.data.get(i).username

                    mapstaff.put(staff.data.get(i).username, (staff.data.get(i).userId.toString()).toInt())

                    if (get_StringPref(USER_ID, "").equals(staff.data.get(i).userId))
                    {
                        spiner_defualt_position = i
                    }
                    if (i == staff.data.size - 1)
                    {
//                        staffSpinnerAdapterInit(array = productarray)
//                        hideDialog()
                    }
                }
            }

            SendMessageidentifier ->
            {
                hideDialog()
                model.GetChats(get_StringPref(BEARER_TOKEN, ""), customerId)
                Et_Message.text = null
            }
            SendImageApiIdentifier -> {
                val model: SendImageIntoChat = result as SendImageIntoChat
                hideDialog()
                if (model.success == 1) {
                    toast("Image sent")
                    this.model.GetChats(get_StringPref(BEARER_TOKEN, ""), customerId)
                }
            }
            StaffAssignApiIdentifier -> {
                val model: StaffAssign = result as StaffAssign
                hideDialog()
                if (model.success == 1) {
                    toast(model.data)
                }
            }
        }
    }

    override fun <T> OnError(result: T) {
        Log.e(TAG, "OnError: " + result)
        hideDialog()
        toast(result)
    }

    override fun onClick(p0: View?) {
        when (p0) {
            Iv_Back -> {
                onBackPressed()
            }
        }
    }
    private var songs: Array<String> = arrayOf()
    fun add(input: String)
    {
        songs += input
    }
    fun remove(arr: Array<String>, target: String): Array<String>
    {
        val result = arr.toMutableList()
        result.removeAll(listOf(target))
        return result.toTypedArray()
    }

}