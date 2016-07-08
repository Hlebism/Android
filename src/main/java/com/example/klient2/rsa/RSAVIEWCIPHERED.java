package com.example.klient2.rsa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Klient 2 on 2016-06-26.
 */
public class RSAVIEWCIPHERED extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewciphered);
        final Context context = this;
        File path = context.getFilesDir();
        List<Integer> list = new ArrayList<Integer>();

        File file = new File(path.toString() + "/textciphered.txt");
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(file));
            String text = null;

            String[] arr=reader.readLine().split(" ");


            for(int i=0;i<arr.length;i++)
                list.add(Integer.parseInt(arr[i]));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }
        TextView tv = (TextView) findViewById(R.id.view_result);
        String result = list.get(0).toString();
        for(int i  = 1; i < list.size(); i++) {
            result += " ";
            result += list.get(i).toString();
        }
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

