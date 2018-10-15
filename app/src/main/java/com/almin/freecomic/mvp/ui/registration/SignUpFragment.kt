package com.almin.freecomic.mvp.ui.registration

import android.graphics.drawable.Drawable
import android.view.View
import com.almin.freecomic.R
import com.almin.freecomic.imageloader.GlideApp
import com.almin.freecomic.mvp.contract.AbstractContract
import com.almin.freecomic.mvp.ui.base.AbstractFcFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_sign_up.*

/**
 * Created by Almin on 2018/6/26.
 */
class SignUpFragment : AbstractFcFragment() {

    init {
        layoutResourceId = R.layout.fragment_sign_up
        pageTitle = "注册"
    }

    override val presenter: AbstractContract.Presenter<Unit> = AbstractContract.Presenter.EMPTY

    companion object {
        fun instance(): SignUpFragment{
            val signUpFragment = SignUpFragment()
            return signUpFragment.apply { arguments = null }
        }
    }

    override fun initView(view: View) {
    }

    override fun initData() {

        val requestOptions = RequestOptions()
                .placeholder(R.drawable.abc_ic_clear_material)//加载前的占位图
                .error(R.drawable.abc_ab_share_pack_mtrl_alpha)//加载失败后占位图
                .override(400,600)//指定宽高大小
//                .fitCenter()
//                .centerCrop()
//                .circleCrop()
//                .skipMemoryCache(true)	//跳过内存缓存
//                .diskCacheStrategy(DiskCacheStrategy.ALL)	//缓存原图以及被修改后所有不同尺寸的最终图像
//                .diskCacheStrategy(DiskCacheStrategy.NONE)	//不进行缓存操作
//                .diskCacheStrategy(DiskCacheStrategy.DATA)	//只缓存原来分辨率的图片
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)	//只缓存最终的图片

        var builderFactory = GlideBuilder().setBitmapPool(null)

        GlideApp.init(activity!!.applicationContext,builderFactory)
        println("1212121212121")
        Glide.with(this)
                .load("http://n.sinaimg.cn/tech/transform/525/w225h300/20180830/cNtG-hikcahf7723370.gif")
                .apply(requestOptions)
                .into(object : DrawableImageViewTarget(iv_gif) {
                    override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                        if(resource is GifDrawable){
                            resource.setLoopCount(1)
                        }
                        super.onResourceReady(resource, transition)
                    }
                })
    }

}