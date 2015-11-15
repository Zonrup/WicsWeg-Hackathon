package heisenbug.helpmeout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Waez on 2015-11-14.
 */
//
public class NotificationReceiverActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Homepage.isRunning) {
            Intent intent = new Intent(this, Homepage.class);
            startActivity(intent);
        }
        finish();
    }
}

