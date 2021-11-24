package com.heyanle.okkv.mkkv

import android.content.Context
import com.heyanle.okkv.Store
import com.tencent.mmkv.MMKV
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 21:17.
 * https://github.com/heyanLE
 */
class MMKVStore(context: Context) : Store {

    private val applicationContext = context.applicationContext

    override fun init() {
        MMKV.initialize(applicationContext)
    }

    override fun getString(key: String, def: String): String? {
        return MMKV.defaultMMKV().decodeString(key, def)
    }

    override fun getDouble(key: String, def: Double): Double {
        return MMKV.defaultMMKV().decodeDouble(key, def)
    }

    override fun getInt(key: String, def: Int): Int {
        return MMKV.defaultMMKV().decodeInt(key, def)
    }

    override fun getLong(key: String, def: Long): Long {
        return MMKV.defaultMMKV().decodeLong(key, def)
    }

    override fun getFloat(key: String, def: Float): Float {
        return MMKV.defaultMMKV().decodeFloat(key, def)
    }

    override fun getBoolean(key: String, def: Boolean): Boolean {
        return MMKV.defaultMMKV().decodeBool(key, def)
    }

    override fun setString(key: String, value: String): Exception? {
        try{
            MMKV.defaultMMKV().encode(key, value)
        }catch (e :Exception){
            return e
        }
        return null
    }

    override fun setDouble(key: String, value: Double): Exception? {
        try{
            MMKV.defaultMMKV().encode(key, value)
        }catch (e :Exception){
            return e
        }
        return null
    }

    override fun setInt(key: String, value: Int): Exception? {
        try{
            MMKV.defaultMMKV().encode(key, value)
        }catch (e :Exception){
            return e
        }
        return null
    }

    override fun setLong(key: String, value: Long): Exception? {
        try{
            MMKV.defaultMMKV().encode(key, value)
        }catch (e :Exception){
            return e
        }
        return null
    }

    override fun setFloat(key: String, value: Float): Exception? {
        try{
            MMKV.defaultMMKV().encode(key, value)
        }catch (e :Exception){
            return e
        }
        return null
    }

    override fun setBoolean(key: String, value: Boolean): Exception? {
        try{
            MMKV.defaultMMKV().encode(key, value)
        }catch (e :Exception){
            return e
        }
        return null
    }
}