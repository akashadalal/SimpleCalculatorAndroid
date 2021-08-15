package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = (EditText)findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getString(R.string.display).equals(display.getText().toString()))
                {
                    display.setText("");
                }
            }
        });
    }
    private  void updateText(String strToAdd){
        String oldstr=display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftstr=oldstr.substring(0,cursorPos);
        String rightstr=oldstr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString()))
        {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }else {
            display.setText(String.format("%s%s%s", leftstr, strToAdd, rightstr));
            display.setSelection(cursorPos + 1);
        }
    }
    public void zeroBTN(View view){
        updateText("0");

    }
    public void oneBTN(View view){
        updateText("1");

    }
    public void twoBTN(View view){
        updateText("2");

    }
    public void threeBTN(View view){
        updateText("3");

    }
    public void fourBTN(View view){
        updateText("4");

    }
    public void fiveBTN(View view){
        updateText("5");

    }
    public void sixBTN(View view){
        updateText("6");

    }
    public void sevenBTN(View view){
        updateText("7");

    }
    public void eightBTN(View view){

        updateText("8");
    }
    public void nineBTN(View view){
        updateText("9");
    }
    public void equalBTN(View view){
        String exp=display.getText().toString();
        exp=exp.replaceAll("÷" ,"/");
        exp=exp.replaceAll("×","*");

        Expression exp1=new Expression(exp);

        String result = String.valueOf(exp1.calculate());

        display.setText(result);
        display.setSelection(result.length());

    }
    public void exponentBTN(View view){
        updateText("^");
    }
    public void backspaceBTN(View view){
        int cursorPos=display.getSelectionStart();
        int textlen = display.getText().length();

    if(cursorPos != 0 && textlen !=0){
        SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
        selection.replace(cursorPos-1,cursorPos,"");
        display.setText(selection);
        display.setSelection(cursorPos-1);
    }
    }
    public void clearBTN(View view){
        display.setText("");

    }
    public void addBTN(View view){
        updateText("+");

    }
    public void multiplyBTN(View view){
        updateText("*");

    }
    public void divideBTN(View view){
        updateText("/");

    }
    public void subtractBTN(View view){
        updateText("-");

    }
    public void paranthesesBTN(View view){
        int cursorPos=display.getSelectionStart();
        int openpar=0;
        int closepar=0;
        int textlen=display.getText().length();

        for(int i=0;i<cursorPos;i++)
        {
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openpar+=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closepar+=1;
            }
        }
        if(openpar==closepar || display.getText().toString().substring(textlen-1,textlen).equals("(")){
            updateText("(");
        }
        else if(closepar<openpar && !display.getText().toString().substring(textlen-1,textlen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPos+1);

    }
    public void decimalBTN(View view){
        updateText(".");
    }

}