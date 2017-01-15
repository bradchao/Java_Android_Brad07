package tw.org.iii.brad07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private View mainView;
    private int mainWidth, mainHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainView = findViewById(R.id.activity_main);

        mainWidth = mainView.getWidth();
        mainHeight = mainView.getHeight();
        Log.v("brad", "1. " + mainWidth + "x" + mainHeight);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mainWidth = mainView.getWidth();
        mainHeight = mainView.getHeight();
        Log.v("brad", "2. " + mainWidth + "x" + mainHeight);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mainWidth = mainView.getWidth();
        mainHeight = mainView.getHeight();
        Log.v("brad", "3. " + mainWidth + "x" + mainHeight);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            mainWidth = mainView.getWidth();
            mainHeight = mainView.getHeight();
            Log.v("brad", "4. " + mainWidth + "x" + mainHeight);
        }else{
            Log.v("brad", "xx focus");
        }
    }
}
