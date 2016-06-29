package cs496.common_assignment_1;

import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
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


public class Images extends Fragment {

    GridView gridView;

    private static final String TAG_IMAGES = "images";
    private static final String TAG_NAME = "name";
    private static final String TAG_IMAGE = "image";

    private static final int PICTURE_NUM = 3;

    // Array of strings storing country names
    String[] names = new String[] {
            "SOUL COOKE",
            "NATURE",
            "OLD BOY"
    };

    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] images = new int[]{
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.images, container, false);

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InputStream in = getResources().openRawResource(R.raw.images);

        String data = null;
        ArrayList<HashMap<String, String>> imageList= new ArrayList<HashMap<String, String>>();

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

//        if (data != null) {
//            try {
//                JSONObject jsonObj = new JSONObject(data);
//                JSONArray images = jsonObj.getJSONArray(TAG_IMAGES);
//                for (int i = 0; i < images.length(); i++) {
//                    JSONObject c = images.getJSONObject(i);
//
//                    String name = c.getString(TAG_NAME);
//                    String image = c.getString(TAG_IMAGE);
//
//                    HashMap<String, Object> animage = new HashMap<String, Object>();
//
//                    Drawable tempimage = getResources().getDrawable(R.drawable.image1);
//
//                    animage.put(TAG_NAME, name);
//                    animage.put(TAG_IMAGE, tempimage);
//
//                    imageList.add(animage);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }

        for(int i=0;i<PICTURE_NUM;i++){
            HashMap<String, String> hm = new HashMap<String,String>();
            hm.put(TAG_NAME, names[i]);
            hm.put(TAG_IMAGE, Integer.toString(images[i]));
            imageList.add(hm);
        }

        gridView = (GridView) view.findViewById(R.id.grid);

        SimpleAdapter adapter = new SimpleAdapter(
                getActivity(), imageList,
                R.layout.image_small_view, new String[] { TAG_NAME, TAG_IMAGE }, new int[] { R.id.name, R.id.image});

        gridView.setAdapter(adapter);

        // Gridview on item click listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting values from selected ListItem
                String name = ((TextView) view.findViewById(R.id.name)).getText().toString();

                // Starting single image activity
                Intent in = new Intent(getActivity().getApplicationContext(),
                        Image_FullScreen.class);

                int i;
                for(i = 0 ; i < PICTURE_NUM ; i ++)
                {
                    if(name.equals(names[i]))
                    {
                        in.putExtra(TAG_IMAGE, images[i]);
                        break;
                    }
                }
                if(i == PICTURE_NUM)
                    throw new RuntimeException();

                startActivity(in);

            }
        });
        return view;
    }
}