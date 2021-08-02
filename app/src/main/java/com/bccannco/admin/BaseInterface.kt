package com.bccannco.admin

interface BaseInterface {

    fun <T> OnSuccess(result: T,identifier: Int = 0)
    fun <T> OnError(result: T)
}