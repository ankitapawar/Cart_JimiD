package com.bccannco.admin.apis.ApiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetChat {


    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("error")
    @Expose
    private List<String> error = null;
    @SerializedName("data")
    @Expose
    private List<Data> data;
    @SerializedName("user_msg_data")
    @Expose
    private List<UserMsgDatum> userMsgData = null;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public List<UserMsgDatum> getUserMsgData() {
        return userMsgData;
    }

    public void setUserMsgData(List<UserMsgDatum> userMsgData) {
        this.userMsgData = userMsgData;
    }

    public class UserMsgDatum {

        @SerializedName("message_id")
        @Expose
        private int messageId;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("sent_by")
        @Expose
        private String sentBy;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("customer_id")
        @Expose
        private int customerId;
        @SerializedName("user_id")
        @Expose
        private int userId;
        @SerializedName("user_read")
        @Expose
        private int userRead;
        @SerializedName("customer_read")
        @Expose
        private int customerRead;
        @SerializedName("sent_at")
        @Expose
        private String sentAt;

        public int getMessageId() {
            return messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getSentBy() {
            return sentBy;
        }

        public void setSentBy(String sentBy) {
            this.sentBy = sentBy;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getUserRead() {
            return userRead;
        }

        public void setUserRead(int userRead) {
            this.userRead = userRead;
        }

        public int getCustomerRead() {
            return customerRead;
        }

        public void setCustomerRead(int customerRead) {
            this.customerRead = customerRead;
        }

        public String getSentAt() {
            return sentAt;
        }

        public void setSentAt(String sentAt) {
            this.sentAt = sentAt;
        }
    }

    public class Data {


    }

}
