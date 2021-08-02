package com.bccannco.admin.chats

import com.bccannco.admin.BaseInterface

interface ChatlistInterface : BaseInterface {

    fun OnItemClickEvent(position: Int,customername:String)
}