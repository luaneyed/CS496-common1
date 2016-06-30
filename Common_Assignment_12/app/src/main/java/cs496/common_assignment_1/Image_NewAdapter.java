package cs496.common_assignment_1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by q on 2016-06-30.
 */
public class Image_NewAdapter extends SimpleAdapter{
    Context context;
    private int[] GalImages = Images.images;

    Image_Adapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return GalImages.length;
    }

    public void setViewImage(ImageView v, String value) {
        try {

            v.setImageResource(Integer.parseInt(value));
        } catch (NumberFormatException nfe) {
            v.setImageURI(Uri.parse(value));
        }
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Drawable d = ((ImageView)object).getDrawable();
        if(d instanceof BitmapDrawable)
        {
            Bitmap bitmap = ((BitmapDrawable)d).getBitmap();
            bitmap.recycle();
            bitmap = null;
        }
        d.setCallback(null);

        ((ViewPager) container).removeView((ImageView) object);
    }
}
