package com.example.klient2.rsa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class RSAMAIN extends Activity {

    Button button1, button2, button3;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsamain);
        addListenerOnButton();

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Application")
                .setMessage("Do you want to close this application??")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        File path = context.getFilesDir();
                        File file1 = new File(path, "public.txt");
                        file1.delete();
                        File file2 = new File(path, "private.txt");
                        file2.delete();
                        File file3 = new File(path, "textciphered.txt");
                        file3.delete();

                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
    public void addListenerOnButton()
    {

        final Context context = this;

            button1 = (Button) findViewById(R.id.buttondata);

            button1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    finish();
                    Intent intent = new Intent(context, RSADATA.class);
                    startActivity(intent);

                }

            });

            button2 = (Button) findViewById(R.id.buttoncipher);

            button2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    finish();
                    Intent intent = new Intent(context, RSACIPHER.class);
                    startActivity(intent);

                }

            });
            button3 = (Button) findViewById(R.id.buttondecipher);

            button3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View arg0) {

                    finish();
                    Intent intent = new Intent(context, RSADECIPHER.class);
                    startActivity(intent);

                }

            });


        }
}

