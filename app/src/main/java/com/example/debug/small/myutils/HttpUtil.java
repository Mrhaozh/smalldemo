package com.example.debug.small.myutils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import org.greenrobot.eventbus.EventBus;
import okhttp3.Call;
import okhttp3.MediaType;

/**
 * Created by lenovo on 2018/3/5.
 */

public class HttpUtil {
    private String httpUrl;

    public HttpUtil(String httpUrl) {
        this.httpUrl = "http://47.94.97.161:808/Goods/" + httpUrl;
    }
    public void get(){
        OkHttpUtils.get().url(httpUrl).tag(this).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
            }
            @Override
            public void onResponse(String response, int id) {
                EventBus.getDefault().post(new Message(response));
            }
        });
    }
    public void post(String json){
        OkHttpUtils.postString()
                   .url(httpUrl)
                   .mediaType(MediaType.parse("application/json;charset=utf-8"))
                   .content(json)
                   .build()
                   .execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                EventBus.getDefault().post(new Message(response));
            }
        });
    }
}
