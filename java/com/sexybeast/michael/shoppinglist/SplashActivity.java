package com.sexybeast.michael.shoppinglist;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2550;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);       //first parameter for the activity u are switching to, second parameter for current activity(splash screen in this case)
            }
        }, SPLASH_DISPLAY_LENGTH);

        ImageView splashLogo = (ImageView) findViewById(R.id.splashImage);
        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.rotate_right);
        splashLogo.startAnimation(myanim);

        TextView textLogo = (TextView) findViewById(R.id.splashText);
        Animation myanim2 = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        textLogo.startAnimation(myanim2);

    }
}
