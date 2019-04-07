package com.almin.freecomic.module.comic.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.almin.freecomic.module.comic.ui.DetailInfoTabFragment
import com.almin.freecomic.module.registration.ui.SignUpFragment

/**
 * Created by Almin on 2018/11/14.
 */
class ComicInfoViewPageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {


    override fun getItem(position: Int): Fragment {
        return DetailInfoTabFragment()
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

    companion object {
        val titles: Array<String> = arrayOf("简介","选集")
    }
}