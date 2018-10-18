package com.almin.freecomic.module.home.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.almin.freecomic.R
import com.almin.freecomic.extension.showToast
import com.almin.freecomic.module.common.ui.AbstractFcActivity
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AbstractFcActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var tabDelegate: TabDelegate
    private val dashBoardTab = Tab("首页",R.id.navigation_dashboard,DashboardTabFragment())
    private val newsTab = Tab("新闻",R.id.navigation_news,NewsTabFragment())
    private val followsTab = Tab("关注",R.id.navigation_follow,FollowsTabFragment())

    private var lastBackTime: Long = 0

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

        supportFragmentManager.addOnBackStackChangedListener {

        }
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
