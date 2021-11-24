package com.heyanle.okkv.core.chain

import com.heyanle.okkv.OkkvValue
import java.lang.Exception
import java.util.*

/**
 * Created by HeYanLe on 2021/11/24 18:57.
 * https://github.com/heyanLE
 */
class CacheInterceptorChain : BaseInterceptorChain() {

    private val hashMap = Collections.synchronizedMap(hashMapOf<String, Any>())

    override fun <T> get(okkvValue: OkkvValue<T>): T? {
        if(hashMap.containsKey(okkvValue.key())){
            try {
                return hashMap[okkvValue.key()] as T
            }catch (e : Exception){
                e.printStackTrace()
            }
        }
        val o = next?.get(okkvValue)
        o?.let {
            hashMap[okkvValue.key()] = o
        }
        return o
    }

    override fun <T> set(okkvValue: OkkvValue<T>, value: T): Exception? {
        val res = next?.set(okkvValue, value)
        if(res == null){
            hashMap[okkvValue.key()] = value
        }
        return res
    }
}