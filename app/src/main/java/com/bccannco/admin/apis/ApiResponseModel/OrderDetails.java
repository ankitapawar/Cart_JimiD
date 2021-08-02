package com.bccannco.admin.apis.ApiResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OrderDetails {

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

        @SerializedName("order_id")
        @Expose
        private String orderId;
        @SerializedName("invoice_no")
        @Expose
        private String invoiceNo;
        @SerializedName("invoice_prefix")
        @Expose
        private String invoicePrefix;
        @SerializedName("store_id")
        @Expose
        private String storeId;
        @SerializedName("store_name")
        @Expose
        private String storeName;
        @SerializedName("store_url")
        @Expose
        private String storeUrl;
        @SerializedName("customer_id")
        @Expose
        private String customerId;
        @SerializedName("firstname")
        @Expose
        private String firstname;
        @SerializedName("lastname")
        @Expose
        private String lastname;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("telephone")
        @Expose
        private String telephone;
        @SerializedName("custom_field")
        @Expose
        private List<Object> customField = null;
        @SerializedName("payment_firstname")
        @Expose
        private String paymentFirstname;
        @SerializedName("payment_lastname")
        @Expose
        private String paymentLastname;
        @SerializedName("payment_company")
        @Expose
        private String paymentCompany;
        @SerializedName("payment_address_1")
        @Expose
        private String paymentAddress1;
        @SerializedName("payment_address_2")
        @Expose
        private String paymentAddress2;
        @SerializedName("payment_postcode")
        @Expose
        private String paymentPostcode;
        @SerializedName("payment_city")
        @Expose
        private String paymentCity;
        @SerializedName("payment_zone_id")
        @Expose
        private String paymentZoneId;
        @SerializedName("payment_zone")
        @Expose
        private String paymentZone;
        @SerializedName("payment_zone_code")
        @Expose
        private String paymentZoneCode;
        @SerializedName("payment_country_id")
        @Expose
        private String paymentCountryId;
        @SerializedName("payment_country")
        @Expose
        private String paymentCountry;
        @SerializedName("payment_iso_code_2")
        @Expose
        private String paymentIsoCode2;
        @SerializedName("payment_iso_code_3")
        @Expose
        private String paymentIsoCode3;
        @SerializedName("payment_address_format")
        @Expose
        private String paymentAddressFormat;
        @SerializedName("payment_custom_field")
        @Expose
        private List<Object> paymentCustomField = null;
        @SerializedName("payment_method")
        @Expose
        private String paymentMethod;
        @SerializedName("payment_code")
        @Expose
        private String paymentCode;
        @SerializedName("shipping_firstname")
        @Expose
        private String shippingFirstname;
        @SerializedName("shipping_lastname")
        @Expose
        private String shippingLastname;
        @SerializedName("shipping_company")
        @Expose
        private String shippingCompany;
        @SerializedName("shipping_address_1")
        @Expose
        private String shippingAddress1;
        @SerializedName("shipping_address_2")
        @Expose
        private String shippingAddress2;
        @SerializedName("shipping_postcode")
        @Expose
        private String shippingPostcode;
        @SerializedName("shipping_city")
        @Expose
        private String shippingCity;
        @SerializedName("shipping_zone_id")
        @Expose
        private String shippingZoneId;
        @SerializedName("shipping_zone")
        @Expose
        private String shippingZone;
        @SerializedName("shipping_zone_code")
        @Expose
        private String shippingZoneCode;
        @SerializedName("shipping_country_id")
        @Expose
        private String shippingCountryId;
        @SerializedName("shipping_country")
        @Expose
        private String shippingCountry;
        @SerializedName("shipping_iso_code_2")
        @Expose
        private String shippingIsoCode2;
        @SerializedName("shipping_iso_code_3")
        @Expose
        private String shippingIsoCode3;
        @SerializedName("shipping_address_format")
        @Expose
        private String shippingAddressFormat;
        @SerializedName("shipping_custom_field")
        @Expose
        private List<Object> shippingCustomField = null;
        @SerializedName("shipping_method")
        @Expose
        private String shippingMethod;
        @SerializedName("shipping_code")
        @Expose
        private String shippingCode;
        @SerializedName("tracking")
        @Expose
        private String tracking;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("total")
        @Expose
        private double total;
        @SerializedName("order_status_id")
        @Expose
        private String orderStatusId;
        @SerializedName("order_status")
        @Expose
        private String orderStatus;
        @SerializedName("affiliate_id")
        @Expose
        private String affiliateId;
        @SerializedName("commission")
        @Expose
        private String commission;
        @SerializedName("language_id")
        @Expose
        private String languageId;
        @SerializedName("language_code")
        @Expose
        private String languageCode;
        @SerializedName("currency_id")
        @Expose
        private String currencyId;
        @SerializedName("currency_code")
        @Expose
        private String currencyCode;
        @SerializedName("currency_value")
        @Expose
        private String currencyValue;
        @SerializedName("ip")
        @Expose
        private String ip;
        @SerializedName("forwarded_ip")
        @Expose
        private String forwardedIp;
        @SerializedName("user_agent")
        @Expose
        private String userAgent;
        @SerializedName("accept_language")
        @Expose
        private String acceptLanguage;
        @SerializedName("date_added")
        @Expose
        private String dateAdded;
        @SerializedName("date_modified")
        @Expose
        private String dateModified;
        @SerializedName("products")
        @Expose
        private List<Product> products = null;
        @SerializedName("histories")
        @Expose
        private List<History> histories = null;
        @SerializedName("totals")
        @Expose
        private List<Total> totals = null;
        @SerializedName("shipping_exclude_tax")
        @Expose
        private double shippingExcludeTax;
        @SerializedName("shipping_tax")
        @Expose
        private Integer shippingTax;
        @SerializedName("shipping_lines")
        @Expose
        private List<ShippingLine> shippingLines = null;
        @SerializedName("shipping_total")
        @Expose
        private double shippingTotal;
        @SerializedName("item_total_tax")
        @Expose
        private Integer itemTotalTax;
        @SerializedName("item_total_exclude_tax")
        @Expose
        private Integer itemTotalExcludeTax;
        @SerializedName("subtotal")
        @Expose
        private Integer subtotal;
        @SerializedName("coupons")
        @Expose
        private List<Object> coupons = null;
        @SerializedName("discounts")
        @Expose
        private List<Object> discounts = null;
        @SerializedName("atvpont")
        @Expose
        private String atvpont;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getInvoiceNo() {
            return invoiceNo;
        }

        public void setInvoiceNo(String invoiceNo) {
            this.invoiceNo = invoiceNo;
        }

        public String getInvoicePrefix() {
            return invoicePrefix;
        }

        public void setInvoicePrefix(String invoicePrefix) {
            this.invoicePrefix = invoicePrefix;
        }

        public String getStoreId() {
            return storeId;
        }

        public void setStoreId(String storeId) {
            this.storeId = storeId;
        }

        public String getStoreName() {
            return storeName;
        }

        public void setStoreName(String storeName) {
            this.storeName = storeName;
        }

        public String getStoreUrl() {
            return storeUrl;
        }

        public void setStoreUrl(String storeUrl) {
            this.storeUrl = storeUrl;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public List<Object> getCustomField() {
            return customField;
        }

        public void setCustomField(List<Object> customField) {
            this.customField = customField;
        }

        public String getPaymentFirstname() {
            return paymentFirstname;
        }

        public void setPaymentFirstname(String paymentFirstname) {
            this.paymentFirstname = paymentFirstname;
        }

        public String getPaymentLastname() {
            return paymentLastname;
        }

        public void setPaymentLastname(String paymentLastname) {
            this.paymentLastname = paymentLastname;
        }

        public String getPaymentCompany() {
            return paymentCompany;
        }

        public void setPaymentCompany(String paymentCompany) {
            this.paymentCompany = paymentCompany;
        }

        public String getPaymentAddress1() {
            return paymentAddress1;
        }

        public void setPaymentAddress1(String paymentAddress1) {
            this.paymentAddress1 = paymentAddress1;
        }

        public String getPaymentAddress2() {
            return paymentAddress2;
        }

        public void setPaymentAddress2(String paymentAddress2) {
            this.paymentAddress2 = paymentAddress2;
        }

        public String getPaymentPostcode() {
            return paymentPostcode;
        }

        public void setPaymentPostcode(String paymentPostcode) {
            this.paymentPostcode = paymentPostcode;
        }

        public String getPaymentCity() {
            return paymentCity;
        }

        public void setPaymentCity(String paymentCity) {
            this.paymentCity = paymentCity;
        }

        public String getPaymentZoneId() {
            return paymentZoneId;
        }

        public void setPaymentZoneId(String paymentZoneId) {
            this.paymentZoneId = paymentZoneId;
        }

        public String getPaymentZone() {
            return paymentZone;
        }

        public void setPaymentZone(String paymentZone) {
            this.paymentZone = paymentZone;
        }

        public String getPaymentZoneCode() {
            return paymentZoneCode;
        }

        public void setPaymentZoneCode(String paymentZoneCode) {
            this.paymentZoneCode = paymentZoneCode;
        }

        public String getPaymentCountryId() {
            return paymentCountryId;
        }

        public void setPaymentCountryId(String paymentCountryId) {
            this.paymentCountryId = paymentCountryId;
        }

        public String getPaymentCountry() {
            return paymentCountry;
        }

        public void setPaymentCountry(String paymentCountry) {
            this.paymentCountry = paymentCountry;
        }

        public String getPaymentIsoCode2() {
            return paymentIsoCode2;
        }

        public void setPaymentIsoCode2(String paymentIsoCode2) {
            this.paymentIsoCode2 = paymentIsoCode2;
        }

        public String getPaymentIsoCode3() {
            return paymentIsoCode3;
        }

        public void setPaymentIsoCode3(String paymentIsoCode3) {
            this.paymentIsoCode3 = paymentIsoCode3;
        }

        public String getPaymentAddressFormat() {
            return paymentAddressFormat;
        }

        public void setPaymentAddressFormat(String paymentAddressFormat) {
            this.paymentAddressFormat = paymentAddressFormat;
        }

        public List<Object> getPaymentCustomField() {
            return paymentCustomField;
        }

        public void setPaymentCustomField(List<Object> paymentCustomField) {
            this.paymentCustomField = paymentCustomField;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getPaymentCode() {
            return paymentCode;
        }

        public void setPaymentCode(String paymentCode) {
            this.paymentCode = paymentCode;
        }

        public String getShippingFirstname() {
            return shippingFirstname;
        }

        public void setShippingFirstname(String shippingFirstname) {
            this.shippingFirstname = shippingFirstname;
        }

        public String getShippingLastname() {
            return shippingLastname;
        }

        public void setShippingLastname(String shippingLastname) {
            this.shippingLastname = shippingLastname;
        }

        public String getShippingCompany() {
            return shippingCompany;
        }

        public void setShippingCompany(String shippingCompany) {
            this.shippingCompany = shippingCompany;
        }

        public String getShippingAddress1() {
            return shippingAddress1;
        }

        public void setShippingAddress1(String shippingAddress1) {
            this.shippingAddress1 = shippingAddress1;
        }

        public String getShippingAddress2() {
            return shippingAddress2;
        }

        public void setShippingAddress2(String shippingAddress2) {
            this.shippingAddress2 = shippingAddress2;
        }

        public String getShippingPostcode() {
            return shippingPostcode;
        }

        public void setShippingPostcode(String shippingPostcode) {
            this.shippingPostcode = shippingPostcode;
        }

        public String getShippingCity() {
            return shippingCity;
        }

        public void setShippingCity(String shippingCity) {
            this.shippingCity = shippingCity;
        }

        public String getShippingZoneId() {
            return shippingZoneId;
        }

        public void setShippingZoneId(String shippingZoneId) {
            this.shippingZoneId = shippingZoneId;
        }

        public String getShippingZone() {
            return shippingZone;
        }

        public void setShippingZone(String shippingZone) {
            this.shippingZone = shippingZone;
        }

        public String getShippingZoneCode() {
            return shippingZoneCode;
        }

        public void setShippingZoneCode(String shippingZoneCode) {
            this.shippingZoneCode = shippingZoneCode;
        }

        public String getShippingCountryId() {
            return shippingCountryId;
        }

        public void setShippingCountryId(String shippingCountryId) {
            this.shippingCountryId = shippingCountryId;
        }

        public String getShippingCountry() {
            return shippingCountry;
        }

        public void setShippingCountry(String shippingCountry) {
            this.shippingCountry = shippingCountry;
        }

        public String getShippingIsoCode2() {
            return shippingIsoCode2;
        }

        public void setShippingIsoCode2(String shippingIsoCode2) {
            this.shippingIsoCode2 = shippingIsoCode2;
        }

        public String getShippingIsoCode3() {
            return shippingIsoCode3;
        }

        public void setShippingIsoCode3(String shippingIsoCode3) {
            this.shippingIsoCode3 = shippingIsoCode3;
        }

        public String getShippingAddressFormat() {
            return shippingAddressFormat;
        }

        public void setShippingAddressFormat(String shippingAddressFormat) {
            this.shippingAddressFormat = shippingAddressFormat;
        }

        public List<Object> getShippingCustomField() {
            return shippingCustomField;
        }

        public void setShippingCustomField(List<Object> shippingCustomField) {
            this.shippingCustomField = shippingCustomField;
        }

        public String getShippingMethod() {
            return shippingMethod;
        }

        public void setShippingMethod(String shippingMethod) {
            this.shippingMethod = shippingMethod;
        }

        public String getShippingCode() {
            return shippingCode;
        }

        public void setShippingCode(String shippingCode) {
            this.shippingCode = shippingCode;
        }

        public String getTracking() {
            return tracking;
        }

        public void setTracking(String tracking) {
            this.tracking = tracking;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public String getOrderStatusId() {
            return orderStatusId;
        }

        public void setOrderStatusId(String orderStatusId) {
            this.orderStatusId = orderStatusId;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public String getAffiliateId() {
            return affiliateId;
        }

        public void setAffiliateId(String affiliateId) {
            this.affiliateId = affiliateId;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getLanguageId() {
            return languageId;
        }

        public void setLanguageId(String languageId) {
            this.languageId = languageId;
        }

        public String getLanguageCode() {
            return languageCode;
        }

        public void setLanguageCode(String languageCode) {
            this.languageCode = languageCode;
        }

        public String getCurrencyId() {
            return currencyId;
        }

        public void setCurrencyId(String currencyId) {
            this.currencyId = currencyId;
        }

        public String getCurrencyCode() {
            return currencyCode;
        }

        public void setCurrencyCode(String currencyCode) {
            this.currencyCode = currencyCode;
        }

        public String getCurrencyValue() {
            return currencyValue;
        }

        public void setCurrencyValue(String currencyValue) {
            this.currencyValue = currencyValue;
        }

        public String getIp() {
            return ip;
        }

        public void setIp(String ip) {
            this.ip = ip;
        }

        public String getForwardedIp() {
            return forwardedIp;
        }

        public void setForwardedIp(String forwardedIp) {
            this.forwardedIp = forwardedIp;
        }

        public String getUserAgent() {
            return userAgent;
        }

        public void setUserAgent(String userAgent) {
            this.userAgent = userAgent;
        }

        public String getAcceptLanguage() {
            return acceptLanguage;
        }

        public void setAcceptLanguage(String acceptLanguage) {
            this.acceptLanguage = acceptLanguage;
        }

        public String getDateAdded() {
            return dateAdded;
        }

        public void setDateAdded(String dateAdded) {
            this.dateAdded = dateAdded;
        }

        public String getDateModified() {
            return dateModified;
        }

        public void setDateModified(String dateModified) {
            this.dateModified = dateModified;
        }

        public List<Product> getProducts() {
            return products;
        }

        public void setProducts(List<Product> products) {
            this.products = products;
        }

        public List<History> getHistories() {
            return histories;
        }

        public void setHistories(List<History> histories) {
            this.histories = histories;
        }

        public List<Total> getTotals() {
            return totals;
        }

        public void setTotals(List<Total> totals) {
            this.totals = totals;
        }

        public double getShippingExcludeTax() {
            return shippingExcludeTax;
        }

        public void setShippingExcludeTax(double shippingExcludeTax) {
            this.shippingExcludeTax = shippingExcludeTax;
        }

        public Integer getShippingTax() {
            return shippingTax;
        }

        public void setShippingTax(Integer shippingTax) {
            this.shippingTax = shippingTax;
        }

        public List<ShippingLine> getShippingLines() {
            return shippingLines;
        }

        public void setShippingLines(List<ShippingLine> shippingLines) {
            this.shippingLines = shippingLines;
        }

        public double getShippingTotal() {
            return shippingTotal;
        }

        public void setShippingTotal(double shippingTotal) {
            this.shippingTotal = shippingTotal;
        }

        public Integer getItemTotalTax() {
            return itemTotalTax;
        }

        public void setItemTotalTax(Integer itemTotalTax) {
            this.itemTotalTax = itemTotalTax;
        }

        public Integer getItemTotalExcludeTax() {
            return itemTotalExcludeTax;
        }

        public void setItemTotalExcludeTax(Integer itemTotalExcludeTax) {
            this.itemTotalExcludeTax = itemTotalExcludeTax;
        }

        public Integer getSubtotal() {
            return subtotal;
        }

        public void setSubtotal(Integer subtotal) {
            this.subtotal = subtotal;
        }

        public List<Object> getCoupons() {
            return coupons;
        }

        public void setCoupons(List<Object> coupons) {
            this.coupons = coupons;
        }

        public List<Object> getDiscounts() {
            return discounts;
        }

        public void setDiscounts(List<Object> discounts) {
            this.discounts = discounts;
        }

        public String getAtvpont() {
            return atvpont;
        }

        public void setAtvpont(String atvpont) {
            this.atvpont = atvpont;
        }

        public class History {

            @SerializedName("notify")
            @Expose
            private String notify;
            @SerializedName("status")
            @Expose
            private String status;
            @SerializedName("comment")
            @Expose
            private String comment;
            @SerializedName("date_added")
            @Expose
            private String dateAdded;

            public String getNotify() {
                return notify;
            }

            public void setNotify(String notify) {
                this.notify = notify;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public String getDateAdded() {
                return dateAdded;
            }

            public void setDateAdded(String dateAdded) {
                this.dateAdded = dateAdded;
            }

        }

        public class Product {

            @SerializedName("order_product_id")
            @Expose
            private Integer orderProductId;
            @SerializedName("product_id")
            @Expose
            private Integer productId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("model")
            @Expose
            private String model;
            @SerializedName("sku")
            @Expose
            private String sku;
            @SerializedName("option")
            @Expose
            private List<Option> option = null;
            @SerializedName("quantity")
            @Expose
            private Integer quantity;
            @SerializedName("tax_class_id")
            @Expose
            private Integer taxClassId;
            @SerializedName("currency_code")
            @Expose
            private String currencyCode;
            @SerializedName("currency_value")
            @Expose
            private String currencyValue;
            @SerializedName("price_formated")
            @Expose
            private String priceFormated;
            @SerializedName("price")
            @Expose
            private Integer price;
            @SerializedName("price_exclude_tax")
            @Expose
            private Integer priceExcludeTax;
            @SerializedName("tax")
            @Expose
            private Integer tax;
            @SerializedName("total_formated")
            @Expose
            private String totalFormated;
            @SerializedName("total")
            @Expose
            private Integer total;
            @SerializedName("total_exclude_tax")
            @Expose
            private Integer totalExcludeTax;
            @SerializedName("total_tax")
            @Expose
            private Integer totalTax;

            public Integer getOrderProductId() {
                return orderProductId;
            }

            public void setOrderProductId(Integer orderProductId) {
                this.orderProductId = orderProductId;
            }

            public Integer getProductId() {
                return productId;
            }

            public void setProductId(Integer productId) {
                this.productId = productId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getModel() {
                return model;
            }

            public void setModel(String model) {
                this.model = model;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }

            public List<Option> getOption() {
                return option;
            }

            public void setOption(List<Option> option) {
                this.option = option;
            }

            public Integer getQuantity() {
                return quantity;
            }

            public void setQuantity(Integer quantity) {
                this.quantity = quantity;
            }

            public Integer getTaxClassId() {
                return taxClassId;
            }

            public void setTaxClassId(Integer taxClassId) {
                this.taxClassId = taxClassId;
            }

            public String getCurrencyCode() {
                return currencyCode;
            }

            public void setCurrencyCode(String currencyCode) {
                this.currencyCode = currencyCode;
            }

            public String getCurrencyValue() {
                return currencyValue;
            }

            public void setCurrencyValue(String currencyValue) {
                this.currencyValue = currencyValue;
            }

            public String getPriceFormated() {
                return priceFormated;
            }

            public void setPriceFormated(String priceFormated) {
                this.priceFormated = priceFormated;
            }

            public Integer getPrice() {
                return price;
            }

            public void setPrice(Integer price) {
                this.price = price;
            }

            public Integer getPriceExcludeTax() {
                return priceExcludeTax;
            }

            public void setPriceExcludeTax(Integer priceExcludeTax) {
                this.priceExcludeTax = priceExcludeTax;
            }

            public Integer getTax() {
                return tax;
            }

            public void setTax(Integer tax) {
                this.tax = tax;
            }

            public String getTotalFormated() {
                return totalFormated;
            }

            public void setTotalFormated(String totalFormated) {
                this.totalFormated = totalFormated;
            }

            public Integer getTotal() {
                return total;
            }

            public void setTotal(Integer total) {
                this.total = total;
            }

            public Integer getTotalExcludeTax() {
                return totalExcludeTax;
            }

            public void setTotalExcludeTax(Integer totalExcludeTax) {
                this.totalExcludeTax = totalExcludeTax;
            }

            public Integer getTotalTax() {
                return totalTax;
            }

            public void setTotalTax(Integer totalTax) {
                this.totalTax = totalTax;
            }

        }

        public class Total {

            @SerializedName("title")
            @Expose
            private String title;
            @SerializedName("text")
            @Expose
            private String text;
            @SerializedName("value")
            @Expose
            private double value;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public double getValue() {
                return value;
            }

            public void setValue(double value) {
                this.value = value;
            }
        }

        public class ShippingLine {

            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("price")
            @Expose
            private Integer price;
            @SerializedName("tax")
            @Expose
            private Integer tax;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Integer getPrice() {
                return price;
            }

            public void setPrice(Integer price) {
                this.price = price;
            }

            public Integer getTax() {
                return tax;
            }

            public void setTax(Integer tax) {
                this.tax = tax;
            }
        }

        public class Option {

            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("value")
            @Expose
            private String value;
            @SerializedName("type")
            @Expose
            private String type;
            @SerializedName("product_option_id")
            @Expose
            private Integer productOptionId;
            @SerializedName("product_option_value_id")
            @Expose
            private Integer productOptionValueId;
            @SerializedName("option_id")
            @Expose
            private Integer optionId;
            @SerializedName("option_value_id")
            @Expose
            private Integer optionValueId;
            @SerializedName("sku")
            @Expose
            private String sku;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Integer getProductOptionId() {
                return productOptionId;
            }

            public void setProductOptionId(Integer productOptionId) {
                this.productOptionId = productOptionId;
            }

            public Integer getProductOptionValueId() {
                return productOptionValueId;
            }

            public void setProductOptionValueId(Integer productOptionValueId) {
                this.productOptionValueId = productOptionValueId;
            }

            public Integer getOptionId() {
                return optionId;
            }

            public void setOptionId(Integer optionId) {
                this.optionId = optionId;
            }

            public Integer getOptionValueId() {
                return optionValueId;
            }

            public void setOptionValueId(Integer optionValueId) {
                this.optionValueId = optionValueId;
            }

            public String getSku() {
                return sku;
            }

            public void setSku(String sku) {
                this.sku = sku;
            }
        }
    }
}