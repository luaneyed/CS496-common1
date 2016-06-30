package cs496.common_assignment_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import android.support.v4.view.ViewPager;

import org.w3c.dom.Text;

public class Image_FullScreen extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_full_view);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        Image_Adapter adapter = new Image_Adapter(this);
        viewPager.setAdapter(adapter);

        Intent in = getIntent();
        viewPager.setCurrentItem(in.getIntExtra("num_image", 0));
    }

}