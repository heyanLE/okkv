package com.heyanle.okkv.core.chain

import com.heyanle.okkv.OkkvValue
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 18:42.
 * https://github.com/heyanLE
 */
class HeadInterceptorChain : BaseInterceptorChain() {
    override fun <T> get(okkvValue: OkkvValue<T>): T? {
        return next()?.get(okkvValue)
    }

    override fun <T> set(okkvValue: OkkvValue<T>, value: T): Exception? {
        return next()?.set(okkvValue, value)
    }
}