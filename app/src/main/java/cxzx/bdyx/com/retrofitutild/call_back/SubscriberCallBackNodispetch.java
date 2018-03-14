package cxzx.bdyx.com.retrofitutild.call_back;

import cxzx.bdyx.com.retrofitutild.api_test.Test;
import rx.Subscriber;

/**
 * Created by QunCheung on 2018/3/14.
 */

/**
 * 自判定失败结果
 * @param <T>
 */
public abstract class SubscriberCallBackNodispetch<T> extends Subscriber<Test<T>>{
    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        onFailed("-500",e.toString());
    }

    @Override
    public void onNext(Test<T> tTest) {
        onSuccess(tTest);
        if (!tTest.getRspCode().equals("0")){
            onFailed(tTest.getRspCode(),tTest.getRspDesc());
        }
    }

    abstract void onSuccess(Test<T> tTest);
    abstract void onFailed(String rspCode, String rspDesc);
}
