package com.heyanle.okkv

/**
 * Created by HeYanLe on 2021/11/24 18:49.
 * https://github.com/heyanLE
 */
interface Converter {

    fun <T> convertFrom (string: String, okkvValue: OkkvValue<T>) : T?

    fun <T> convertTo (obj: T, okkvValue: OkkvValue<T>) : String?

}