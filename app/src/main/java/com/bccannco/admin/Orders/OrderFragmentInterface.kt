package com.bccannco.admin.Orders

import com.bccannco.admin.BaseInterface

interface OrderFragmentInterface : BaseInterface {

    fun OnItemClickEvent(position: Int,status:String)
}