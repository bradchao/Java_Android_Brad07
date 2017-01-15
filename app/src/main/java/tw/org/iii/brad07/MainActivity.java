package tw.org.iii.brad07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private View mainView;
    private MyView myView;
    private int mainWidth, mainHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = findViewById(R.id.activity_main);
        myView = (MyView) findViewById(R.id.myView);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            mainWidth = mainView.getWidth();
            mainHeight = mainView.getHeight();
            Log.v("brad", "4. " + myView.getWidth() + "x" + myView.getHeight());
        }else{
            Log.v("brad", "xx focus");
        }
    }
}
