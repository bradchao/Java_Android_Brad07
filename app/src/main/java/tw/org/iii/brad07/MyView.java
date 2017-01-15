package tw.org.iii.brad07;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by user on 2017/1/15.
 */

public class MyView extends View {
    private Context c;
    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
        c = context;
        setBackgroundColor(Color.RED);
    }
}
