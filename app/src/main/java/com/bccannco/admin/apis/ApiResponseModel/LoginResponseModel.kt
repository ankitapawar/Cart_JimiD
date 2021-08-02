package com.bccannco.admin.apis.ApiResponseModel

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginResponseModel(
    val success: Int,
    val error: Array<String>,
    val data: List<data>

)

data class data(
    val user_id: Int,
    val user_group_id: Int,
    val username: String,
    val firstname: String,
    val lastname: String,
    val email: String,
    val image: String,
    val ip: String,
    val status: Int,
    val date_added: String,
    val user_group: String,
)