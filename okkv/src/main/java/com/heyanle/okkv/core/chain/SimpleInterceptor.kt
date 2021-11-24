package com.heyanle.okkv.core.chain

import com.heyanle.okkv.OkkvValue
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 18:45.
 * https://github.com/heyanLE
 */
abstract class SimpleInterceptor : BaseInterceptorChain() {

    abstract fun <T> onGet(okkvValue: OkkvValue<T>)

    abstract fun <T> onSet(okkvValue: OkkvValue<T>, value: T)

    abstract fun <T> onGetAfter(okkvValue: OkkvValue<T>, res: T)

    abstract fun <T> onSetAfter(okkvValue: OkkvValue<T>, value: T, res: Exception?)

    override fun <T> get(okkvValue: OkkvValue<T>): T? {
        onGet(okkvValue)
        return next()?.get(okkvValue)
    }

    override fun <T> set(okkvValue: OkkvValue<T>, value: T): Exception? {
        onSet(okkvValue, value)
        return next()?.set(okkvValue, value)
    }
}