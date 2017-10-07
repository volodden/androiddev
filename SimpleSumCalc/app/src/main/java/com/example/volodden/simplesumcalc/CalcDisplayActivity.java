package com.example.volodden.simplesumcalc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CalcDisplayActivity extends AppCompatActivity {

    private int numberInButtons = 0;
    private int numberInTextField = 0;

    private final String NUMBER_BUTTON = "numberInButton";
    private final String NUMBER_FIELD = "numberInField";

    private EditText et;
    private TextView tw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calc_display);

        tw = (TextView) findViewById(R.id.texNumber);
        et = (EditText) findViewById(R.id.textField);

        if( savedInstanceState == null ) {
            numberInButtons = Integer.parseInt(tw.getText().toString());
            Log.i("OnCreate 1", String.valueOf(numberInButtons));

            numberInTextField = Integer.parseInt(et.getText().toString());
            Log.i("OnCreate 1", String.valueOf(numberInTextField));
        } else {
            numberInButtons = savedInstanceState.getInt(NUMBER_BUTTON);
            tw.setText(String.valueOf(numberInButtons));
            Log.i("OnCreate 2", String.valueOf(numberInButtons));

            numberInTextField = savedInstanceState.getInt(NUMBER_FIELD);
            et.setText(String.valueOf(numberInTextField));
            Log.i("OnCreate 2", String.valueOf(numberInTextField));
        }

        et.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = String.valueOf(et.getText());
                if( text.equals("") ) {
                    numberInTextField = 0;
                    Log.i("ChT 1", "0");
                } else {
                    try {
                        numberInTextField = Integer.parseInt(text);
                    }
                    catch (Exception e) {
                        et.setText(String.valueOf(numberInTextField));
                        Log.i("Except", e.toString());
                    }
                    Log.i("ChT 2", String.valueOf(numberInTextField));
                }

                changeResult();
            }
        });

        changeResult();
    }

    protected void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i("SInstButtonNumber", String.valueOf(numberInButtons));
        Log.i("SInstFieldButton", String.valueOf(numberInTextField));

        savedInstanceState.putInt(NUMBER_BUTTON, numberInButtons);
        savedInstanceState.putInt(NUMBER_FIELD, numberInTextField);

        super.onSaveInstanceState(savedInstanceState);
    }

    void onClickOnButton(int value) {

        numberInButtons += value;

        String text = String.valueOf(numberInButtons);

        Log.i("ButtonClick", text);

        tw.setText(text);

        changeResult();
    }

    void changeResult() {
        int res = numberInButtons * numberInTextField;

        Log.i("ChRes", String.valueOf(res));

        TextView twSum = (TextView) findViewById(R.id.textMult);
        twSum.setText(String.valueOf(res));
    }

    void onClickOnMinus(View view) {
        onClickOnButton(-1);
    }

    void onClickOnPlus(View view) {
        onClickOnButton(1);
    }
}
