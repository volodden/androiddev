package com.example.volodden.myphoneapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.EditText;

public class MainActivity extends FragmentActivity implements AFragment.OnSelectedButtonListener,
        C1Fragment.OnSelectedButtonListener, C2Fragment.OnSelectedButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
