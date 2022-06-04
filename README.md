### OKKV —— 一个好用的数据持久化工具类


# 已过时！
# 新版点击[此处](https://github.com/heyanLE/okkv2)

[![](https://www.jitpack.io/v/heyanLE/okkv.svg)](https://www.jitpack.io/#heyanLE/okkv)

### Latest

[![](https://www.jitpack.io/v/heyanLE/okkv.svg)](https://www.jitpack.io/#heyanLE/okkv)

* 1.1.8 2022/05/17  
  更新 MemoryStore 并作为默认实现（直接存放内存）  
  将 CacheInterceptorChain 的数据结构从 SynchronizedMap 更新为 ConcurrentHashMap （效率较高）

### Load

```groovy
repositories {
    // Other
    maven {url 'https://jitpack.io'}
}
```

```groovy
implementation 'com.github.heyanLE.okkv:okkv-core:1.1.8' // 核心代码
implementation 'com.github.heyanLE.okkv:okkv-sp:1.1.8' // 使用 SharedPreference 储存
implementation 'com.github.heyanLE.okkv:okkv-mmkv:1.1.8' // 使用 MMKV 储存
```

### Use

首先需要在 Application 中初始化：
支持的基本类型：

- String
- Int
- Long
- Double
- Float
- Boolean
- 自定义实体 （需定义 Convert 序列化方式）

```kotlin
class MyApplication: Application {
    
    override fun onCreate(){
        super.onCreate()

        
        Okkv.Builder()
            .store(MMKVStore(this)) // 使用 MMKV 储存
            //.store(SPStore(this, "my_data"))  使用 SharedPreference 储存
            .build() // 创建 Okkv 对象
            .init() // 初始化
            .default() // 作为默认
    }
}
```

然后可以直接使用委托，或者直接获取对象：

```kotlin
// 委托使用
var data by okkv("key", "defValue")
var data1 by okkv("key1", 0)

data = "13" // 直接储存


// 对象使用
val dataV = okkv("key", "defValue")
val data = dataV.get()
dataV.set("123")
```

当然也可以使用自定义的 Okkv 对象：

```kotlin
val okkv = Okkv.Builder()
    .store(MMKVStore(this)) // 使用 MMKV 储存
    //.store(SPStore(this, "my_data"))  使用 SharedPreference 储存
    .build() // 创建 Okkv 对象
    .init() // 初始化

var data by okkv.getValue("key", "defVal")
val dv = okkv.getValue("key", "defVal")
val d = dv.get()
```

### InterceptorChain

可以在 okkv 中添加拦截：
```kotlin
Okkv.Builder()
    .cache()
    .addInterceptorChain(LogcatInterceptorChain()) // 自带的打印 拦截链
        
        
    .addInterceptorChain(object: SimpleInterceptor(){ // SimpleInterceptor 不会拦截，直接调用对应方法
        override fun <T> onGet(okkvValue: OkkvValue<T>) {
            TODO("Not yet implemented")
        }
    
        override fun <T> onGetAfter(okkvValue: OkkvValue<T>, res: T) {
            TODO("Not yet implemented")
        }
    
        override fun <T> onSet(okkvValue: OkkvValue<T>, value: T) {
            TODO("Not yet implemented")
        }
    
        override fun <T> onSetAfter(okkvValue: OkkvValue<T>, value: T, res: Exception?) {
            TODO("Not yet implemented")
        }
    })
        
        
    .addInterceptorChain(object: BaseInterceptorChain(){ // BaseInterceptorChain 会拦截，需要在其中调用下一个链已进行之后的操作
        override fun <T> get(okkvValue: OkkvValue<T>): T? {
            return next()?.get(okkvValue)
        }

        override fun <T> set(okkvValue: OkkvValue<T>, value: T): Exception? {
            return next()?.set(okkvValue, value)
        }
    })
    .store(MMKVStore(this))
    .build().default()
```

### Covert

当我们需要的类型不是基本类型时，可以通过 Converter 来序列化，在使用时，所有的 Converter 都会被调用，直到第一个不为空的结果出现

```kotlin
Okkv.Builder()
    .store(MMKVStore(this)) // 使用 MMKV 储存
    .addConverter(object: Converter { // 添加 Coverter
        override fun convertFrom(string: String, okkvValue: OkkvValue<*>): Any? {
            // 反序列化
            TODO("Not yet implemented")
        }

        override fun convertTo(obj: Any?, okkvValue: OkkvValue<*>): String? {
            // 序列化
            TODO("Not yet implemented")
        }
    })
    .build() // 创建 Okkv 对象
    .init() // 初始化
    .default() // 作为默认
```

