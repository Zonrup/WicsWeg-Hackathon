package heisenbug.helpmeout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Homepage extends Activity {
    PorterDuff.Mode mode = PorterDuff.Mode.SRC_ATOP;
    Homepage homepage = this;
    SmsManager smsManager;
    String targetAddress = null;
    String message = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        createNotification();
        smsManager = SmsManager.getDefault();
        final ImageButton handButton = (ImageButton) findViewById(R.id.hand);
        final ImageButton foodButton = (ImageButton) findViewById(R.id.fries);
        final ImageButton phoneButton = (ImageButton) findViewById(R.id.phone);
        final Button contact2 = (Button) findViewById(R.id.contact2);
        final Button contact1 = (Button) findViewById(R.id.contact1);
        final Button contact3 = (Button) findViewById(R.id.contact3);
        final ImageButton sendButton = (ImageButton) findViewById(R.id.send);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "Call me maybe!";
                Drawable d = getResources().getDrawable(R.drawable.telephonejpg);
                d.setColorFilter(0x00FF00,mode);
                phoneButton.setImageDrawable(d);
                foodButton.setImageDrawable(getResources().getDrawable(R.drawable.fries));
                handButton.setImageDrawable(getResources().getDrawable(R.drawable.hand));
            }
        });
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "Food I need!";
                Drawable d = getResources().getDrawable(R.drawable.fries);
                d.setColorFilter(0x00FF00,mode);
                foodButton.setImageDrawable(d);
                phoneButton.setImageDrawable(getResources().getDrawable(R.drawable.telephonejpg));
                handButton.setImageDrawable(getResources().getDrawable(R.drawable.hand));
            }
        });
        handButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "I need a hand!";
                Drawable d = getResources().getDrawable(R.drawable.hand);
                d.setColorFilter(0x00FF00,mode);
                handButton.setImageDrawable(d);
                phoneButton.setImageDrawable(getResources().getDrawable(R.drawable.telephonejpg));
                foodButton.setImageDrawable(getResources().getDrawable(R.drawable.fries));
            }
        });
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
                phoneButton.setImageDrawable(getResources().getDrawable(R.drawable.telephonejpg));
                foodButton.setImageDrawable(getResources().getDrawable(R.drawable.fries));
                handButton.setImageDrawable(getResources().getDrawable(R.drawable.hand));
                contact1.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact2.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact3.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
            }
        });
        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetAddress = "+17788652902";
                contact1.setBackground(getResources().getDrawable(R.drawable.selected_button_background));
                contact2.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact3.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
            }
        });
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetAddress = "+17787088611";
                contact1.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact2.setBackground(getResources().getDrawable(R.drawable.selected_button_background));
                contact3.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
            }
        });
        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                targetAddress = "+16047197895";
                contact1.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact2.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact3.setBackground(getResources().getDrawable(R.drawable.selected_button_background));
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
    public void createNotification() {
        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, NotificationReceiverActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);

        // Build notification
        // Actions are just fake
        Notification noti = new Notification.Builder(this)
                .setContentTitle("HelpMeOut")
                .setContentText("Press to open HelpMeOut app.")
                .setSmallIcon(R.drawable.telephone)
                .setContentIntent(pIntent)
                .setColor(0xFFFF0000)
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_NO_CLEAR;

        notificationManager.notify(0, noti);

    }
}
