package com.heyanle.okkv

/**
 * Created by HeYanLe on 2021/11/24 18:49.
 * https://github.com/heyanLE
 */
interface Converter {

    fun convertFrom (string: String, okkvValue: OkkvValue<*>) : Any?

    fun convertTo (obj: Any?, okkvValue: OkkvValue<*>) : String?

}