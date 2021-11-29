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
    private val converter: List<Converter>
) : Okkv {

    override fun init() : Okkv{
        store.init()
        return this
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
        converter.forEach {
            runCatching {
                it.convertTo(obj, okkvValue)?.let {
                    return it
                }
            }

        }
        return null
    }

    override fun <T> convertFrom(string: String, okkvValue: OkkvValue<T>): T? {
        converter.forEach {
            runCatching {
                it.convertFrom(string, okkvValue)?.let {
                    return it as T
                }
            }
        }
        return null
    }
}