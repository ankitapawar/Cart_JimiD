package com.bccannco.admin.apis.ApiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrimaryStaff
{

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("error")
    @Expose
    private List<Object> error = null;
    @SerializedName("data")
    @Expose
    private List<Object> data = null;
    @SerializedName("staff_assigned")
    @Expose
    private StaffAssigned staffAssigned;

    public int getSuccess()
    {
        return success;
    }

    public void setSuccess(int success)
    {
        this.success = success;
    }

    public List<Object> getError()
    {
        return error;
    }

    public void setError(List<Object> error)
    {
        this.error = error;
    }

    public List<Object> getData()
    {
        return data;
    }

    public void setData(List<Object> data)
    {
        this.data = data;
    }

    public StaffAssigned getStaffAssigned()
    {
        return staffAssigned;
    }

    public void setStaffAssigned(StaffAssigned staffAssigned)
    {
        this.staffAssigned = staffAssigned;
    }

    public class StaffAssigned
    {
        @SerializedName("staff_id")
        @Expose
        private String staffId;

        public String getStaffId()
        {
            return staffId;
        }
        public void setStaffId(String staffId)
        {
            this.staffId = staffId;
        }

    }
}
