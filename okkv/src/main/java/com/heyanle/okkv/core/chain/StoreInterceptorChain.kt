package com.heyanle.okkv.core.chain

import com.heyanle.okkv.OkkvValue
import com.heyanle.okkv.Store
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 17:47.
 * https://github.com/heyanLE
 */
class StoreInterceptorChain(
    private val store: Store
) : BaseInterceptorChain(){
    override fun <T> get(okkvValue: OkkvValue<T>): T? {
        return when(okkvValue.defValue()){
            is String -> {
                store.getString(okkvValue.key(), okkvValue.defValue() as String)
            }
            is Long -> {
                store.getLong(okkvValue.key(), okkvValue.defValue() as Long)
            }
            is Double -> {
                store.getDouble(okkvValue.key(), okkvValue.defValue() as Double)
            }
            is Float -> {
                store.getFloat(okkvValue.key(), okkvValue.defValue() as Float)
            }
            is Int -> {
                store.getInt(okkvValue.key(), okkvValue.defValue() as Int)
            }
            is Boolean -> {
                store.getBoolean(okkvValue.key(), okkvValue.defValue() as Boolean)
            }
            else -> {
                null
            }
        } as T?
    }

    override fun <T> set(okkvValue: OkkvValue<T>, value: T): Exception? {
        try{
            when(okkvValue.defValue()){
                is String -> {
                    store.setString(okkvValue.key(), value as String)
                }
                is Long -> {
                    store.setLong(okkvValue.key(), value as Long)
                }
                is Double -> {
                    store.setDouble(okkvValue.key(), value as Double)
                }
                is Float -> {
                    store.setFloat(okkvValue.key(), value as Float)
                }
                is Int -> {
                    store.setInt(okkvValue.key(), value as Int)
                }
                is Boolean -> {
                    store.setBoolean(okkvValue.key(), value as Boolean)
                }
            }
            return null
        }catch (e : Exception){
            return e
        }
    }
}