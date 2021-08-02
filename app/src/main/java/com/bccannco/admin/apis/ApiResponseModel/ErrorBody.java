package com.bccannco.admin.apis.ApiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ErrorBody
{
    public class Example {

        @SerializedName("success")
        @Expose
        private int success;
        @SerializedName("error")
        @Expose
        private List<String> error = null;
        @SerializedName("data")
        @Expose
        private List<Object> data = null;

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

        public List<Object> getData() {
            return data;
        }

        public void setData(List<Object> data) {
            this.data = data;
        }

    }
}
