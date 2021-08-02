package com.bccannco.admin.apis.ApiResponseModel;

import android.animation.TypeEvaluator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.lang.reflect.GenericArrayType;
import java.sql.Types;
import java.util.List;

public class CustomerList {


    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("error")
    @Expose
    private List<String> error = null;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("customer_id")
        @Expose
        private Integer customerId;
        @SerializedName("customer_group_id")
        @Expose
        private Integer customerGroupId;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("newsletter")
        @Expose
        private Integer newsletter;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("approved")
        @Expose
        private String approved;
        @SerializedName("safe")
        @Expose
        private Integer safe;
        @SerializedName("ip")
        @Expose
        private String ip;
        @SerializedName("telephone")
        @Expose
        private String telephone;
        @SerializedName("reward_points")
        @Expose
        private Integer rewardPoints;

        @SerializedName("account_custom_field")
        @Expose
        private List<GenericArrayType> accountCustomField = null;

        @SerializedName("custom_fields")
        @Expose
        private List<GenericArrayType> customFields = null;

        @SerializedName("affiliate")
        @Expose

        private List<GenericArrayType> affiliate = null;
        @SerializedName("addresses")
        @Expose

        private List<Address> addresses = null;
        @SerializedName("date_added")
        @Expose

        private String dateAdded;

        public Integer getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Integer customerId) {
            this.customerId = customerId;
        }

        public Integer getCustomerGroupId() {
            return customerGroupId;
        }

        public void setCustomerGroupId(Integer customerGroupId) {
            this.customerGroupId = customerGroupId;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getNewsletter() {
            return newsletter;
        }

        public void setNewsletter(Integer newsletter) {
            this.newsletter = newsletter;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getApproved() {
            return approved;
        }

        public void setApproved(String approved) {
            this.approved = approved;
        }

        public Integer getSafe() {
            return safe;
        }

        public void setSafe(Integer safe) {
            this.safe = safe;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public Integer getRewardPoints() {
            return rewardPoints;
        }

        public void setRewardPoints(Integer rewardPoints) {
            this.rewardPoints = rewardPoints;
        }

        public List<GenericArrayType> getAccountCustomField() {
            return accountCustomField;
        }

        public void setAccountCustomField(List<GenericArrayType> accountCustomField) {
            this.accountCustomField = accountCustomField;
        }

        public List<GenericArrayType> getCustomFields() {
            return customFields;
        }

        public void setCustomFields(List<GenericArrayType> customFields) {
            this.customFields = customFields;
        }

        public List<GenericArrayType> getAffiliate() {
            return affiliate;
        }

        public void setAffiliate(List<GenericArrayType> affiliate) {
            this.affiliate = affiliate;
        }

        public List<Address> getAddresses() {
            return addresses;
        }

        public void setAddresses(List<Address> addresses) {
            this.addresses = addresses;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public class Address {

            @SerializedName("address_id")
            @Expose
            private String addressId;
            @SerializedName("customer_id")
            @Expose
            private String customerId;
            @SerializedName("firstname")
            @Expose
            private String firstname;
            @SerializedName("lastname")
            @Expose
            private String lastname;
            @SerializedName("company")
            @Expose
            private String company;
            @SerializedName("address_1")
            @Expose
            private String address1;
            @SerializedName("address_2")
            @Expose
            private String address2;
            @SerializedName("postcode")
            @Expose
            private String postcode;
            @SerializedName("city")
            @Expose
            private String city;
            @SerializedName("zone_id")
            @Expose
            private String zoneId;
            @SerializedName("zone")
            @Expose
            private String zone;
            @SerializedName("zone_code")
            @Expose
            private String zoneCode;
            @SerializedName("country_id")
            @Expose
            private String countryId;
            @SerializedName("country")
            @Expose
            private String country;
            @SerializedName("iso_code_2")
            @Expose
            private String isoCode2;
            @SerializedName("iso_code_3")
            @Expose
            private String isoCode3;
            @SerializedName("address_format")
            @Expose
            private String addressFormat;
            @SerializedName("custom_field")
            @Expose
            private List<GenericArrayType> customField = null;

            public String getAddressId() {
                return addressId;
            }

            public void setAddressId(String addressId) {
                this.addressId = addressId;
            }

            public String getCustomerId() {
                return customerId;
            }

            public void setCustomerId(String customerId) {
                this.customerId = customerId;
            }

            public String getFirstname() {
                return firstname;
            }

            public void setFirstname(String firstname) {
                this.firstname = firstname;
            }

            public String getLastname() {
                return lastname;
            }

            public void setLastname(String lastname) {
                this.lastname = lastname;
            }

            public String getCompany() {
                return company;
            }

            public void setCompany(String company) {
                this.company = company;
            }

            public String getAddress1() {
                return address1;
            }

            public void setAddress1(String address1) {
                this.address1 = address1;
            }

            public String getAddress2() {
                return address2;
            }

            public void setAddress2(String address2) {
                this.address2 = address2;
            }

            public String getPostcode() {
                return postcode;
            }

            public void setPostcode(String postcode) {
                this.postcode = postcode;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getZoneId() {
                return zoneId;
            }

            public void setZoneId(String zoneId) {
                this.zoneId = zoneId;
            }

            public String getZone() {
                return zone;
            }

            public void setZone(String zone) {
                this.zone = zone;
            }

            public String getZoneCode() {
                return zoneCode;
            }

            public void setZoneCode(String zoneCode) {
                this.zoneCode = zoneCode;
            }

            public String getCountryId() {
                return countryId;
            }

            public void setCountryId(String countryId) {
                this.countryId = countryId;
            }

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
            }

            public String getIsoCode2() {
                return isoCode2;
            }

            public void setIsoCode2(String isoCode2) {
                this.isoCode2 = isoCode2;
            }

            public String getIsoCode3() {
                return isoCode3;
            }

            public void setIsoCode3(String isoCode3) {
                this.isoCode3 = isoCode3;
            }

            public String getAddressFormat() {
                return addressFormat;
            }

            public void setAddressFormat(String addressFormat) {
                this.addressFormat = addressFormat;
            }

            public List<GenericArrayType> getCustomField() {
                return customField;
            }

            public void setCustomField(List<GenericArrayType> customField) {
                this.customField = customField;
            }

        }

    }
}
