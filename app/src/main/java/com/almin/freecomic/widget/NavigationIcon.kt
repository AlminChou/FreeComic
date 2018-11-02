package com.almin.freecomic.widget

import com.almin.freecomic.R

/**
 * Created by Almin on 2018/6/25.
 */
data class NavigationIcon(val icon: Int, val iconColor: Int){

    companion object {
        val BACK = NavigationIcon(R.drawable.abc_ic_ab_back_material,0)
        val HOME = NavigationIcon(R.drawable.ic_menu_manage,0)
    }



//    val upArrow = ContextCompat.getDrawable(context, R.drawable.abc_ic_ab_back_material)
//                    upArrow?.setColorFilter(ContextCompat.getColor(context, android.R.color.white), PorterDuff.Mode.SRC_ATOP)
//                    supportActionBar?.setHomeAsUpIndicator(upArrow)

}
