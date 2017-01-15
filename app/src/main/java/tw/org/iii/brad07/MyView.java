package tw.org.iii.brad07;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by user on 2017/1/15.
 */

public class MyView extends View {
    private Context c;
    private int viewW, viewH;
    private Paint paint;
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private GestureDetector gd;

    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
        c = context;
        setBackgroundColor(Color.BLACK);

        gd = new GestureDetector(c, new MyGDListener());

        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(4);

        lines = new LinkedList<>();
    }

    private class MyGDListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            Log.v("brad", "onDown");
            return true; //super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.v("brad", "onFling");
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX(); float ey = event.getY();
        if (event.getAction()==MotionEvent.ACTION_DOWN){
            addNewLine(ex, ey);
        }else if (event.getAction()==MotionEvent.ACTION_UP){
        }else if (event.getAction()==MotionEvent.ACTION_MOVE){
            addNewPoint(ex, ey);
        }
        return gd.onTouchEvent(event); //super.onTouchEvent(event);
    }

    private void addNewLine(float x, float y){
        LinkedList<HashMap<String,Float>> line = new LinkedList<>();
        lines.add(line);
        addNewPoint(x, y);
    }

    private void addNewPoint(float x, float y){
        HashMap<String, Float> point = new HashMap<>();
        point.put("x", x); point.put("y",y);
        lines.getLast().add(point);
        invalidate();   // repaint
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

        for (LinkedList<HashMap<String,Float>> line : lines){
            for (int i=1; i<line.size(); i++){
                canvas.drawLine(line.get(i-1).get("x"),line.get(i-1).get("y"),
                        line.get(i).get("x"),line.get(i).get("y"),paint);
            }
        }

    }
}
