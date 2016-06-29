package cs496.common_assignment_1;

import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Image_FullScreen extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_full_view);
        Intent in = getIntent();

        int image =in.getIntExtra("image", 0);

        ImageView imageview = (ImageView) findViewById(R.id.image);

        imageview.setImageResource(image);
    }
}
