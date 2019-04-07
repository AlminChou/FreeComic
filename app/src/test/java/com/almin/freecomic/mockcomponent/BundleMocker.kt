package com.almin.freecomic.mockcomponent

import android.os.Bundle
import org.mockito.Matchers.anyString
import org.mockito.Mockito.*
import java.util.*

/**
 * Created by Almin on 2018/11/14.
 */
class BundleMocker{
    companion object {
        fun mockBundle(): Bundle {
            val fakeBundle = HashMap<String, String>()
            val bundle = mock<Bundle>(Bundle::class.java)

            doAnswer { invocation ->
                val arguments = invocation.arguments
                val key = arguments[0] as String
                val value = arguments[1] as String
                fakeBundle[key] = value
                null
            }.`when`(bundle).putString(anyString(), anyString())


            `when`<String>(bundle.getString(anyString())).thenAnswer { invocation ->
                val arguments = invocation.arguments
                val key = arguments[0] as String
                fakeBundle[key]
            }
            return bundle
        }
    }
}