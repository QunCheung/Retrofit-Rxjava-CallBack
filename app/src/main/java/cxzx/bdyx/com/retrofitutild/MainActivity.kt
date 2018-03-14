package cxzx.bdyx.com.retrofitutild

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cxzx.bdyx.com.retrofitutild.api_test.ApiTest
import cxzx.bdyx.com.retrofitutild.call_back.SubscriberCallBack
import cxzx.bdyx.com.retrofitutild.retrofit_instance.RetrofitInstance
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    internal var obs: SubscriberCallBack<InfoBean> =
            object : SubscriberCallBack<InfoBean>() {
        override fun onSuccess(info: InfoBean?) {
            tv.setText(info!!.sign)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        RetrofitInstance.request(RetrofitInstance
                .createApiService("http://192.168.43.40:8080",
                        ApiTest::class.java)
                .baidu(),
                obs)
    }

    override fun onDestroy() {
        super.onDestroy()
        obs.unsubscribe()
    }
}
