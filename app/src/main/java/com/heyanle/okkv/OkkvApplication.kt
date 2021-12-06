package com.heyanle.okkv

import android.app.Application
import com.heyanle.okkv.mkkv.MMKVStore

/**
 * Created by HeYanLe on 2021/11/24 23:23.
 * https://github.com/heyanLE
 */
class OkkvApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Okkv.Builder().store(MMKVStore(this)).build().init().default()

        var s : Boolean by okkv("F", false)
        s = true
    }
}