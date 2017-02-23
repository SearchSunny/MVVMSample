package com.mvvmsample.viewmodel;

import android.content.Context;
import android.preference.PreferenceActivity;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.mvvmsample.model.MainModelBean;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

import static android.R.attr.id;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 *  业务具体处理，包括负责存储、检索、操纵数据等
 */
public class MainViewModel extends ViewModel {

    private static final String TAG = "MainViewModel";


    private MainModelBean mainModelBean;

    public MainViewModel(Context context) {
        super(context);
        showProgress("",true);
        loadData();
    }

    @Override
    public Object showData() {
        return mainModelBean;
    }

    public void setMainModelBean(MainModelBean mainModelBean) {
        this.mainModelBean = mainModelBean;
    }

    public void loadData(){

        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

        asyncHttpClient.get("http://www.weather.com.cn/adat/sk/101010100.html",new JsonHttpResponseHandler(){

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    MainModelBean mainModelBean = new MainModelBean();
                    JSONObject weatherInfo = response.getJSONObject("weatherinfo");
                    Log.d( TAG,weatherInfo.getString("city"));
                    mainModelBean.setCity(weatherInfo.getString("city"));
                    mainModelBean.setWd(weatherInfo.getString("WD"));
                    mainModelBean.setWs(weatherInfo.getString("WS"));
                    mainModelBean.setTime(weatherInfo.getString("time"));
                    setMainModelBean(mainModelBean);

                    dismissProgress();
                }catch (JSONException e){

                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.d( TAG,"statusCode="+statusCode);
                dismissProgress();

            }
        });
    }
}
