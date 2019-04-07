package com.almin.freecomic.module.common.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.*
import android.widget.RelativeLayout
import com.almin.freecomic.R
import com.almin.freecomic.extension.afterKikat
import com.almin.freecomic.extension.getStatusbarHeight
import com.almin.freecomic.module.common.contract.AbstractContract
import com.almin.freecomic.module.common.contract.AbstractContract.Presenter
import com.almin.freecomic.widget.NavigationIcon
import kotlinx.android.synthetic.main.root_toolbar_layout.view.*
import com.almin.freecomic.extension.showToast as quickShowToast


/**
 * Created by Almin on 2018/6/11.
 */
abstract class AbstractFcFragment : AbstractFragment(), AbstractContract.ViewRenderer{
    protected var autoAttachToolbar = true
    protected var toolbarBackgroundColor: Int = 0
    protected var navigationIcon = NavigationIcon.BACK

    protected abstract val presenter : Presenter?
    protected abstract fun initView(view: View)
    protected abstract fun initData()

//    protected var mToolbar: Toolbar ? = null

    final override fun onAttach(context: Context) {
        super.onAttach(context)
        onFcAttatch(context)
        if (presenter == null){
            throw IllegalArgumentException("Presenter can not be null, please check!")
        }
        arguments?.let {
            presenter!!.start(it)
        }
    }

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onFcCreate()
    }

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        val pageRootView = if(layoutResourceId > 0) inflater.inflate(layoutResourceId,container,false) as ViewGroup else null

        return when(autoAttachToolbar){
            true -> {
                val appRootPage = inflater.inflate(R.layout.root_toolbar_layout,container,false) as ViewGroup
                if(pageRootView!=null){
                    val relativeLayoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
                    relativeLayoutParams.addRule(RelativeLayout.BELOW, R.id.app_bar)
                    appRootPage.addView(pageRootView,relativeLayoutParams)
                }
                // init common toolbar
                initToolbar(appRootPage.toolbar)
                return appRootPage
            }
            false -> pageRootView
        }
    }


    protected open fun initToolbar(toolbar: Toolbar) {
        with(toolbar){
            title = pageTitle

            if(toolbarBackgroundColor > 0){
                setBackgroundColor(ContextCompat.getColor(context, toolbarBackgroundColor))
//                activity!!.window.statusBarColor = ContextCompat.getColor(context, toolbarBackgroundColor)
            }

            (activity as AppCompatActivity).setSupportActionBar(this)
            (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)

//          (activity as AppCompatActivity).supportActionBar!!.setDisplayShowTitleEnabled(true)

            setNavigationIcon(this@AbstractFcFragment.navigationIcon.icon)
            setNavigationOnClickListener {
                onNavigationClick()
            }

            afterKikat{
                // statusbar
                val layoutParams = activity!!.window.attributes
                layoutParams.flags = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or layoutParams.flags
                (toolbar.parent as View).setPadding(paddingLeft,getStatusbarHeight(),paddingRight,paddingBottom)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        menu!!.clear()
        if (menuResourceId > 0) inflater!!.inflate(menuResourceId,menu) else super.onCreateOptionsMenu(menu, inflater)
    }

    protected fun onNavigationClick() {
        println("1212121212121212")
        goBack()
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    final override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    final override fun onResume() {
        super.onResume()
        onFcResume()
    }

    //final will crash
    override fun onDestroyView() {
        onFcDestroyView()
        super.onDestroyView()
    }

    final override fun onDestroy() {
        onFcDestroy()
        super.onDestroy()
    }

    final override fun onDetach() {
        presenter!!.detach()
        super.onDetach()
    }

    protected open fun onFcAttatch(context: Context){

    }

    protected open fun onFcCreate(){

    }

    protected open fun onFcResume(){

    }

    protected open fun onFcDestroyView(){

    }

    protected open fun onFcDestroy(){

    }

    override fun showSpinner() {
    }

    override fun hideSpinner() {
    }

    override fun goBack() {
        activity!!.onBackPressed()
    }

    override fun showToast(msg: String) {
        quickShowToast(msg)
    }

    override fun showSnackBar(msg: String) {
    }
}