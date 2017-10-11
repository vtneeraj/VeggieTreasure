package com.veggietreasures.veggietreasure;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    private InterstitialAd mInterstitialAd;

    WebView webView;

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();

                    }
                });
            }
            webView.goBack();
        }else{
            if (mInterstitialAd.isLoaded()) {
                mInterstitialAd.show();
                mInterstitialAd.setAdListener(new AdListener() {
                    @Override
                    public void onAdClosed() {
                        super.onAdClosed();

                    }
                });
            }
            super.onBackPressed();
        }



    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView=(WebView) findViewById(R.id.webView);



        WebSettings webSettings=webView.getSettings();

        webSettings.setJavaScriptEnabled(true);

        //For WebView site performance
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        webSettings.setSavePassword(true);
        webSettings.setSaveFormData(true);
        webSettings.setEnableSmoothTransition(true);
        //For WebView site performance

        webView.setWebViewClient(new com.veggietreasures.veggietreasure.VtWebClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                //hide loading image
                findViewById(R.id.progressBar1).setVisibility(View.GONE);
                //show webview
                findViewById(R.id.webView).setVisibility(View.VISIBLE);
            }}
        );

        webView.loadUrl("http://www.veggietreasures.com");



        //Ad units - Banner
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Ad units - Interstitial
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("");

        // Prepare the Interstitial Ad
        mInterstitialAd = new InterstitialAd(MainActivity.this);
        // Insert the Ad Unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.vt_InterstitialAdunit));

        mInterstitialAd.loadAd(adRequest);

        // Prepare an Interstitial Ad Listener
        /*mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                // Call displayInterstitial() function
                displayInterstitial();
            }
        });*/

    }
    public void displayInterstitial() {
    // If Ads are loaded, show Interstitial else show nothing.
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }







}
