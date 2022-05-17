package com.heyanle.okkv

import java.lang.Exception
import java.util.concurrent.ConcurrentHashMap

/**
 * Created by HeYanLe on 2022/5/17 20:00.
 * https://github.com/heyanLE
 */
class MemoryStore : Store{

    private val map = ConcurrentHashMap<String, Any>()

    override fun init() {

    }

    override fun getString(key: String, def: String): String? {
        return kotlin.runCatching {
            map[key] as String?
        }.getOrElse {
            def
        }
    }

    override fun getDouble(key: String, def: Double): Double? {
        return kotlin.runCatching {
            map[key] as Double?
        }.getOrElse {
            def
        }
    }

    override fun getInt(key: String, def: Int): Int? {
        return kotlin.runCatching {
            map[key] as Int?
        }.getOrElse {
            def
        }
    }

    override fun getLong(key: String, def: Long): Long? {
        return kotlin.runCatching {
            map[key] as Long?
        }.getOrElse {
            def
        }
    }

    override fun getFloat(key: String, def: Float): Float? {
        return kotlin.runCatching {
            map[key] as Float?
        }.getOrElse {
            def
        }
    }

    override fun getBoolean(key: String, def: Boolean): Boolean? {
        return kotlin.runCatching {
            map[key] as Boolean?
        }.getOrElse {
            def
        }
    }

    override fun setString(key: String, value: String): Exception? {
        return kotlin.runCatching {
            map[key] = value
        }.exceptionOrNull() as Exception
    }

    override fun setDouble(key: String, value: Double): Exception? {
        return kotlin.runCatching {
            map[key] = value
        }.exceptionOrNull() as Exception
    }

    override fun setInt(key: String, value: Int): Exception? {
        return kotlin.runCatching {
            map[key] = value
        }.exceptionOrNull() as Exception
    }

    override fun setLong(key: String, value: Long): Exception? {
        return kotlin.runCatching {
            map[key] = value
        }.exceptionOrNull() as Exception
    }

    override fun setFloat(key: String, value: Float): Exception? {
        return kotlin.runCatching {
            map[key] = value
        }.exceptionOrNull() as Exception
    }

    override fun setBoolean(key: String, value: Boolean): Exception? {
        return kotlin.runCatching {
            map[key] = value
        }.exceptionOrNull() as Exception
    }
}