package cs496.common_assignment_1;

import android.widget.ImageView;
import android.content.Context;
import android.util.AttributeSet;

/**
 * Created by q on 2016-06-30.
 */
public class Image_GridViewItem extends ImageView{
    public Image_GridViewItem(Context context) {
        super(context);
    }

    public Image_GridViewItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Image_GridViewItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }
}