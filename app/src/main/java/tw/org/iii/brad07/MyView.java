package tw.org.iii.brad07;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by user on 2017/1/15.
 */

public class MyView extends View {
    private Context c;
    private int viewW, viewH;
    private Paint paint;
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private GestureDetector gd;
    private Resources res;
    private Bitmap ballBmp;
    private float ballW, ballH;
    private Matrix matrix;
    private float ballX, ballY, dx, dy;
    private Timer timer;

    public MyView(Context context, AttributeSet attrs){
        super(context, attrs);
        c = context;
        res = c.getResources();
        setBackgroundColor(Color.BLACK);

        timer = new Timer();
        matrix = new Matrix();
        gd = new GestureDetector(c, new MyGDListener());

        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeWidth(4);

        lines = new LinkedList<>();
    }

    Timer getTimer(){return timer;}
    private class BallTask extends TimerTask {
        @Override
        public void run() {
            if (ballX < 0 || ballX + ballW > viewW){
                dx *= -1;
            }
            if (ballY<0 || ballY + ballH > viewH){
                dy *= -1;
            }


            ballX += dx; ballY+= dy;
            postInvalidate();
        }
    }

    private class MyGDListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDown(MotionEvent e) {
            return true; //super.onDown(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float vX, float vY) {
            if (Math.abs(vX)>Math.abs(vY)){
                if (vX > 1000){
                    Log.v("brad", "Right");
                }else if (vX < -1000){
                    Log.v("brad", "Left");
                }
            }else{
                if (vY > 1000){
                    Log.v("brad", "Down");
                }else if (vY < -1000){
                    Log.v("brad", "Up");
                }
            }
            return super.onFling(e1, e2, vX, vY);
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
        init();
    }

    private void init(){
        ballW = viewW / 8f; ballH = ballW;
        dx = dy = 8;
        ballBmp = BitmapFactory.decodeResource(res,R.drawable.ball2);
        matrix.reset();
        matrix.postScale(ballW/ballBmp.getWidth(), ballH/ballBmp.getHeight());
        ballBmp = Bitmap.createBitmap(ballBmp,0,0,
                ballBmp.getWidth(),ballBmp.getHeight(),matrix,false);
        timer.schedule(new BallTask(),1000, 40);
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
        canvas.drawBitmap(ballBmp, ballX, ballY, null);


    }
}
