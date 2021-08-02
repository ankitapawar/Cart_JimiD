package com.bccannco.admin.apis

import com.bccannco.admin.apis.ApiResponseModel.*
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    //    client credential
    @Headers("accept:application/json")
    @POST("rest_admin/oauth2/token/client_credentials")
    fun Client_Credentials(
        @Header("Authorization") APIKey: String
    ): Call<ClientCredential>

    //    authentication or login
    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("rest_admin/login")
    fun Authentication(
        @Header("Authorization") token: String,
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<StaffLogin>

    //    logout
    @Headers("accept:application/json")
    @POST("rest_admin/logout")
    fun logout(@Header("Authorization") token: String): Call<logout>

    //    basic Order list
    @Headers("accept:application/json")
    @GET("rest_admin/orders")
    fun OrderList(@Header("Authorization") token: String): Call<OrderList>

    //    Order list
    @Headers("accept:application/json")
    @GET("rest_admin/orders/basic")
    fun BasicOrderList(@Header("Authorization") token: String): Call<OrderList>

    //    Order list filter  largerthan
    @Headers("accept:application/json")
    @GET("rest_admin/orders/id_larger_than/{id}")
    fun Orders_Filter_Larger(
        @Header("Authorization") token: String,
        @Path("id ") OrderIDID: Int
    ): Call<OrderList>


    //    Order list from statusid
    @Headers("accept:application/json")
    @GET("rest_admin/orders/status/{statusID}")
    fun OrderListFormStatus(
        @Header("Authorization") token: String,
        @Path("statusID") statusID: Int
    ): Call<OrderList>

    //    Order Details
    @Headers("accept:application/json")
    @GET("rest_admin/orders/{orderid}")
    fun OrderDetails(
        @Header("Authorization") token: String,
        @Path("orderid") orderid: Int
    ): Call<OrderDetails>

    //   Basic Order Details
    @Headers("accept:application/json")
    @GET("rest_admin/orders/basic/{order_id}")
    fun BasicOrderDetails(
        @Header("Authorization") token: String,
        @Path("order_id") orderid: Int
    ): Call<BasicOrderDetails>

    //    Update Order status
    @FormUrlEncoded
    @Headers("accept:application/json")
    @PUT("rest_admin/order_status/{id}")
    fun UpdateOrderStatus(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Field("status") status: String
    ): Call<OrderList>

    //Newa latter
    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("rest_admin/newsletter/send")
    fun SendNewesLetter(
        @Header("Authorization") token: String,
        @Field("message") message: String,
        @Field("subject") subject: String
    ): Call<Newslatter>

    //    productList
    @Headers("accept:application/json")
    @GET("rest_admin/orders/status/{orderid}")
    fun ProductList(@Path("orderid") orderid: String): Call<OrderDetails>


    //    Chat list
    @Headers("accept:application/json")
    @GET("rest_admin/orders/status/{orderid}")
    fun ChatList(@Path("orderid") orderid: String): Call<LoginResponseModel>

    //    Personal chat
    @Headers("accept:application/json")
    @GET("rest_admin/orders/status/{orderid}")
    fun GetChat(@Path("orderid") orderid: String): Call<LoginResponseModel>

    @Headers("accept:application/json")
    @GET("rest_admin/chatbot/getStats/{userID}")
    fun GetUnreadMessageCount(
        @Header("Authorization") token: String,
        @Path("userID") userID: String): Call<UnreadMessage>

    //    Get Customer list
    @Headers("accept:application/json")
    @GET("rest_admin/customers/limit/{limit}/page/{page}")
    fun GetCustomerList(
        @Header("Authorization") token: String,
        @Path("limit") limit: Int,
        @Path("page") page: Int
    ): Call<CustomerList>


    @Headers("accept:application/json")
    @GET("rest_admin/chatbot/getAllCustomerMessage/{customerID}")
    fun getAllMessages(
        @Header("Authorization") token: String,
        @Path("customerID") customer_id: Int
    ): Call<GetChat>

    @Headers("accept:application/json")
    @GET("rest_admin/chatbot/getAssignedStaff/{customerID}")
    fun getAssignedStaffFromCustomerID(
        @Header("Authorization") token: String,
        @Path("customerID") customer_id: Int
    ): Call<PrimaryStaff>


    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("rest_admin/chatbot/sendMessageToCustomer")
    fun SendMessages(
        @Header("Authorization") token: String,
        @Field("customer_id") customer_id: Int,
        @Field("message") message: String,
        @Field("staff_id") staff_id: Int
    ): Call<SentMessage>

    //    send image into chat
    @Multipart
    @POST("rest_admin/chatbot/sendImageToCustomer")
    fun SendImages(
        @Header("Authorization") token: String,
        @Part("customer_id") customer_id: Int,
        @Part file: MultipartBody.Part,
        @Part("staff_id") staff_id: Int
    ): Call<SendImageIntoChat>

    @Headers("accept:application/json")
    @GET("rest_admin/graphs/dailySales")
    fun DashbordGraph(@Header("Authorization") token: String): Call<SalesGraph>

    //    get batch
    @Headers("accept:application/json")
    @GET("rest_admin/inventory/getBatches")
    fun GetBatch(@Header("Authorization") tokengetBatches: String): Call<GetBatch>

    //    get category
    @Headers("accept:application/json")
    @GET("rest_admin/categories/basic")
    fun GetCatogory(@Header("Authorization") token: String): Call<GetCategory>

    //    get batch
    @Headers("accept:application/json")
    @GET("rest_admin/inventory/getBatchById/batch_id/{batch_id} ")
    fun GetProductByCategory(
        @Header("Authorization") token: String,
//        @Path("batchid ") batch_id : String
        @Path("batch_id") batch_id : Int
    ): Call<GetProduct>

    //    send inventory data
    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("rest_admin/inventory/countInventory")
    fun PostInventory(
        @Header("Authorization") token: String,
        @Field("batch_id") batch_id: Int,
        @Field("count_qty") count_qty: Int,
        @Field("product_id") product_id: Int
    ): Call<PostInventory>

    //    get staff
    @Headers("accept:application/json")
    @GET("rest_admin/users")
    fun GetActiveStaff(
        @Header("Authorization") token: String
    ): Call<GetStaff>

    //    assign staff
    @FormUrlEncoded
    @Headers("accept:application/json")
    @POST("rest_admin/customers/assignStaff")
    fun StaffAssign(
        @Header("Authorization") token: String,
        @Field("customer_id") customer_id: Int,
        @Field("user_id") user_id: Int
    ): Call<StaffAssign>
}