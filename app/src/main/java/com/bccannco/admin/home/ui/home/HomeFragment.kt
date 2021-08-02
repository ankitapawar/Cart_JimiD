package com.bccannco.admin.home.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bccannco.admin.BaseFragment
import com.bccannco.admin.Orders.OrderDetail.OrderDetailsActivity
import com.bccannco.admin.Orders.OrderFragment
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.OrderList
import com.bccannco.admin.apis.ApiResponseModel.SalesGraph
import com.bccannco.admin.comman.*
import com.bccannco.admin.home.HomeActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment() : BaseFragment(), HomeFragInterface {

    lateinit var _context: Context
    private val TAG = "HomeFragment"
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var layoutmanager: LinearLayoutManager
    lateinit var madapter: OrderListAdapter
    lateinit var RvList: RecyclerView
    lateinit var list: List<OrderList>
    lateinit var SalesGraphResponse: SalesGraph

//    lateinit var toolbar: Toolbar

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = HomeViewModel(this)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
//        val textView: TextView = root.findViewById(R.id.text_home)

//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
//            Log.e(TAG, "onCreateView:--> " + it)
//            textView.text = it
//        })

//        toolbar = root.findViewById(R.id.toolbar)
        _context = (context?.applicationContext) as Context
        adapterInit(root)
        prefInit(_context)
        loaderInit(root)
        showDialog()
        if (isNetworkAvailable()) {
            homeViewModel.GetOrdeList(get_StringPref(BEARER_TOKEN, ""))
            homeViewModel.GetSalesGraph(get_StringPref(BEARER_TOKEN, ""))
        } else {
            hideDialog()
            toast("Please check your internet connectivity.")
        }

        return root
    }

    private fun adapterInit(view: View) {
        RvList = view.findViewById(R.id.Rv_orderList)
        layoutmanager = LinearLayoutManager(context)
        RvList.layoutManager = layoutmanager
    }

    override fun OnOrderListClick(position: Int, status: String) {
//        toast(position)
//        if (activity != null) {
//            (activity as HomeActivity).supportActionBar?.title = "Order"
//        }
        if (isNetworkAvailable()) {

            startActivity(
                Intent(context, OrderDetailsActivity::class.java).putExtra(
                    ORDER_ID,
                    position
                ).putExtra(STATUS, status)
            )
        } else {
            toast("Please check your internet connectivity.")
        }
//        val firstFragment = OrderFragment()
////        firstFragment.arguments = intent.extras
//        val transaction = fragmentManager?.beginTransaction()
//        transaction?.addToBackStack("view_order")
//        transaction?.replace(R.id.nav_host_fragment, firstFragment)
//        transaction?.commit()
//        nav_host_fragment
    }

    override fun <T> OnSuccess(result: T, identifier: Int) {
        hideDialog()
        Log.e(TAG, "OnSuccess: " + result)
        when (identifier) {
            OrderListapiIdentifier -> {
                list = listOf(result as OrderList)
//                Collections.reverse(list)
                madapter = OrderListAdapter(context, this, list)
                layoutmanager.reverseLayout = true
                layoutmanager.stackFromEnd = true
                RvList.adapter = madapter
                madapter.notifyDataSetChanged()
            }
            SalesGraphapiIdentifier -> {
                SalesGraphResponse = result as SalesGraph
//                aa_chart_view
                Picasso.get()
                    .load(SalesGraphResponse.data.dailysalesgraph)
                    .into(aa_chart_view)
            }
        }
    }

    override fun <T> OnError(result: T) {
        hideDialog()
        toast(result.toString())
    }

    override fun onResume() {
        super.onResume()
        if (activity != null) {
            (activity as HomeActivity).supportActionBar?.title = getString(R.string.sales_Dashbord)
        }
    }
}