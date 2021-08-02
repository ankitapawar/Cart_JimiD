package com.bccannco.admin.home.ui.home

import com.bccannco.admin.BaseInterface

interface HomeFragInterface : BaseInterface {

    fun OnOrderListClick(position: Int,status:String)
}