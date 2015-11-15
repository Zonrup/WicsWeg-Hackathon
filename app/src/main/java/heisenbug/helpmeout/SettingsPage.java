package heisenbug.helpmeout;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOError;
import java.io.IOException;


public class SettingsPage extends Activity {
    SettingsPage settingsPage = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);
        Button button_settings_sos = (Button) findViewById(R.id.button_settings_sos);
        // this doens't work lol
        button_settings_sos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                File file = new File(Homepage.FILENAME);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        Toast toast = Toast.makeText(settingsPage,"Could not create file.",Toast.LENGTH_SHORT);
                        toast.show();
                        return;
                    }
                }*/
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(settingsPage);
                alertDialog.setTitle("SOS Number");
                alertDialog.setMessage("Enter SOS Phone Number");
                final EditText input = new EditText(settingsPage);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("YES",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String number = input.getText().toString();
                                try {
                                    FileOutputStream fos = openFileOutput(Homepage.FILENAME, Context.MODE_PRIVATE);
                                    fos.write(number.getBytes());
                                    fos.close();
                                } catch (IOException e){
                                    Toast toast = Toast.makeText(settingsPage,"Could not create file.",Toast.LENGTH_SHORT);
                                    toast.show();
                                }
                                /*try (BufferedWriter sos_number = new BufferedWriter(new FileWriter(new File(Homepage.FILENAME)))) {
                                    sos_number.write(number);
                                } catch (IOException e) {
                                    Toast toast = Toast.makeText(settingsPage,"Could not create file.",Toast.LENGTH_SHORT);
                                    toast.show();
                                }*/
                            }
                        });

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_settings_page, menu);
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
}
