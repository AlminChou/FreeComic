package com.almin.freecomic.module.home.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.almin.freecomic.R
import com.almin.freecomic.extension.showToast
import com.almin.freecomic.imageloader.FcImageLoader
import com.almin.freecomic.imageloader.GlideApp
import com.almin.freecomic.module.common.datasource.model.UserProfile
import com.almin.freecomic.module.common.ui.AbstractFcActivity
import com.almin.library.imageloader.ImageLoader
import com.almin.library.imageloader.component.DisplayOptions
import com.almin.library.imageloader.component.ScaleType
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.nav_header_home.view.*
import org.koin.android.ext.android.inject


class HomeActivity : AbstractFcActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var tabDelegate: TabDelegate
    private val dashBoardTab = Tab("首页",R.id.navigation_dashboard,DashboardTabFragment())
    private val newsTab = Tab("新闻",R.id.navigation_news,NewsTabFragment())
    private val followsTab = Tab("关注",R.id.navigation_follow,FollowsTabFragment())

    private var lastBackTime: Long = 0

    private val imageLoader: ImageLoader by inject()
    private val user: UserProfile by inject()

    companion object {
        fun go(activity : AppCompatActivity){
            val intent = Intent(activity, HomeActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // init drawer
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, null,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener(this)


        // init tab switch delegate
        tabDelegate = HomePageDelegate(R.id.fl_home_container,supportFragmentManager)
        tabDelegate.addTab(dashBoardTab)
        tabDelegate.addTab(newsTab)
        tabDelegate.addTab(followsTab)
        tabDelegate.setUpTabNavigationView(navigation)


        // init user profile
        val headerView = nav_view.getHeaderView(0)
        val avatarView = headerView.findViewById<ImageView>(R.id.iv_avatar)
        val displayOptions = DisplayOptions.create()
        displayOptions.scaleType = ScaleType.CIRCLE_CROP
        imageLoader.load(user.photo,avatarView,displayOptions)
        headerView.findViewById<TextView>(R.id.tv_username).text = user.nickname
        headerView.findViewById<TextView>(R.id.tv_email).text = user.email
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if(!tabDelegate.handleBackPressed()){
                // kill
                val time = System.currentTimeMillis()
                if (time - lastBackTime < 800) {
                    finish()
                } else {
                    lastBackTime = time
                    showToast("再按一次返回")
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.login_page_menu, menu)
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_follow -> {
                //
            }
            R.id.nav_read_history -> {

            }
            R.id.nav_download_cache -> {

            }
            R.id.nav_read_setting -> {

            }
            R.id.nav_notification_setting -> {

            }
            R.id.nav_account_setting -> {

            }
            R.id.nav_logout -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }


}
