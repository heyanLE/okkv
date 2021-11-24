package com.heyanle.okkv.core.chain

import com.heyanle.okkv.InterceptorChain
import com.heyanle.okkv.OkkvValue
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 17:33.
 * https://github.com/heyanLE
 */
abstract class BaseCatchingChain(
) : BaseInterceptorChain() {

    abstract fun onCatching(exception: Exception)


    override fun <T> get(okkvValue: OkkvValue<T>): T? {
       return try {
            next()?.get(okkvValue)
        }catch (e : Exception){
            onCatching(e)
            null
        }
    }

    override fun <T> set(okkvValue: OkkvValue<T>, value: T): Exception? {
        try {
            next()?.set(okkvValue, value)
        }catch (e : Exception){
            onCatching(e)
        }
        return null
    }
}