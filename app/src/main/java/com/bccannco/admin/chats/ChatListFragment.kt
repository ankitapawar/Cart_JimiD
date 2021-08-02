package com.bccannco.admin.chats

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bccannco.admin.BaseFragment
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.CustomerList
import com.bccannco.admin.apis.ApiResponseModel.UnreadMessage
import com.bccannco.admin.chats.personalChat.PersonalChatActivity
import com.bccannco.admin.comman.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_chat_list.*

class ChatListFragment : BaseFragment(), ChatlistInterface
{
    private val TAG = "ChatListFragment"
    lateinit var _context: Context
    lateinit var RvChatlist: RecyclerView
    lateinit var layout: LinearLayoutManager
    lateinit var adapter: ChatlistAdapter
    lateinit var model: ChatlistModel

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_chat_list, container, false)

        _context = container?.context!!
        model = ChatlistModel(_context, this)

        loaderInit(view)

        prefInit(_context)

        RvChatlist = view.findViewById(R.id.Rv_Chatlist)

        adapterInit()

        showDialog()
        if (isNetworkAvailable())
        {
            model.GetCustomerList(get_StringPref(BEARER_TOKEN, ""), 10, 1)
            model.GetUnreadMessageCount(get_StringPref(BEARER_TOKEN, ""), "3")
        }
        else
        {
            hideDialog()
            toast("Please check your internet connectivity.")
        }
        return view
    }

    private fun adapterInit()
    {
        layout = LinearLayoutManager(context)
        RvChatlist.layoutManager = layout
    }

    override fun OnItemClickEvent(value: Int, customername: String)
    {
        if (isNetworkAvailable())
        {
            startActivity(
                Intent(context, PersonalChatActivity::class.java).
                putExtra(CHAT_ID, value).putExtra(CUSTOMER_NAME, customername))
        }
        else
        {
            hideDialog()
            toast("Please check your internet connectivity.")
        }
    }

    override fun <T> OnSuccess(result: T, identifier: Int)
    {
        Log.e(TAG, "OnSuccess: ")
        when (identifier)
        {
            CustomerListapiIdentifier ->
            {
                val customerList: CustomerList = result as CustomerList
                adapter = ChatlistAdapter(context, this, customerList)
                RvChatlist.adapter = adapter
                adapter.notifyDataSetChanged()
                hideDialog()
            }
            GetUnreadMessageCount ->
            {
                val unreadresponse = result as UnreadMessage

                Log.e("unreadResponse", ""+Gson().toJson(unreadresponse))

                if(unreadresponse.data.allunread.toString() != null)
                {
                    Tv_AllUnread.text = unreadresponse.data.allunread.toString() +" All Unread Messages"
                }
                if(unreadresponse.data.myunread.toString() != null)
                {
                    Tv_MyUnread.text = unreadresponse.data.myunread.toString()  +" My Unread Messages"
                }
            }
        }
    }
    override fun <T> OnError(result: T)
    {
        Log.e(TAG, "OnError: " + result)
        hideDialog()
        toast(result)
    }
}