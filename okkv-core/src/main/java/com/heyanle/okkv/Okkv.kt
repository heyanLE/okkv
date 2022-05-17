package com.heyanle.okkv

import com.heyanle.okkv.core.OkkvImpl
import com.heyanle.okkv.core.chain.*
import java.lang.Exception
import java.lang.NullPointerException

/**
 * Created by HeYanLe on 2021/11/24 16:33.
 * https://github.com/heyanLE
 */

fun <T> okkv(key: String, def: T): OkkvValue<T>{
    return OkkvValueLazy<T>(key, def)
}




object DefaultOkkv {
    var okkv: Okkv? = null
    fun def(okkv: Okkv){
        this.okkv = okkv
    }
}

interface Okkv {

    class Builder {
        var store: Store? = MemoryStore()
        var converters  = arrayListOf<Converter>()
        var interceptorChains = arrayListOf<InterceptorChain>()
        var cache: Boolean = false
        var catchingChain : BaseCatchingChain = SimpleCatchingChain()

        fun cache(): Builder {
            cache = true
            return this
        }

        fun cache(boolean: Boolean):Builder{
            cache = boolean
            return this
        }

        fun store(store: Store): Builder{
            this.store = store
            return this
        }

        fun addConverter(converter: Converter): Builder {
            this.converters.add(converter)
            return this
        }

        fun addInterceptorChain(interceptorChain: InterceptorChain):Builder{
            this.interceptorChains.add(interceptorChain)
            return this

        }

        fun build(): Okkv{

            val list = arrayListOf<InterceptorChain>()
            list.add(catchingChain)

            if(cache){
                list.add(CacheInterceptorChain())
            }

            list.addAll(interceptorChains)

            val st = store?:throw NullPointerException("store can't be null")

            list.add(CovertInterceptorChain())
            list.add(StoreInterceptorChain(st))

            val head = HeadInterceptorChain()
            var p: InterceptorChain = head
            for(chain in list){
                p.next(chain)
                p = chain
            }
            return OkkvImpl(head, st, converters)
        }
    }

    fun init(): Okkv

    fun <T> getValue(key: String, defValue: T): OkkvValue<T>

    fun <T> getValue(value: OkkvValue<T>): T

    fun <T> setValue(value: OkkvValue<T>, v: T) : Exception?

    fun <T> convertTo(obj : T, okkvValue: OkkvValue<T>) : String?

    fun <T> convertFrom(string: String, okkvValue: OkkvValue<T>) : T?

    fun default(){
        DefaultOkkv.def(this)
    }


}
