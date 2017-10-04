package com.example.volodden.counter;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class CounterActivity extends AppCompatActivity {

    private final String textOff = "stop";
    private final String textOn = "start";
    private int time = 0;
    private final int maxTime = 999;
    private CounterOnDisplay timer;

    static final String STATE_TIME = "time";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.i("Cntr", "Creater Counter");

        setContentView(R.layout.activity_counter);

        if( savedInstanceState != null ) {
            time = savedInstanceState.getInt(STATE_TIME);
            Button buttonStSp = (Button) findViewById(R.id.buttonStSp);
            if( time == 0 ) {
                buttonStSp.setText(textOn);
            } else {
                buttonStSp.setText(textOff);
                timer = new CounterOnDisplay((maxTime - time)*1000, 1000);
                timer.start();
            }
        } else {
            Button buttonStSp = (Button) findViewById(R.id.buttonStSp);
            buttonStSp.setText(textOn);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(STATE_TIME, time);

        timer.cancel();

        super.onSaveInstanceState(savedInstanceState);
    }

    void onClickOnButton( View view ) {
        Button buttonStSp = (Button) findViewById(R.id.buttonStSp);
        String text = (String) buttonStSp.getText();
        if( text.equals(textOn) ) {
            buttonStSp.setText(textOff);
            time = 0;
            timer = new CounterOnDisplay(maxTime*1000, 1000);
            timer.start();
        } else {
            buttonStSp.setText(textOn);
            TextView textCounter = (TextView) findViewById(R.id.textCounter);
            textCounter.setText(R.string.textCounter);
            timer.cancel();
        }
    }

    class CounterOnDisplay extends CountDownTimer {

        private TextGetter textGetter = new TextGetter();

        public CounterOnDisplay(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);

            TextView textCounter = (TextView) findViewById(R.id.textCounter);
            textCounter.setText(textGetter.input(time));
        }

        @Override
        public void onTick(long l) {
            time++;
            TextView textCounter = (TextView) findViewById(R.id.textCounter);
            textCounter.setText(textGetter.input(time));
        }

        @Override
        public void onFinish() {
            Button buttonStSp = (Button) findViewById(R.id.buttonStSp);
            buttonStSp.setText(textOff);

            TextView textCounter = (TextView) findViewById(R.id.textCounter);
            textCounter.setText(R.string.textCounter);
        }
    }

    class TextGetter {

        private Map<Integer, String> words = new HashMap<Integer, String>();

        public TextGetter() {
            words.put(1, "один");
            words.put(2, "два");
            words.put(3, "три");
            words.put(4, "четыре");
            words.put(5, "пять");
            words.put(6, "шесть");
            words.put(7, "семь");
            words.put(8, "восемь");
            words.put(9, "девять");
            words.put(10, "десять");
            words.put(11, "одиннадцать");
            words.put(12, "двенадцать");
            words.put(13, "тринадцать");
            words.put(14, "четырнадцать");
            words.put(15, "пятнадцать");
            words.put(16, "шестнадцать");
            words.put(17, "семнадцать");
            words.put(18, "восемнадцать");
            words.put(19, "девятнадцать");
            words.put(20, "двадцать");
            words.put(30, "тридцать");
            words.put(40, "сорок");
            words.put(50, "пятьдесят");
            words.put(60, "шестьдесят");
            words.put(70, "семьдесят");
            words.put(80, "весемьдесят");
            words.put(90, "девяносто");
            words.put(100, "сто");
            words.put(200, "двести");
            words.put(300, "триста");
            words.put(400, "четыреста");
            words.put(500, "пятьсот");
            words.put(600, "шестьсот");
            words.put(700, "семьсот");
            words.put(800, "восемьсот");
            words.put(900, "девятьсот");
            words.put(1000, "тысяча");
        }

        String input(int number) {
            if( ( number < 0 ) || ( number > 1000 ) ) {
                return "Error";
            }

            Boolean flag = false;

            //number++;

            StringBuilder stringBuilder = new StringBuilder(" ");

            if( number == 1000 ) {
                return words.get(number);
            }

            int rest = number / 100;
            if( rest != 0 ) {
                rest *= 100;
                stringBuilder.append(words.get(rest));
                flag = true;
            }

            rest = number / 10 % 10;
            if( rest == 0 ) {
                rest = number % 10;
                if( rest != 0 ) {
                    if( flag ) {
                        stringBuilder.append(" ");
                    }
                    stringBuilder.append(words.get(rest));
                    flag = true;
                }
            } else {
                if( rest == 1 ) {
                    rest = number % 100;
                    if( flag ) {
                        stringBuilder.append(" ");
                    }
                    stringBuilder.append(words.get(rest));
                    flag = true;
                } else {
                    rest *= 10;
                    if( flag ) {
                        stringBuilder.append(" ");
                    }
                    stringBuilder.append(words.get(rest));
                    flag = true;
                    rest = number % 10;
                    if( rest != 0 ) {
                        if( flag ) {
                            stringBuilder.append(" ");
                        }
                        stringBuilder.append(words.get(rest));
                        flag = true;
                    }
                }
            }

            return stringBuilder.toString();
        }
    }
}
