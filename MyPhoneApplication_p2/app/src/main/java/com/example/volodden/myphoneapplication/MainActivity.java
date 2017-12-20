package com.example.volodden.myphoneapplication;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;

public class MainActivity extends FragmentActivity implements AFragment.OnSelectedButtonListener,
        C1Fragment.OnSelectedButtonListener, C2Fragment.OnSelectedButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.vpager);
            if (viewPager != null) {
                viewPager.setAdapter(new SimplePagerAdapter(this));
            }
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
            //сделать сдвиг на второй экран
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

    public enum ResourcesModel {

        FIRST_SCREEN(R.layout.fragment_a),
        SECOND_SCREEN(R.layout.fragment_b_c);

        private int mLayoutResourceId;

        ResourcesModel(int layoutResId) {
            mLayoutResourceId = layoutResId;
        }

        public int getLayoutResourceId() {
            return mLayoutResourceId;
        }
    }

    class SimplePagerAdapter extends PagerAdapter {

        private Context mContext;

        public SimplePagerAdapter(Context context) {
            mContext = context;
        }

        @Override
        public Object instantiateItem(ViewGroup viewGroup, int position) {
            ResourcesModel resources = ResourcesModel.values()[position];
            LayoutInflater inflater = LayoutInflater.from(mContext);

            ViewGroup layout = (ViewGroup) inflater.inflate(resources.getLayoutResourceId(), viewGroup, false);

            Log.i("CRTpages", "create p");

            viewGroup.addView(layout);

            return layout;
        }

        @Override
        public void destroyItem(ViewGroup viewGroup, int position, Object view) {
            viewGroup.removeView((View) view);
        }

        @Override
        public int getCount() {
            return ResourcesModel.values().length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
