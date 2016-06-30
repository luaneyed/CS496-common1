package cs496.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;
import java.text.DecimalFormat;

public class MainActivity extends Activity implements OnClickListener {

    private TextView Display;
    private Boolean Typing = false;
    private Boolean Typing0 = false;
    private Brain Brain;
    private static final String DIGITS = "0123456789.";

    DecimalFormat df = new DecimalFormat("@##########");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Brain = new Brain();
        Display = (TextView) findViewById(R.id.text1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(10);

        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);

        findViewById(R.id.buttonAdd).setOnClickListener(this);
        findViewById(R.id.buttonSubtract).setOnClickListener(this);
        findViewById(R.id.buttonMultiply).setOnClickListener(this);
        findViewById(R.id.buttonDivide).setOnClickListener(this);
        findViewById(R.id.buttonSign).setOnClickListener(this);
        findViewById(R.id.buttonDecimal).setOnClickListener(this);
        findViewById(R.id.buttonEqual).setOnClickListener(this);
        findViewById(R.id.buttonCancel).setOnClickListener(this);
        findViewById(R.id.buttonInverse).setOnClickListener(this);
        findViewById(R.id.buttonBack).setOnClickListener(this);
        findViewById(R.id.buttonSquareRoot).setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        String buttonPressed = ((Button) v).getText().toString();

        if(DIGITS.contains(buttonPressed) || buttonPressed.equals("BACK")){
            if(Typing){
                if(buttonPressed.equals(".") && Display.getText().toString().contains(".")){
                }else if(buttonPressed.equals("BACK")) {
                    String a = Display.getText().toString();
                    int len = a.length();
                    if (len == 1) {
                        Display.setText(df.format(0));
                        Typing = false;
                        Typing0 = true;
                    }else {
                        a = a.substring(0,len-1);
                        Display.setText(df.format(Double.parseDouble(a)));}
                }else{
                    String a = Display.getText().toString();
                    if(a.length()>1){
                        if (a.charAt(0)!='0' || a.charAt(1)=='.') {
                            Display.append(buttonPressed);
                        }
                    }else{
                        if(a.charAt(0)!='0') {
                            Display.append(buttonPressed);
                        }else{
                            Display.setText(df.format(Double.parseDouble(buttonPressed)));
                        }
                    }

                }
            }else{
                if(buttonPressed.equals(".")){
                    Display.setText(0 + buttonPressed);
                    Typing = true;
                }else if(buttonPressed.equals("BACK")) {
                }else if(buttonPressed.equals("0")) {
                }else{
                    Display.setText(buttonPressed);
                    Typing = true;
                }
            }
        }else{
            if(Typing){
                Brain.setOperand(Double.parseDouble(Display.getText().toString()));
                Typing = false;
            }
            Brain.performOperation(buttonPressed);
            Display.setText(df.format(Brain.getResult()));
        }
    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
//        outState.putDouble("OPERAND", Brain.getResult());
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState){
//        super.onRestoreInstanceState(savedInstanceState);
//        Brain.setOperand(savedInstanceState.getDouble("OPERAND"));
//        Display.setText(df.format(Brain.getResult()));
//    }
}
