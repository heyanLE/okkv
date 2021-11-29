package com.heyanle.okkv

import java.lang.NullPointerException
import kotlin.reflect.KProperty

/**
 * Created by HeYanLe on 2021/11/24 20:38.
 * https://github.com/heyanLE
 */
class OkkvValueLazy<T>(
    private val key: String,
    private val def: T,
    private val okkvFinder: ()->Okkv = {
                                       DefaultOkkv.okkv?:throw NullPointerException("Please init first !")
    },
) : OkkvValue<T>{

    private val proxy : OkkvValue<T> by lazy {
        okkvFinder().getValue(key, def)
    }

    override fun okkv(): Okkv {
        return proxy.okkv()
    }

    override fun key(): String {
        return proxy.key()
    }

    override fun defValue(): T {
        return proxy.defValue()
    }

    override fun get(): T {
        return proxy.get()
    }

    override fun set(value: T) {
        proxy.set(value)
    }

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return proxy.getValue(thisRef, property)
    }

    override operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        proxy.setValue(thisRef, property, value)
    }
}