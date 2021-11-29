package com.heyanle.okkv.core.chain

import com.heyanle.okkv.OkkvValue
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 18:45.
 * https://github.com/heyanLE
 */
abstract class SimpleInterceptor : BaseInterceptorChain() {

    abstract fun <T> onGet(okkvValue: OkkvValue<T>)

    abstract fun <T> onSet(okkvValue: OkkvValue<T>, value: T?)

    abstract fun <T> onGetAfter(okkvValue: OkkvValue<T>, res: T?)

    abstract fun <T> onSetAfter(okkvValue: OkkvValue<T>, value: T, res: Exception?)

    override fun <T> get(okkvValue: OkkvValue<T>): T? {
        onGet(okkvValue)
        val res =  next()?.get(okkvValue)
        onGetAfter(okkvValue, res)
        return res
    }

    override fun <T> set(okkvValue: OkkvValue<T>, value: T): Exception? {
        onSet(okkvValue, value)
        val res =  next()?.set(okkvValue, value)
        onSetAfter(okkvValue,value, res)
        return res
    }
}