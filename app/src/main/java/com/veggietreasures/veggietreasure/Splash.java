package com.veggietreasures.veggietreasure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by krisna on 08/10/17.
 */

public class Splash extends Activity {


    private static int SPLASH_TIME_OUT=10000;

    private GifImageView gifImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        gifImageView=(GifImageView) findViewById(R.id.gifImageView);
        //Set GifImage Resource
        try{
            InputStream inputStream=getAssets().open("vtgif.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        //wait for SPLASH_TIME_OUT and start main Activity
        Thread t =new Thread(){
            public void run(){
                try{
                    sleep(SPLASH_TIME_OUT);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    Intent i =new Intent(Splash.this,MainActivity.class);
                    startActivity(i);
                }
            }
        };
        t.start();
    }
    @Override
    public void onPause(){
        super.onPause();
        finish();
    }
}
