package heisenbug.helpmeout;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Homepage extends Activity {
    static boolean isRunning = false;
    int nocolor = Color.argb(0,0,0,0);
    Homepage homepage = this;
    SmsManager smsManager;
    String message = null;
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        isRunning = true;
        notificationManager  = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        createNotification();
        smsManager = SmsManager.getDefault();
        final ImageButton handButton = (ImageButton) findViewById(R.id.hand);
        final ImageButton foodButton = (ImageButton) findViewById(R.id.fries);
        final ImageButton phoneButton = (ImageButton) findViewById(R.id.phone);
        final ImageButton sosButton = (ImageButton) findViewById(R.id.sosButton);
        final Button contact2 = (Button) findViewById(R.id.contact2);
        final Button contact1 = (Button) findViewById(R.id.contact1);
        final Button contact3 = (Button) findViewById(R.id.contact3);
        final Button contact4 = (Button) findViewById(R.id.contact4);
        final ImageButton sendButton = (ImageButton) findViewById(R.id.send);
        contact1.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
        contact2.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
        contact3.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
        contact4.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
        final List<String> targets = new ArrayList<String>();
        targets.add(0, "");
        targets.add(1, "");
        targets.add(2, "");
        targets.add(3, "");
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        phoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "Call me maybe!";
                phoneButton.setColorFilter(Color.argb(127, 0, 255, 0));
                foodButton.setColorFilter(nocolor);
                handButton.setColorFilter(nocolor);
            }
        });
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "Food I need!";
                foodButton.setColorFilter(Color.argb(127,0,255,0));
                phoneButton.setColorFilter(nocolor);
                handButton.setColorFilter(nocolor);
            }
        });
        handButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = "I need a hand!";
                handButton.setColorFilter(Color.argb(127, 0, 255, 0));
                phoneButton.setColorFilter(nocolor);
                foodButton.setColorFilter(nocolor);
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean messageSent = false;
                if (message != null) {
                    if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                        Location locationGPS = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        message += ("\nLast Known Location: https://maps.google.com/maps?&z=13&q=" +
                                locationGPS.getLatitude() + "+" +
                                locationGPS.getLongitude() + "&ll=" +
                                locationGPS.getLatitude() + "+" +
                                locationGPS.getLongitude() );
                    }
                    for (int i = 0; i < 4; i++) {
                        String s = targets.get(i);
                        if (!s.isEmpty()) {
                            smsManager.sendTextMessage(s, null, message, null, null);
                            messageSent = true;
                        }
                        targets.remove(i);
                        targets.add(i,"");
                    }
                    Toast toast = Toast.makeText(homepage,"Message sent",Toast.LENGTH_SHORT);
                    toast.show();
                    message = null;
                }
                if (!messageSent) {
                    new AlertDialog.Builder(homepage)
                            .setTitle("Can't do that")
                            .setMessage("Please select at least 1 contact and an action")
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();
                }
                phoneButton.setColorFilter(nocolor);
                handButton.setColorFilter(nocolor);
                foodButton.setColorFilter(nocolor);
                contact1.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact2.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact3.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                contact4.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
            }
        });
        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (targets.get(0).isEmpty()) {
                    contact1.setBackground(getResources().getDrawable(R.drawable.selected_button_background));
                    targets.remove(0);
                    targets.add(0,"+17788652902");
                } else {
                    contact1.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                    targets.remove(0);
                    targets.add(0,"");
                }
            }
        });
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (targets.get(1).isEmpty()) {
                    contact2.setBackground(getResources().getDrawable(R.drawable.selected_button_background));
                    targets.remove(1);
                    targets.add(1,"+17787088611");
                } else {
                    contact2.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                    targets.remove(1);
                    targets.add(1,"");
                }
            }
        });
        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (targets.get(2).isEmpty()) {
                    contact3.setBackground(getResources().getDrawable(R.drawable.selected_button_background));
                    targets.remove(2);
                    targets.add(2,"+16047197895");
                } else {
                    contact3.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                    targets.remove(2);
                    targets.add(2,"");
                }
            }
        });
        contact4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (targets.get(3).isEmpty()) {
                    contact4.setBackground(getResources().getDrawable(R.drawable.selected_button_background));
                    targets.remove(3);
                    targets.add(3,"+16044408738");
                } else {
                    contact4.setBackground(getResources().getDrawable(android.R.drawable.btn_default));
                    targets.remove(3);
                    targets.add(3,"");
                }
            }
        });
        sosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:+16044408738"));
                startActivity(intent);
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
        // hide the notification after its selected
        noti.flags |= Notification.FLAG_NO_CLEAR;

        notificationManager.notify(0, noti);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        isRunning = false;
    }
    @Override
    protected void onStop() {
        super.onStop();
        isRunning=false;
    }
}
