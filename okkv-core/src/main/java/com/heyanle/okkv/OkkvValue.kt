package com.heyanle.okkv

import kotlin.reflect.KProperty

/**
 * Created by HeYanLe on 2021/11/24 16:36.
 * https://github.com/heyanLE
 */
interface OkkvValue<T> {

    fun okkv(): Okkv

    fun key(): String

    fun defValue(): T

    fun get(): T

    fun set(value: T)

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T)
}