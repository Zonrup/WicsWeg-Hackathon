package heisenbug.helpmeout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class Homepage extends Activity {
    Homepage homepage = this;
    SmsManager smsManager;
    String targetAddress = null;
    String message = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        smsManager = SmsManager.getDefault();
        final Button handButton = (Button) findViewById(R.id.hand);
        handButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "I need a hand!";
                handButton.setBackground(getResources().getDrawable(R.drawable.selected_button_background));
            }
        });
        final Button sendButton = (Button) findViewById(R.id.send);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (targetAddress == null || message == null) {
                    new AlertDialog.Builder(homepage)
                            .setTitle("Can't do that")
                            .setMessage("Please select a contact and an action")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                } else {
                    smsManager.sendTextMessage(targetAddress, null, message, null, null);
                    targetAddress = null;
                    message = null;
                    Toast toast = Toast.makeText(homepage,"Message sent",Toast.LENGTH_SHORT);
                    toast.show();
                }
                handButton.setBackground(null);
            }
        });
        final Button contact1 = (Button) findViewById(R.id.contact1);
        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetAddress = "+17788652902";
            }
        });
        final Button contact2 = (Button) findViewById(R.id.contact2);
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetAddress = "+17787088611";
            }
        });
        final Button contact3 = (Button) findViewById(R.id.contact3);
        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetAddress = "+16047197895";
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
