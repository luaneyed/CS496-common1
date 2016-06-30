package cs496.common_assignment_1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;


public class Calculator extends Fragment implements OnClickListener {

    private TextView Display;
    private Boolean Typing = false;
//    private Boolean Typing0 = false;
    private Brain Brain;
    private static final String DIGITS = "0123456789.";

    DecimalFormat df = new DecimalFormat("@##########");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calculator, container, false);
//        return view;

        Brain = new Brain();
        Display = (TextView) view.findViewById(R.id.text1);

        df.setMinimumFractionDigits(0);
        df.setMinimumIntegerDigits(1);
        df.setMaximumIntegerDigits(10);

        view.findViewById(R.id.button0).setOnClickListener(this);
        view.findViewById(R.id.button1).setOnClickListener(this);
        view.findViewById(R.id.button2).setOnClickListener(this);
        view.findViewById(R.id.button3).setOnClickListener(this);
        view.findViewById(R.id.button4).setOnClickListener(this);
        view.findViewById(R.id.button5).setOnClickListener(this);
        view.findViewById(R.id.button6).setOnClickListener(this);
        view.findViewById(R.id.button7).setOnClickListener(this);
        view.findViewById(R.id.button8).setOnClickListener(this);
        view.findViewById(R.id.button9).setOnClickListener(this);

        view.findViewById(R.id.buttonAdd).setOnClickListener(this);
        view.findViewById(R.id.buttonSubtract).setOnClickListener(this);
        view.findViewById(R.id.buttonMultiply).setOnClickListener(this);
        view.findViewById(R.id.buttonDivide).setOnClickListener(this);
        view.findViewById(R.id.buttonSign).setOnClickListener(this);
        view.findViewById(R.id.buttonDecimal).setOnClickListener(this);
        view.findViewById(R.id.buttonEqual).setOnClickListener(this);
        view.findViewById(R.id.buttonCancel).setOnClickListener(this);
        view.findViewById(R.id.buttonInverse).setOnClickListener(this);
        view.findViewById(R.id.buttonBack).setOnClickListener(this);
        view.findViewById(R.id.buttonSquareRoot).setOnClickListener(this);

        return view;
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
//                        Typing0 = true;
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

//    @Override
//    public void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
//        outState.putDouble("OPERAND", Brain.getResult());
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState){
//        super.onRestoreInstanceState(savedInstanceState);
//        Brain.setOperand(savedInstanceState.getDouble("OPERAND"));
//        Display.setText(df.format(Brain.getResult()));
//    }
}
