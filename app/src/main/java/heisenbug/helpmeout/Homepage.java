package heisenbug.helpmeout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class Homepage extends Activity {
    SmsManager smsManager;
    String targetAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        smsManager = SmsManager.getDefault();
        final Button handButton = (Button) findViewById(R.id.hand);
        handButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                smsManager.sendTextMessage("+17788652902",null,"I need a hand!",null,null);
            }
        });
    }


    @Override
      public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homepage, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void doOtherButton(View view) {
        Intent intent = new Intent(this, OtherActionsPage.class);
        startActivity(intent);
    }
    public void doMainSettingsButton(View view) {
        Intent intent = new Intent(this, SettingsPage.class);
        startActivity(intent);
    }
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            // do stuff to
        }
    }
}
