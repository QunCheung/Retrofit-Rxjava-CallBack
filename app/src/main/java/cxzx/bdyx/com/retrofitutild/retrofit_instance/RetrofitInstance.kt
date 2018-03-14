package cxzx.bdyx.com.retrofitutild.retrofit_instance

import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

/**
 * Created by QunCheung on 2018/3/13.
 */
object RetrofitInstance{
    private lateinit var instance : RetrofitInstance
    private var retrofits: MutableMap<String, Retrofit> = hashMapOf()

    private operator fun invoke(): RetrofitInstance {
        return instance
    }

    /**
     * 获取不同BaseUrl地址下的Retrofit实例
     */
    private fun initRetrofit(baseUrl:String):Retrofit{
        if (retrofits.containsKey(baseUrl)){
            return retrofits.getValue(baseUrl)
        }else{
            var retrofit:Retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl)
                    .build()
            retrofits.put(baseUrl,retrofit)
            return retrofit
        }
    }

    /**
     * 创建服务
     */
    fun <T>createApiService(baseUrl: String,apiService:Class<T> ):T{
        return initRetrofit(baseUrl).create(apiService)
    }

    fun <T> request(observable: Observable<T>, subscriber: Subscriber<T>) {
        observable
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber)
    }

}
