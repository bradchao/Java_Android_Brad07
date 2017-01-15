package tw.org.iii.brad07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by user on 2017/1/15.
 */

public class MyView extends View {
    private Context c;
    private int viewW, viewH;

    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
        c = context;
        setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        viewW = getWidth(); viewH = getHeight();
        Log.v("brad", "MyView:onSizeChanged(): " +viewW + "x" + viewH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
