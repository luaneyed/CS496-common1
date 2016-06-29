package cs496.common_assignment_1;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PhonebookSingleContactActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonebook_single_contact);
        Intent in = getIntent();

        String name = in.getStringExtra("name");
        String email = in.getStringExtra("email");
        String mobile = in.getStringExtra("mobile");
        String address = in.getStringExtra("address");
        String gender = in.getStringExtra("gender");
        String home = in.getStringExtra("home");
        String office = in.getStringExtra("office");

        TextView txtName = (TextView) findViewById(R.id.txtname);
        TextView txtEmail = (TextView) findViewById(R.id.txtemail);
        TextView txtMobile = (TextView) findViewById(R.id.txtmobile);
        TextView txtAddress = (TextView) findViewById(R.id.txtaddress);
        TextView txtGender = (TextView) findViewById(R.id.txtgender);
        TextView txtHome = (TextView) findViewById(R.id.txthome);
        TextView txtOffice = (TextView) findViewById(R.id.txtoffice);

        txtName.setText(name);
        txtEmail.setText(email);
        txtMobile.setText(mobile);
        txtAddress.setText(address);
        txtGender.setText(gender);
        txtHome.setText(home);
        txtOffice.setText(office);
    }
}
