package com.bccannco.admin.apis.ApiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UnreadMessage
{
    @Expose
    private int success;

    @SerializedName("error")
    @Expose
    private List<String> error = null;

    @SerializedName("data")
    @Expose
    private Data data;

    public int getSuccess()
    {
        return success;
    }

    public void setSuccess(int success)
    {
        this.success = success;
    }

    public List<String> getError()
    {
        return error;
    }

    public void setError(List<String> error)
    {
        this.error = error;
    }

    public Data getData()
    {
        return data;
    }

    public void setData(Data data)
    {
        this.data = data;
    }

    public class Data
    {
        @SerializedName("myunread")
        @Expose
        private String myunread;

        @SerializedName("allunread")
        @Expose
        private String allunread;

        public String getMyunread()
        {
            return myunread;
        }

        public void setMyunread(String myunread)
        {
            this.myunread = myunread;
        }

        public String getAllunread()
        {
            return allunread;
        }

        public void setAllunread(String allunread)
        {
            this.allunread = allunread;
        }
    }
}
