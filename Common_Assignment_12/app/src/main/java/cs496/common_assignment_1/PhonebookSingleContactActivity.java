package cs496.common_assignment_1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class PhonebookSingleContactActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonebook_single_contact);
        Intent in = getIntent();

        String name = in.getStringExtra("name");
        String email = in.getStringExtra("email");
        String mobile = in.getStringExtra("mobile");

        TextView txtName = (TextView) findViewById(R.id.txtname);
        TextView txtEmail = (TextView) findViewById(R.id.txtemail);
        TextView txtMobile = (TextView) findViewById(R.id.txtmobile);

        txtName.setText(name);
        txtEmail.setText(email);
        txtMobile.setText(mobile);
    }
}
