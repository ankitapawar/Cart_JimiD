package com.bccannco.admin.apis.ApiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SalesGraph {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("error")
    @Expose
    private List<String> error = null;
    @SerializedName("data")
    @Expose
    private Data data;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public List<String> getError() {
        return error;
    }

    public void setError(List<String> error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {


        @SerializedName("dailysalesgraph")
        @Expose
        private String dailysalesgraph;

        public String getDailysalesgraph() {
            return dailysalesgraph;
        }

        public void setDailysalesgraph(String dailysalesgraph) {
            this.dailysalesgraph = dailysalesgraph;
        }
    }
}

