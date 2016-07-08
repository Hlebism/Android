package com.example.klient2.rsa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RSADECIPHER extends Activity {

    String Result;
    EditText N, D, MSG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsadecipher);
        File path = this.getFilesDir();
        File file = new File(path.toString() + "/private.txt");
        BufferedReader reader = null;
        List<Integer> list = new ArrayList<Integer>();
        if (file.exists()) {
            try {
                reader = new BufferedReader(new FileReader(file));
                String text = null;
                String[] arr = reader.readLine().split(" ");

                for (int i = 0; i < arr.length; i++)
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
            D = (EditText) findViewById(R.id.D);
            N = (EditText) findViewById(R.id.N);
            D.setText(list.get(0).toString());
            N.setText(list.get(1).toString());
        }

    }

    public void button_submit(View v) {
        TextView tw = (TextView) findViewById(R.id.result);
        final Context context = this;

        D = (EditText) findViewById(R.id.D);
        N = (EditText) findViewById(R.id.N);
        MSG = (EditText) findViewById(R.id.MSG);
        String sz_d = D.getText().toString();
        String sz_n = N.getText().toString();
        String sz_msg = MSG.getText().toString();
        if (sz_d.matches("")) {
            tw.setText("Number D is empty");
            return;
        } else if (sz_n.matches("")) {
            tw.setText("Number N is empty");
            return;
        } else if (sz_msg.matches("")) {
            tw.setText("Message is EMPTY!!");
            return;
        }
        int d = Integer.parseInt(sz_d);
        int n = Integer.parseInt(sz_n);
        String[] words = sz_msg.split(" ");
        Result = "";
        for (int i = 0; i < words.length; i++) {
            int c = Integer.parseInt(words[i]);
            c = pow_mod(c, d, n);
            Result += String.valueOf((char) c);
        }

        Intent intent = new Intent(context, RSAVIEWDECIPHERED.class);
        intent.putExtra("result", Result);
        finish();
        startActivity(intent);
        return;
    }

    String getResult()
    {
        return Result;
    }


    private int pow_mod(int a, int w, int n) {
        int pot, wyn, q;

        // wykładnik w rozbieramy na sumę potęg 2
        // przy pomocy algorytmu Hornera. Dla reszt
        // niezerowych tworzymy iloczyn potêg a modulo n.
        pot = a;
        wyn = 1;
        for (q = w; q > 0; q /= 2) {
            if (q % 2 == 1) {
                wyn = (wyn * pot) % n;
            }
            pot = (pot * pot) % n; // kolejna potęga
        }
        return wyn;
    }
}
