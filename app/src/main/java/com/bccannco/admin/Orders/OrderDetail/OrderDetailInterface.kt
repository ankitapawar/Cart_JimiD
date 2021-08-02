package com.bccannco.admin.Orders.OrderDetail

import com.bccannco.admin.BaseInterface

interface OrderDetailInterface : BaseInterface {

    fun OnItemClickEvent(position: Int)
}