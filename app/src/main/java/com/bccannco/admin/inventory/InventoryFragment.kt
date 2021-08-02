package com.bccannco.admin.inventory

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.bccannco.admin.BaseFragment
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.GetBatch
import com.bccannco.admin.apis.ApiResponseModel.GetCategory
import com.bccannco.admin.apis.ApiResponseModel.GetProduct
import com.bccannco.admin.apis.ApiResponseModel.PostInventory
import com.bccannco.admin.comman.*
import com.squareup.picasso.Picasso

class InventoryFragment : BaseFragment(), View.OnClickListener, Inventoryinterface {
    lateinit var _context: Context
    private val TAG = "InventoryFragment"
    private val REQUEST_CODE = 101
    lateinit var Sp_Category: Spinner
    lateinit var Sp_Product: Spinner
    lateinit var Iv_Productimage: ImageView
    lateinit var Tv_Save: TextView
    lateinit var Et_Qty: EditText
    lateinit var batchlist: GetCategory
    lateinit var batch_list: GetBatch
    lateinit var productlist: GetProduct
    lateinit var model: InventoryModel
    lateinit var mapCategory: HashMap<String, Int>
    lateinit var mapBatch: HashMap<String, Int>
    lateinit var mapProduct: HashMap<String, Int>
    var product_id: Int = 0;
    var selectedBatchID: Int = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_inventory, container, false)

        Sp_Category = view.findViewById(R.id.Sp_Category)
        Sp_Product = view.findViewById(R.id.Sp_Product)
        Iv_Productimage = view.findViewById(R.id.Iv_Productimage)
        Tv_Save = view.findViewById(R.id.Tv_Save)
        Et_Qty = view.findViewById(R.id.Et_Qty)

        Iv_Productimage.setOnClickListener(this)
        Tv_Save.setOnClickListener(this)
        model = InventoryModel(context, this);
        _context = (context?.applicationContext) as Context

        _context = (context?.applicationContext) as Context
        prefInit(_context)
        loaderInit(view)
        showDialog()
        if (isNetworkAvailable()) {
            model.GetBatchApiCalling(get_StringPref(BEARER_TOKEN, ""))
        } else {
            hideDialog()
            toast("Please check your internet connectivity.")
        }

//        var Categoryadapter =
//            context?.let {
//                ArrayAdapter(
//                    it,
//                    android.R.layout.simple_spinner_item,
//                    resources.getStringArray(R.array.array_status)
//                )
//            }

        Sp_Category.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                showDialog()
                if (isNetworkAvailable()) {


                    selectedBatchID = mapBatch.get(parent.getItemAtPosition(position))!!.toInt();
                    model.GetProductApiCalling(get_StringPref(BEARER_TOKEN, "") , selectedBatchID);
                } else {
                    toast("Please check your internet connectivity.")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        Sp_Product.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View, position: Int, id: Long
            ) {
                if (productlist.data.size > 0) {
                    product_id = mapProduct.get(parent.getItemAtPosition(position).toString())!!
                    Picasso.get()
                        .load(productlist.data.get(position).image)
                        .into(Iv_Productimage)
                }
//                Iv_Productimage
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }
        return view
    }

    fun categorySpinnerAdapterInit(batcharray: Array<String?>) {
        val Categoryadapter =
            context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    batcharray
                )
            }
        Sp_Category.adapter = Categoryadapter
    }

    fun productSpinnerAdapterInit(productarray: Array<String?>) {
        val Productadapter =
            context?.let {
                ArrayAdapter(
                    it,
                    android.R.layout.simple_spinner_item,
                    productarray
                )
            }
        Sp_Product.adapter = Productadapter
    }

    fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onClick(view: View?) {
        when (view) {
            Iv_Productimage -> {
//                openGalleryForImage()
            }
            Tv_Save -> {
                if (Et_Qty.text.isEmpty() && Et_Qty.text.toString().equals("0")) {
                    toast("Please entery quntity")
                    return
                }
                if (product_id == 0) {
                    toast("Please select product")
                    return
                }

                if (isNetworkAvailable()) {
                    var qty: Int = (Et_Qty.text.toString()).toInt()
                    model.PostInventoryData(
                        get_StringPref(BEARER_TOKEN, ""),
                        selectedBatchID,
                        qty,
                        product_id
                    )
                } else {
                    toast("Please check your internet connectivity.")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE) {
            Iv_Productimage.setImageURI(data?.data) // handle chosen image
        }
    }

    override fun <T> OnSuccess(result: T, identifier: Int)
    {
        when (identifier)
        {
            GetCategoryApiIdentifier ->
            {
                batchlist = result as GetCategory

                mapCategory = HashMap()
                val batcharray = arrayOfNulls<String>(batchlist.data.size)
//                val str = arrayOfNulls<String>(10)
                for (i in 0..batchlist.data.size - 1)
                {
                    Log.e(TAG, "OnSuccess batcharray: " + batchlist.data.get(i).name)
                    Log.e(TAG, "OnSuccess: i = " + i + "  size => " + batchlist.data.size)

                    batcharray[i] = batchlist.data.get(i).name

                    mapCategory.put(batchlist.data.get(i).name, (batchlist.data.get(i).categoryId).toInt())
                    if (i == batchlist.data.size - 1)
                    {
                        categorySpinnerAdapterInit(batcharray = batcharray)
                        hideDialog()
                    }
                }
            }
            BatchApiIdentifier ->
            {
                batch_list = result as GetBatch

                mapBatch = HashMap()
                val batcharray = arrayOfNulls<String>(batch_list .data.size)
//                val str = arrayOfNulls<String>(10)
                for (i in 0..batch_list.data.size - 1)
                {
//                    Log.e(TAG, "OnSuccess batcharray: " + batch_list.data.get(i).name)
                    Log.e(TAG, "OnSuccess: i = " + i + "  size => " + batch_list.data.size)

                    batcharray[i] = batch_list.data.get(i).batchId

                    mapBatch.put(batch_list.data.get(i).batchId, batch_list.data.get(i).batchId.toInt())
                    if (i == batch_list.data.size - 1)
                    {
                        categorySpinnerAdapterInit(batcharray = batcharray)
                        hideDialog()
                    }
                }
            }
            GetProductByIdApiIdentifier -> {
                productlist = result as GetProduct
                mapProduct = HashMap()
                val productarray = arrayOfNulls<String>(productlist.data.size)
//                val str = arrayOfNulls<String>(10)
                for (i in 0..productlist.data.size - 1)
                {
                    Log.e(TAG, "OnSuccess batcharray: " + productlist.data.get(i).model)
                    Log.e(TAG, "OnSuccess: i = " + i + "  size => " + productlist.data.size)
                    productarray[i] = productlist.data.get(i).model

                    mapProduct.put(productlist.data.get(i).model, (productlist.data.get(i).productId).toInt())

                    if (i == productlist.data.size - 1)
                    {
                        productSpinnerAdapterInit(productarray = productarray)
                        hideDialog()
                    }
                }
            }
            PostInventorydApiIdentifier -> {
                hideDialog()
                val data: PostInventory = result as PostInventory
                if (data.success == 1) {
                    toast(data.data)
                    Et_Qty.text = null;
                }
            }
        }
    }

    override fun <T> OnError(result: T) {
        hideDialog()
        toast("" + result)
        Log.e(TAG, "OnError: " + result)
    }
}