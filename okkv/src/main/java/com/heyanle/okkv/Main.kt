package com.heyanle.okkv

import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.*

/**
 * Created by HeYanLe on 2021/11/24 19:20.
 * https://github.com/heyanLE
 */

fun main(){
    val store = Proxy.newProxyInstance(Thread.currentThread().contextClassLoader, arrayOf(Store::class.java), object: InvocationHandler{
        override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
            println("${method?.name} : ${Arrays.toString(args)}")
            return ""
        }
    }) as Store



    var k by okkv("s", "f")

    Okkv.Builder().store(store).build().default()

    println("k $k")
    k = "f"
}