package io.kickflip.glasscast;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;

import io.kickflip.sdk.Util;
import io.kickflip.sdk.activity.ImmersiveActivity;
import io.kickflip.sdk.av.AVRecorder;
import io.kickflip.sdk.av.SessionConfig;

/**
 * Demonstrates using the Kickflip SDK components to
 * create a more traditional Camera app that allows creating multiple
 * recordings per Activity lifecycle.
 * <p/>
 * In this example recording will stop if the Activity proceeds through {@link #onStop()}
 * <p/>
 * <b>Note:</b> This Activity is marked in AndroidManifest.xml with the property android:configChanges="orientation|screenSize"
 * This is a shortcut to prevent the onDestroy ... onCreate ... onDestroy cycle when the screen powers off. Without this
 * shortcut, more careful management of application state is required to make sure you don't create a recorder and set the preview
 * display as a result of that ephemeral onCreate. If you do, you'll get a crash as the Camera won't be able to be acquired.
 * See: http://stackoverflow.com/questions/10498636/prevent-android-activity-from-being-recreated-on-turning-screen-off
 */
public class CameraActivity extends ImmersiveActivity {
    private AVRecorder mRecorder;
    private Button mRecordingButton;
    private boolean mFirstRecording = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        (findViewById(R.id.cameraPreview)).setKeepScreenOn(true);
        /*
        mRecordingButton = (Button) findViewById(R.id.recordButton);
        mRecordingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRecorder.isRecording()) {
                    mRecordingButton.setBackgroundResource(R.drawable.red_dot);
                    mRecorder.stopRecording();
                } else {
                    if (!mFirstRecording) {
                        resetAVRecorder();
                    } else {
                        mFirstRecording = false;
                    }
                    mRecorder.startRecording();
                    mRecordingButton.setBackgroundResource(R.drawable.red_dot_stop);
                }
            }
        });
        */
        SessionConfig config = Util.createRTMPpSessionConfig(this, "rtmp://162.243.133.238/live/glass");
        if (mRecorder == null) {
            // This null check exists because onCreate may be called in the process of
            // destroying the activity
            mRecorder = new AVRecorder(config);
            mRecorder.setPreviewDisplay((io.kickflip.sdk.view.GLCameraView) findViewById(R.id.cameraPreview));
            mRecorder.startRecording();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mRecorder.isRecording()) mRecorder.stopRecording();
    }

    @Override
    public boolean onKeyDown(int keycode, KeyEvent event) {
        if (keycode == KeyEvent.KEYCODE_DPAD_CENTER) {
            mRecorder.stopRecording();
            finish();
            return true;
        } else
            return super.onKeyDown(keycode, event);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mRecorder.release();
    }

    private void resetAVRecorder() {
        mRecorder.reset(Util.createRTMPpSessionConfig(this, "rtmp://162.243.133.238/live/glass"));
    }

}