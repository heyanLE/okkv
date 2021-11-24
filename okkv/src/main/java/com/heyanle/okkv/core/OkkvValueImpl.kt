package com.heyanle.okkv.core

import com.heyanle.okkv.Okkv
import com.heyanle.okkv.OkkvValue
import kotlin.reflect.KProperty

/**
 * Created by HeYanLe on 2021/11/24 17:10.
 * https://github.com/heyanLE
 */
class OkkvValueImpl <T> (
    private var key: String,
    private var okkv: Okkv,
    private var def: T
) : OkkvValue<T> {


    override fun key(): String {
        return key
    }

    override fun defValue(): T {
        return def
    }

    override fun get(): T {
        return okkv.getValue(this)
    }

    override fun set(value: T) {
        okkv.setValue(this, value)?.let {
            throw it
        }
    }

    override fun okkv(): Okkv {
        return okkv
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T{
        return get()
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T){
        set(value)
    }


}