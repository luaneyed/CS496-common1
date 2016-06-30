package cs496.common_assignment_1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.opengl.GLES20;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import java.util.List;
import java.util.Map;


import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by q on 2016-06-30.
 */
public class Image_NewAdapter extends SimpleAdapter{
    Context context;

    Image_NewAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to){
        super(context, data, resource, from, to);
        this.context=context;
        //SimpleAdapter(context, data, resource, from, to);
    }

    @Override
    public void setViewImage(ImageView v, String value) {
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(value), options);
            int scale = 1;

            DisplayMetrics displaymetrics = new DisplayMetrics();
            ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
            int height = displaymetrics.heightPixels;
            int width = displaymetrics.widthPixels;
            int minsize = Math.min(height / 2 , width / 2);
            if(options.outHeight > minsize || options.outWidth > minsize)
                scale = (int)Math.pow(2, (int)Math.round(Math.log(minsize / (double)Math.max(options.outHeight, options.outWidth)) / Math.log(0.5)));
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            Bitmap resized = BitmapFactory.decodeResource(context.getResources(), Integer.parseInt(value), o2);

            v.setImageBitmap(resized);

            //v.setImageResource(Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            v.setImageURI(Uri.parse(value));
        }
    }

}
