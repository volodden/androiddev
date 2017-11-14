package com.example.volodden.myphoneapplication;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

public class MainActivity extends FragmentActivity implements AFragment.OnSelectedButtonListener,
        C1Fragment.OnSelectedButtonListener, C2Fragment.OnSelectedButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
            Display display = getWindowManager().getDefaultDisplay();
            DisplayMetrics metricsB = new DisplayMetrics();
            display.getMetrics(metricsB);

            View fragmentA = findViewById(R.id.fragmentA);

            //Log.i("PORIENT", "A w: " + String.valueOf(fragmentA.getWidth()));

            fragmentA.getLayoutParams().width = metricsB.widthPixels;

            //Log.i("PORIENT", "A w: " + String.valueOf(metricsB.widthPixels));
            //Log.i("PORIENT", "A w: " + String.valueOf(fragmentA.getWidth()));

            View fragmentsBAndC = findViewById(R.id.fragments_B_and_C);

            fragmentsBAndC.getLayoutParams().width = metricsB.widthPixels;

            //Log.i("PORIENT", "BC w: " + String.valueOf(metricsB.widthPixels));
        }
    }

    @Override
    public void onButtonSelected(int buttonId) {

        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();

        if( buttonId == R.id.button_to_c1 ) {
            C1Fragment fragmentC1 = new C1Fragment();
            transaction.replace(R.id.containerForC, fragmentC1, "fragmentC1");
        } else {
            C2Fragment fragmentC2 = new C2Fragment();
            transaction.replace(R.id.containerForC, fragmentC2, "fragmentC2");
        }

        transaction.commitAllowingStateLoss();

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
            HorizontalScrollView sv = (HorizontalScrollView) findViewById(R.id.scrollView);
            sv.scrollTo(sv.getMaxScrollAmount()*2, 0);
        }
    }

    @Override
    public String getPhoneNumber() {

        FragmentManager manager = getSupportFragmentManager();

        BFragment fragmentB = (BFragment) manager.findFragmentById(R.id.fragmentB);

        if( fragmentB == null ) {
            return null;
        }

        EditText et = (EditText) fragmentB.getView().findViewById(R.id.editTextPloneNumber);
        return et.getText().toString();
        //Toast.makeText(this, "Вы хотите позвонить на номер " + uri, Toast.LENGTH_SHORT).show();
    }
}
