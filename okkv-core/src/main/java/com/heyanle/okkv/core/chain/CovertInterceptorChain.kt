package com.heyanle.okkv.core.chain

import com.heyanle.okkv.InterceptorChain
import com.heyanle.okkv.OkkvValue
import com.heyanle.okkv.core.OkkvValueImpl
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 18:32.
 * https://github.com/heyanLE
 */
class CovertInterceptorChain(
) : BaseInterceptorChain() {

    override fun <T> get(okkvValue: OkkvValue<T>): T? {
        val s = okkvValue.defValue()
        when(okkvValue.defValue()){

            is String, is Long, is Double, is Int, is Boolean, is Float -> {
                return next()?.get(okkvValue)
            }
            else -> {
                val v = OkkvValueImpl(okkvValue.key(), okkvValue.okkv(), "")
                val s = next()?.get(v) ?: return null
                return okkvValue.okkv().convertFrom(s, okkvValue)
            }
        }
    }

    override fun <T> set(okkvValue: OkkvValue<T>, value: T): Exception? {
        val s = okkvValue.defValue()
        when(okkvValue.defValue()){
            is String, is Long, is Double, is Int, is Boolean, is Float -> {
                return next()?.set(okkvValue, value)
            }
            else -> {
                val res = try {
                    okkvValue.okkv().convertTo(value, okkvValue)
                }catch (e : Exception){
                    return e
                } ?: return Exception("cover to error !")
                val v = OkkvValueImpl(okkvValue.key(), okkvValue.okkv(), "")
                next()?.set(v, res)
                return null
            }
        }




    }
}