package com.heyanle.okkv

import com.heyanle.okkv.core.chain.BaseInterceptorChain
import java.lang.Exception

/**
 * 拦截链
 * Created by HeYanLe on 2021/11/24 17:03.
 * https://github.com/heyanLE
 */
interface InterceptorChain {

    fun next(interceptorChain: InterceptorChain)

    fun next(): InterceptorChain?

    fun <T> get(okkvValue: OkkvValue<T>) : T?

    fun <T> set(okkvValue: OkkvValue<T>, value: T) : Exception?

}