package com.example.klient2.rsa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RSAVIEWKEYS extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewkeys);
        final Context context = this;
        File path = context.getFilesDir();
        List<Integer> list1 = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();

        File file1 = new File(path.toString() + "/public.txt");
        File file2 = new File(path.toString() + "/private.txt");
        BufferedReader reader1 = null, reader2 = null;

        try {
            reader1 = new BufferedReader(new FileReader(file1));
            reader2 = new BufferedReader(new FileReader(file2));
            String text = null;

            String[] arr1=reader1.readLine().split(" ");
            String[] arr2=reader2.readLine().split(" ");

            for(int i=0;i<arr1.length;i++)
                list1.add(Integer.parseInt(arr1[i]));

            for(int i=0;i<arr2.length;i++)
                list2.add(Integer.parseInt(arr2[i]));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader1 != null) {
                    reader1.close();
                }
                if (reader2 != null) {
                    reader2.close();
                }
            } catch (IOException e) {
            }
        }
        TextView tv = (TextView) findViewById(R.id.view_result);
        String result = "Public Key:\nE = " + list1.get(0) + "\nN = " + list1.get(1) + "\n\nPrivate Key:\nD = " + list2.get(0) + "\nN = " + list2.get(1);
        tv.setText(result);

    }

    @Override
    public void onBackPressed()
    {
        finish();
        Intent intent = new Intent(this, RSAMAIN.class);
        startActivity(intent);

    }
}