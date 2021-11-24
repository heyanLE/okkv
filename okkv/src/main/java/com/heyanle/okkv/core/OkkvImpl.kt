package com.heyanle.okkv.core

import com.heyanle.okkv.*
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 16:53.
 * https://github.com/heyanLE
 */
class OkkvImpl(
    private val interceptorChain: InterceptorChain,
    private val store: Store,
    private val converter: Converter
) : Okkv {

    override fun init() {
        store.init()
    }

    override fun <T> getValue(key: String, defValue: T): OkkvValue<T> {
        return OkkvValueImpl(key, this, defValue)
    }

    override fun <T> getValue(value: OkkvValue<T>): T {
        return interceptorChain.get(value) ?: value.defValue()
    }

    override fun <T> setValue(value: OkkvValue<T>, v: T) : Exception?{
        return interceptorChain.set(value, v)
    }

    override fun <T> convertTo(obj: T, okkvValue: OkkvValue<T>): String? {
        return converter.convertTo(obj, okkvValue)
    }

    override fun <T> convertFrom(string: String, okkvValue: OkkvValue<T>): T? {
        return converter.convertFrom(string, okkvValue)
    }
}