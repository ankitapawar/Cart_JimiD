package com.bccannco.admin.apis.ApiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetProduct
{
    @SerializedName("success")
    @Expose
    private int success;

    @SerializedName("error")
    @Expose
    private List<Object> error = null;

    @SerializedName("data")
    @Expose
    private List<Data> data = null;

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

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public class Data
    {
        @SerializedName("product_id")
        @Expose
        private String productId;

        @SerializedName("name")
        @Expose
        private String name;

        @SerializedName("description")
        @Expose
        private String description;

        @SerializedName("model")
        @Expose
        private String model;

        @SerializedName("quantity")
        @Expose
        private String quantity;

        @SerializedName("mpn")
        @Expose
        private String mpn;

        @SerializedName("price")
        @Expose
        private String price;

        @SerializedName("image")
        @Expose
        private String image;

        @SerializedName("counted")
        @Expose
        private String counted;

        @SerializedName("on_order")
        @Expose
        private Object onOrder;

        public String getProductId()
        {
            return productId;
        }

        public void setProductId(String productId)
        {
            this.productId = productId;
        }

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public String getDescription()
        {
            return description;
        }

        public void setDescription(String description)
        {
            this.description = description;
        }

        public String getModel()
        {
            return model;
        }

        public void setModel(String model)
        {
            this.model = model;
        }

        public String getQuantity()
        {
            return quantity;
        }

        public void setQuantity(String quantity)
        {
            this.quantity = quantity;
        }

        public String getMpn()
        {
            return mpn;
        }

        public void setMpn(String mpn)
        {
            this.mpn = mpn;
        }

        public String getPrice()
        {
            return price;
        }

        public void setPrice(String price)
        {
            this.price = price;
        }

        public String getImage()
        {
            return image;
        }

        public void setImage(String image)
        {
            this.image = image;
        }

        public String getCounted()
        {
            return counted;
        }

        public void setCounted(String counted)
        {
            this.counted = counted;
        }

        public Object getOnOrder()
        {
            return onOrder;
        }

        public void setOnOrder(Object onOrder)
        {
            this.onOrder = onOrder;
        }

    }
}
