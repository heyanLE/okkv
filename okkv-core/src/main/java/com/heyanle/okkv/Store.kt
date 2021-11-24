package com.heyanle.okkv

import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 16:54.
 * https://github.com/heyanLE
 */
interface Store {

    fun init()

    fun getString(key: String, def: String) : String?
    fun getDouble(key: String, def: Double) : Double?
    fun getInt(key: String, def: Int) : Int?
    fun getLong(key: String, def: Long) : Long?
    fun getFloat(key: String, def: Float) : Float?
    fun getBoolean(key: String, def: Boolean) : Boolean?

    fun setString(key: String, value: String): Exception?
    fun setDouble(key: String, value: Double): Exception?
    fun setInt(key: String, value: Int): Exception?
    fun setLong(key: String, value: Long): Exception?
    fun setFloat(key: String, value: Float): Exception?
    fun setBoolean(key: String, value: Boolean): Exception?


}