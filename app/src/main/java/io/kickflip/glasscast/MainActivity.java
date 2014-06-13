package io.kickflip.glasscast;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;

import io.kickflip.sdk.Kickflip;
import io.kickflip.sdk.api.KickflipCallback;
import io.kickflip.sdk.api.json.Response;
import io.kickflip.sdk.api.json.Stream;
import io.kickflip.sdk.av.BroadcastListener;
import io.kickflip.sdk.av.SessionConfig;
import io.kickflip.sdk.exception.KickflipException;


public class MainActivity extends Activity {
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Kickflip.setup(this, SECRETS.KF_KEY, SECRETS.KF_SECRET, new KickflipCallback() {
            @Override
            public void onSuccess(Response response) {
                String outputLocation = new File(getFilesDir(), "index.m3u8").getAbsolutePath();
                Kickflip.setSessionConfig(new SessionConfig.Builder(outputLocation)
                        .withVideoBitrate(2 * 1000 * 1000)
                        .withLocation(false)
                        .withAdaptiveStreaming(false)
                        .withVideoResolution(1280, 720)
                        .build());
                Kickflip.startGlassBroadcastActivity(MainActivity.this, new BroadcastListener() {
                    @Override
                    public void onBroadcastStart() {

                    }

                    @Override
                    public void onBroadcastLive(Stream stream) {

                    }

                    @Override
                    public void onBroadcastStop() {
                        MainActivity.this.finish();
                    }

                    @Override
                    public void onBroadcastError(KickflipException error) {

                    }
                });
            }

            @Override
            public void onError(KickflipException error) {
                // TODO : Disappoint User
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
