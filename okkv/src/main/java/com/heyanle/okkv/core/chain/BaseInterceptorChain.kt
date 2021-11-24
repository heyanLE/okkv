package com.heyanle.okkv.core.chain

import com.heyanle.okkv.InterceptorChain

/**
 * Created by HeYanLe on 2021/11/24 17:35.
 * https://github.com/heyanLE
 */
abstract class BaseInterceptorChain(
    var next: InterceptorChain? = null
) : InterceptorChain {
    override fun next(): InterceptorChain? {
        return next
    }

    override fun next(interceptorChain: InterceptorChain) {
        next = interceptorChain
    }
}