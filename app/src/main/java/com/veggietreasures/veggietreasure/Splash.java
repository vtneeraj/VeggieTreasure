package com.veggietreasures.veggietreasure;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
/**
 * Created by krisna on 08/10/17.
 */

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*ActionBar actionBar = getActionBar();
        actionBar.hide();*/

        Thread t =new Thread(){
            public void run(){
                try{
                    sleep(10000);
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
