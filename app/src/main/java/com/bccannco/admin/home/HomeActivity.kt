package com.bccannco.admin.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.bccannco.admin.BaseActivity
import com.bccannco.admin.Login.LoginActivity
import com.bccannco.admin.R
import com.bccannco.admin.apis.ApiResponseModel.UnreadMessage
import com.bccannco.admin.comman.*
import com.google.gson.Gson

class HomeActivity : BaseActivity(), HomeActivityInterface
{
    private val TAG = "HomeActivity"
    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var model: HomeActivityController

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        if (!get_BooleanPref(IS_LOGIN, false))
        {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }

        model = HomeActivityController(this, this)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
//        navView.setNavigationItemSelectedListener(this)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
            R.id.nav_order, R.id.nav_Inventory, R.id.nav_Chats, R.id.nav_Newsletter, R.id.nav_OrderDetail,), drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

        navView.setupWithNavController(navController)
    }

    //    Option menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        when (item.itemId)
        {
            R.id.action_logout ->
            {
                model.logoutApiCalling(get_StringPref(BEARER_TOKEN, ""))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean
    {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun <T> OnSuccess(result: T, identifier: Int)
    {
        Log.e(TAG, "OnSuccess: ")
        when (identifier)
        {
            0 ->
            {
            }
            1 ->
            {
            }
            LogoutapiIdentifier ->
            {
                set_StringPref(USERNAME, "")
                set_BooleanPref(IS_LOGIN, false)
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            GetUnreadMessageCount ->
            {
                val unreadresponse = result as UnreadMessage

                Log.e("unreadResponse", ""+ Gson().toJson(unreadresponse))

                if(unreadresponse.data.allunread.toString() != null)
                {
                    Log.e("allunread", ""+unreadresponse.data.allunread.toString())
                }
                if(unreadresponse.data.myunread.toString() != null)
                {
                    Log.e("myunread", ""+unreadresponse.data.myunread.toString())
                }
            }
        }
    }

    override fun <T> OnError(result: T)
    {
        if(result != null)
        {
            Log.e(TAG, "OnError: " + result as String)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        toast("SDF")
//        return false
//    }

//    override fun onSupportNavigateUp(): Boolean {
////        val navController = findNavController(R.id.nav_host_fragment)
//        toast("GG")
//        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
//    }

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        toast("SDF")
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        when (item.itemId) {
//
//            R.id.nav_home -> {
//                supportActionBar?.title = R.string.sales_Dashbord.toString()
//                fragmentTransaction.replace(R.id.nav_host_fragment, HomeFragment()).commit()
//            }
//            R.id.nav_order -> {
//                supportActionBar?.title = R.string.View_Orders.toString()
////                val fragmentManager = supportFragmentManager
////                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.remove(HomeFragment())
//                    .add(R.id.nav_host_fragment, OrderFragment())
//                    .replace(R.id.nav_host_fragment, OrderFragment()).commit()
//            }
//            R.id.nav_Inventory -> {
//                supportActionBar?.title = R.string.menu_Inventory.toString()
////                val fragmentManager = supportFragmentManager
////                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.remove(HomeFragment())
//                    .add(R.id.nav_host_fragment, OrderFragment())
//                    .replace(R.id.nav_host_fragment, InventoryFragment()).commit()
//            }
//            R.id.nav_Chats -> {
//                supportActionBar?.title = R.string.menu_chats.toString()
////                val fragmentManager = supportFragmentManager
////                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.remove(HomeFragment())
//                    .add(R.id.nav_host_fragment, OrderFragment())
//                    .replace(R.id.nav_host_fragment, ChatListFragment()).commit()
//            }
//
//            R.id.nav_Newsletter -> {
//                supportActionBar?.title = R.string.menu_newsletter.toString()
////                val fragmentManager = supportFragmentManager
////                val fragmentTransaction = fragmentManager.beginTransaction()
//                fragmentTransaction.remove(HomeFragment())
//                    .replace(R.id.nav_host_fragment, NewsletterFragment()).commit()
//            }
//
//            R.id.nav_logout -> {
////                drawerLayout.close()
//            }
//        }
//        drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
}