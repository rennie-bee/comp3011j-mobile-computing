package com.example.eldercare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

/*
* In weather activity, I reference a weather api which can create a web-view on the layout.
* The weather api is supported by HeFeng Weather and the URL is "https://widget.qweather.com/create-mobile-page"
* To apply the weather api in a decent display, I reference some other codes provided by
*
* https://blog.csdn.net/weixin_47529373/article/details/106584430?ops_request_misc=&request_id
* =&biz_id=102&utm_term=%E5%AE%89%E5%8D%93%E5%A4%A9%E6%B0%94&utm_medium=distribute.pc_search_result.
* none-task-blog-2~all~sobaiduweb~default-3-106584430.first_rank_v2_pc_rank_v29&spm=1018.2226.3001.4187
*
* I study his codes to apply the api on the layout xml file to display weather conditions.
* All in all, the WeatherActivity.java and activity_weather.xml are provided.
* */

public class WeatherActivity extends AppCompatActivity {
    WebView webView;
    ConstraintLayout constraintLayout;
    ConstraintLayout constraintLayout1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        webView=findViewById(R.id.webView);
        // We all know that Android can’t go back with WebView, and can only set the click event to go back.
        // I also added a forward button here by the way.
        constraintLayout=findViewById(R.id.constraintLayout5);// back
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.goBack();
            }
        });
        constraintLayout1=findViewById(R.id.constraintLayout4);
        constraintLayout1.setOnClickListener(new View.OnClickListener() {// forward
            @Override
            public void onClick(View v) {
                webView.goForward();
            }
        });
        webView.loadUrl("https://widget-page.qweather.net/h5/index.html?md=0123456&bg=1&lc=auto&key=22f095dfd2cf4d60956865fe2872f286&v=_1637290556396&lg=en");
        //Prevent WebView from calling the browser that comes with the system
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(false);
        webSettings.setSupportZoom(false);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webView.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view,String url){
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view,url);
            }
        });
    }
}
