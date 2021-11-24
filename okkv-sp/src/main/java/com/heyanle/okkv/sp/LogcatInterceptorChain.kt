package com.heyanle.okkv.sp

import android.util.Log
import com.heyanle.okkv.OkkvValue
import com.heyanle.okkv.core.chain.SimpleInterceptor
import java.lang.Exception

/**
 * Created by HeYanLe on 2021/11/24 21:11.
 * https://github.com/heyanLE
 */
class LogcatInterceptorChain: SimpleInterceptor() {

    override fun <T> onGet(okkvValue: OkkvValue<T>) {
        Log.v("OKKV", "get ${okkvValue.get()} ==>")
    }

    override fun <T> onSet(okkvValue: OkkvValue<T>, value: T) {
        Log.v("OKKV", "set ${okkvValue.get()} <== $value")
    }

    override fun <T> onGetAfter(okkvValue: OkkvValue<T>, res: T) {
        Log.v("OKKV", "get ${okkvValue.get()} ==> $res")
    }

    override fun <T> onSetAfter(okkvValue: OkkvValue<T>, value: T, res: Exception?) {
        Log.v("OKKV", "set ${okkvValue.get()} <== $value ex: $res")
    }

}