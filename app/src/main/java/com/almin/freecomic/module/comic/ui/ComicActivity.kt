package com.almin.freecomic.module.comic.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.almin.freecomic.module.comic.contract.Intent.Companion.BUNDLE_KEY_COMIC_AVATAR_URL
import com.almin.freecomic.module.comic.contract.Intent.Companion.BUNDLE_KEY_COMIC_ID
import com.almin.freecomic.module.common.ui.AbstractFcActivity
import com.almin.freecomic.navigator.ComicNavigator

/**
 * Created by Almin on 2018/11/2.
 */
class ComicActivity: AbstractFcActivity(), ComicNavigator{

    companion object {
        fun go(activity: Activity, comicId: String, avatarUrl: String){
            val intent = Intent(activity, ComicActivity::class.java)
            intent.putExtra(BUNDLE_KEY_COMIC_ID,comicId)
            intent.putExtra(BUNDLE_KEY_COMIC_AVATAR_URL,avatarUrl)
            activity.startActivity(intent)
        }
    }

    init {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToComicInfoPage(
                intent.getStringExtra(BUNDLE_KEY_COMIC_ID),
                intent.getStringExtra(BUNDLE_KEY_COMIC_AVATAR_URL))
    }

    override fun navigateToComicInfoPage(comicId: String, comicAvatarUrl: String) {
        fragmentOperator.replace(ComicInfoFragment.newInstance(comicId,comicAvatarUrl))
    }

    override fun navigateToAuthorInfoPage(authorId: String) {
    }

    override fun navigateToReadComicPage() {
    }
}