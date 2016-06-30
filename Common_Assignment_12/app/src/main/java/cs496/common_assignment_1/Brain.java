package cs496.common_assignment_1;

/**
 * Created by q on 2016-06-30.
 */
public class Brain {

    private double mOperand;
    private double mWaitingOperand;
    private String mWaitingOperator;

    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MULT = "*";
    public static final String DIV = "/";
    public static final String CLEAR = "C";
    public static final String SR = "√";
    public static final String INVERT = "1/x";
    public static final String SIGN = "+/-";


    public Brain(){
        mOperand=0;
        mWaitingOperand=0;
        mWaitingOperator="";
    }

    public void setOperand(double operand) {mOperand = operand;}

    public double getResult() {return mOperand;}

    public String toString() {return Double.toString(mOperand);}

    protected double performOperation(String operator){
        if(operator.equals(CLEAR)){
            mOperand=0;
            mWaitingOperand=0;
            mWaitingOperator="";
        }else if (operator.equals(SR)){
            mOperand = Math.sqrt(mOperand);
        }else if(operator.equals(INVERT)){
            if(mOperand!=0)
            mOperand = 1/mOperand;
        }else if(operator.equals(SIGN)){
            mOperand = -mOperand;
        }else{
            performWaitingOperation();
            mWaitingOperator = operator;
            mWaitingOperand = mOperand;
        }
        return mOperand;
    }

    protected void performWaitingOperation(){
        if(mWaitingOperator.equals(ADD)) {
            mOperand = mWaitingOperand + mOperand;
        }else if(mWaitingOperator.equals(SUB)){
            mOperand = mWaitingOperand - mOperand;
        }else if(mWaitingOperator.equals(MULT)){
            mOperand = mWaitingOperand * mOperand;
        }else if(mWaitingOperator.equals(DIV)){
            if(mOperand!=0) {
                mOperand = mWaitingOperand / mOperand;
            }
        }
    }
}
