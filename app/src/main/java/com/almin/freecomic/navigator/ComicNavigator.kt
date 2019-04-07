package com.almin.freecomic.navigator

/**
 * Created by Almin on 2018/11/5.
 */
interface ComicNavigator{

    fun navigateToComicInfoPage(comicId: String, comicAvatarUrl: String)
    fun navigateToAuthorInfoPage(authorId: String)
    fun navigateToReadComicPage()

}