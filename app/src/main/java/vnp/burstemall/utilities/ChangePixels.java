package vnp.burstemall.utilities;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by pkapo8 on 9/28/2016.
 */

public class ChangePixels {

    public static int pixelsToDp(int px, Context context) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, px,
                context.getResources().getDisplayMetrics());
    }

}
