package com.almin.freecomic.module.comic.ui

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.graphics.ColorUtils
import android.support.v7.graphics.Palette
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.extension.parseTime
import com.almin.freecomic.module.comic.adapter.ComicInfoViewPageAdapter
import com.almin.freecomic.module.comic.contract.ComicInfoContract
import com.almin.freecomic.module.comic.contract.Intent.Companion.BUNDLE_KEY_COMIC_AVATAR_URL
import com.almin.freecomic.module.comic.contract.Intent.Companion.BUNDLE_KEY_COMIC_ID
import com.almin.freecomic.module.comic.datasource.model.ComicInfo
import com.almin.freecomic.module.common.ui.AbstractFcFragment
import com.almin.freecomic.navigator.ComicNavigator
import com.almin.library.imageloader.ImageLoader
import com.almin.library.imageloader.component.BitmapTarget
import kotlinx.android.synthetic.main.fragment_comic_info.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.*


/**
 * Created by Almin on 2018/11/2.
 */
class ComicInfoFragment: AbstractFcFragment() , ComicInfoContract.ViewRenderer{
    private lateinit var navigator: ComicNavigator
    private val imageLoader: ImageLoader by inject()

    override val presenter: ComicInfoContract.Presenter by inject{ parametersOf(this,this) }


    init{
        autoAttachToolbar = false
        layoutResourceId = R.layout.fragment_comic_info
        pageTitle = ""
    }

    override fun onFcAttatch(context: Context) {
         if(context is ComicNavigator){
             navigator = context
         }
    }

    override fun initView(view: View) {
        initToolbar(toolbar)

        fab_read.setOnClickListener {
            presenter.clickRead()
        }
        tl_comic_info_category.setupWithViewPager(vp_comic_info_category)

        app_bar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
//            val alpha: Float = (appBarLayout.bottom.toFloat()/appBarLayout.height.toFloat())
            val alpha: Float = ((appBarLayout.bottom-400).toFloat()/(appBarLayout.height - 400).toFloat())
            cl_comic_base_info.alpha = alpha
        }
    }

    override fun initData() {
        presenter.loadComicInfo()
    }

    override fun navigateToReadComicPage() {
        navigator.navigateToReadComicPage()
    }

    override fun displayComicInfo(info: ComicInfo) {
        tv_comic_title.text = info.title
        tv_comic_authors.text = info.authors
        tv_comic_tags.text = info.typeFlags
        tv_comic_update_status.text = Calendar.getInstance().parseTime(info.lastUpdateTime,"yyyy-MM-dd")
        vp_comic_info_category.adapter = ComicInfoViewPageAdapter(childFragmentManager)

        imageLoader.loadPicBitmap(activity!!.application, info.avatar,
                bitmapTarget = object : BitmapTarget{
                    override fun onBitmapReady(bitmap: Bitmap) {
                    }

                    override fun onBitmapTransform(bitmap: Bitmap): Bitmap {
                        iv_comic_avatar.setImageBitmap(bitmap)
                        Palette.from(bitmap).generate { palette ->
                            var swatch: Palette.Swatch ? = null
                            palette.swatches.isNotEmpty().let {
                                swatch = when {
                                    palette.vibrantSwatch!=null -> palette.vibrantSwatch
                                    palette.darkVibrantSwatch!=null -> palette.darkVibrantSwatch
                                    else -> palette.swatches.first()
                                }
                            }

                            swatch?.let {
                                collapsing_layout.setBackgroundColor(ColorUtils.setAlphaComponent(it.rgb,100))
                                tv_comic_title.setTextColor(it.titleTextColor)
                            }
                        }
                        return bitmap
                    }

                })
    }

    private var isFollow: Boolean = false

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater!!.inflate(R.menu.menu_fragmet_comic_info,menu)
        val menuItem = menu?.findItem(R.id.menu_follow)
        menuItem?.let {
            menuItem.setOnMenuItemClickListener {
                val icon = if(isFollow) R.mipmap.icon_follow_enable else R.mipmap.icon_follow
                menuItem.setIcon(icon)
                isFollow = !isFollow
                true
            }
        }

    }


    companion object {
        fun newInstance(comicId: String, comicAvatarUrl: String) : ComicInfoFragment{
            val fragment = ComicInfoFragment()
            val arguments = Bundle()
            arguments.putString(BUNDLE_KEY_COMIC_ID,comicId)
            arguments.putString(BUNDLE_KEY_COMIC_AVATAR_URL,comicAvatarUrl)
            fragment.arguments = arguments
            return fragment
        }
    }
}