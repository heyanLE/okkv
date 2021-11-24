package com.heyanle.okkv.sp

import android.content.Context
import android.content.SharedPreferences
import com.heyanle.okkv.Store
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 21:04.
 * https://github.com/heyanLE
 */
class SPStore(
    context: Context,
    spName: String
) : Store {

    private val applicationContext = context.applicationContext

    private val sharedPreferences: SharedPreferences by lazy {
        applicationContext.getSharedPreferences(
            spName,
            Context.MODE_PRIVATE
        )
    }

    override fun init() {
    }

    override fun getString(key: String, def: String): String? {
        return sharedPreferences.getString(key, def)
    }

    override fun getDouble(key: String, def: Double): Double {
        return (sharedPreferences.getString(key, "$def")?:"$def").toDouble()
    }

    override fun getInt(key: String, def: Int): Int {
        return sharedPreferences.getInt(key, def)
    }

    override fun getLong(key: String, def: Long): Long {
        return sharedPreferences.getLong(key, def)
    }

    override fun getFloat(key: String, def: Float): Float {
        return sharedPreferences.getFloat(key, def)
    }

    override fun getBoolean(key: String, def: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, def)
    }

    override fun setString(key: String, value: String): Exception? {
        try {
            sharedPreferences.edit().putString(key, value).apply()
        }catch (e: Exception){
            return e
        }
        return null
    }

    override fun setDouble(key: String, value: Double): Exception? {
        try {
            sharedPreferences.edit().putString(key, value.toString()).apply()
        } catch (e: Exception) {
            return e
        }
        return null
    }

    override fun setInt(key: String, value: Int): Exception? {
        try {
            sharedPreferences.edit().putInt(key, value).apply()
        }catch (e: Exception){
            return e
        }
        return null
    }

    override fun setLong(key: String, value: Long): Exception? {
        try {
            sharedPreferences.edit().putLong(key, value).apply()
        }catch (e: Exception){
            return e
        }
        return null
    }

    override fun setFloat(key: String, value: Float): Exception? {
        try {
            sharedPreferences.edit().putFloat(key, value).apply()
        }catch (e: Exception){
            return e
        }
        return null
    }

    override fun setBoolean(key: String, value: Boolean): Exception? {
        try {
            sharedPreferences.edit().putBoolean(key, value).apply()
        }catch (e: Exception){
            return e
        }
        return null
    }
}