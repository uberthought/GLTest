package org.uberthought;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.LinearLayout;

public class GLTestActivity extends Activity {
    private GLSurfaceView _glSurfaceView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        LinearLayout layout = (LinearLayout)findViewById(R.id.main);

        
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//            WindowManager.LayoutParams.FLAG_FULLSCREEN);

        OpenGLRenderer openGLRenderer = new OpenGLRenderer(this);
        
        _glSurfaceView = new GLSurfaceView(this);
        _glSurfaceView.setEGLContextClientVersion(2);
        _glSurfaceView.setRenderer(openGLRenderer);
        
        HtmlGUI htmlGUI = new HtmlGUI(this, openGLRenderer);
        
        layout.addView(htmlGUI);
        layout.addView(_glSurfaceView);
}
    
    @Override
    protected void onResume() {
        super.onResume();
        _glSurfaceView.onResume();
        }

    @Override
    protected void onPause() {
        super.onPause();
        _glSurfaceView.onPause();
    }
}