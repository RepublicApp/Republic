package com.republic;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import java.io.IOException;

public class CameraActivity extends Activity {
    private SurfaceView     preview = null;
    private SurfaceHolder   previewHolder = null;
    private Camera          camera = null;
    SurfaceHolder.Callback  surfaceCallback = new SurfaceHolder.Callback() {
        public void surfaceCreated(SurfaceHolder holder) {}
        public void surfaceDestroyed(SurfaceHolder holder) {}

        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            try {
                camera.setPreviewDisplay(previewHolder);
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            camera.startPreview();
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        camera = Camera.open();
        camera.setDisplayOrientation(90);
        camera.startPreview();
    }

    @Override
    public void onPause() {
        camera.stopPreview();
        camera.release();
        super.onPause();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        preview = (SurfaceView)findViewById(R.id.camera_surface);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams params = preview.getLayoutParams();
        params.width = metrics.widthPixels;
        params.height = (int)(params.width * (4.0 / 3.0));
        previewHolder = preview.getHolder();
        previewHolder.addCallback(surfaceCallback);
        previewHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        preview.setLayoutParams(params);
    }
}