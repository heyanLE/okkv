package com.heyanle.okkv.core.chain

import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 18:58.
 * https://github.com/heyanLE
 */
class SimpleCatchingChain : BaseCatchingChain() {
    override fun onCatching(exception: Exception) {
        exception.printStackTrace()
    }
}