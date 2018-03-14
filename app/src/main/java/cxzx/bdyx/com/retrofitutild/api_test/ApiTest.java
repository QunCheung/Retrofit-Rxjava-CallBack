package cxzx.bdyx.com.retrofitutild.api_test;

import cxzx.bdyx.com.retrofitutild.InfoBean;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by QunCheung on 2018/3/13.
 */

public interface ApiTest {
    @GET("/login")
    Observable<Test<InfoBean>> baidu();
}
