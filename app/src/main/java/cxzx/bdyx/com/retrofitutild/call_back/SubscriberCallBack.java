package cxzx.bdyx.com.retrofitutild.call_back;

import android.util.Log;

import cxzx.bdyx.com.retrofitutild.api_test.Test;
import rx.Subscriber;

/**
 * Created by QunCheung on 2018/3/14.
 */

/**
 * 统一处理网络请求失败结果
 * @param <T>
 */
public abstract class SubscriberCallBack<T> extends Subscriber<Test<T>>{
    @Override
    public void onNext(Test<T> tTest) {
        if (tTest.getRspCode().equals("-100")) {
            onSuccess(tTest.getInfo());
        }
    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        Log.i("http", "onError: "+e.toString());
    }


    public abstract void onSuccess(T info);
}
