package cs496.common_assignment_1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;


public class PhoneBook extends Fragment {

    ListView listView;

    private static final String TAG_CONTACTS = "contacts";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_ADDRESS = "address";
    private static final String TAG_GENDER = "gender";
    private static final String TAG_PHONE = "phone";
    private static final String TAG_PHONE_M = "mobile";
    private static final String TAG_PHONE_H = "home";
    private static final String TAG_PHONE_O = "office";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.phonebook, container, false);

//        return view;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InputStream in = getResources().openRawResource(R.raw.contacts);

        String data = null;
        ArrayList<HashMap<String, String>> contactList= new ArrayList<HashMap<String, String>>();

        try{
            InputStreamReader stream = new InputStreamReader(in, "utf-8");
            BufferedReader buffer = new BufferedReader(stream);

            String read;
            StringBuilder sb = new StringBuilder("");

            while((read=buffer.readLine()) != null){
                sb.append(read);
            }

            in.close();

            data =  sb.toString();
        }catch(Exception e){
            e.printStackTrace();
        }

        if (data != null) {
            try {
                JSONObject jsonObj = new JSONObject(data);
                JSONArray contacts = jsonObj.getJSONArray(TAG_CONTACTS);
                for (int i = 0; i < contacts.length(); i++) {
                    JSONObject c = contacts.getJSONObject(i);

                    String id = c.getString(TAG_ID);
                    String name = c.getString(TAG_NAME);
                    String email = c.getString(TAG_EMAIL);
                    String address = c.getString(TAG_ADDRESS);
                    String gender = c.getString(TAG_GENDER);

                    JSONObject phone = c.getJSONObject(TAG_PHONE);
                    String mobile = phone.getString(TAG_PHONE_M);
                    String home = phone.getString(TAG_PHONE_H);
                    String office = phone.getString(TAG_PHONE_O);

                    HashMap<String, String> contact = new HashMap<String, String>();

                    contact.put(TAG_ID, id);
                    contact.put(TAG_NAME, name);
                    contact.put(TAG_EMAIL, email);
                    contact.put(TAG_PHONE_M, mobile);

                    contactList.add(contact);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        listView = (ListView) view.findViewById(android.R.id.list);

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(), contactList,
                R.layout.list_item, new String[] { TAG_NAME, TAG_EMAIL,
                TAG_PHONE_M }, new int[] { R.id.name,
                R.id.email, R.id.mobile });

        listView.setAdapter(adapter);

//        ListView lv = getListView();

        // Listview on item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name))
                        .getText().toString();
                String email = ((TextView) view.findViewById(R.id.email))
                        .getText().toString();
                String mobile = ((TextView) view.findViewById(R.id.mobile))
                        .getText().toString();

                // Starting single contact activity
                Intent in = new Intent(getActivity().getApplicationContext(),
                        PhonebookSingleContactActivity.class);
                in.putExtra(TAG_NAME, name);
                in.putExtra(TAG_EMAIL, email);
                in.putExtra(TAG_PHONE_M, mobile);
                startActivity(in);

            }
        });
        return view;
    }
}