package com.bccannco.admin.apis.ApiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SentMessage {

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("error")
    @Expose
    private List<Object> error = null;
    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("user_msg_data")
    @Expose
    private List<UserMsgDatum> userMsgData = null;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<Object> getError() {
        return error;
    }

    public void setError(List<Object> error) {
        this.error = error;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
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
        private String messageId;
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
        private String customerId;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("user_read")
        @Expose
        private String userRead;
        @SerializedName("customer_read")
        @Expose
        private String customerRead;
        @SerializedName("sent_at")
        @Expose
        private String sentAt;

        public String getMessageId() {
            return messageId;
        }

        public void setMessageId(String messageId) {
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

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserRead() {
            return userRead;
        }

        public void setUserRead(String userRead) {
            this.userRead = userRead;
        }

        public String getCustomerRead() {
            return customerRead;
        }

        public void setCustomerRead(String customerRead) {
            this.customerRead = customerRead;
        }

        public String getSentAt() {
            return sentAt;
        }

        public void setSentAt(String sentAt) {
            this.sentAt = sentAt;
        }
    }
}
