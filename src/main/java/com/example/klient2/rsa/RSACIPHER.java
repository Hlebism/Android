package com.example.klient2.rsa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RSACIPHER extends Activity
{
        Button button;
        EditText E, N, MSG;
        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_rsacipher);
            File path = this.getFilesDir();
            File file = new File(path.toString() + "/public.txt");
            BufferedReader reader = null;
            List<Integer> list = new ArrayList<Integer>();
            if(file.exists()) {

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
                } catch (IOException e) {}
                }
                E = (EditText) findViewById(R.id.E);
                N = (EditText) findViewById(R.id.N);
                E.setText(list.get(0).toString());
                N.setText(list.get(1).toString());
            }

        }
        public void button_submit(View v)
        {
            TextView tw = (TextView)findViewById(R.id.result);
            final Context context = this;
            File path = context.getFilesDir();

            E = (EditText) findViewById(R.id.E);
            N = (EditText) findViewById(R.id.N);
            MSG = (EditText) findViewById(R.id.MSG);
            String sz_e = E.getText().toString();
            String sz_n = N.getText().toString();
            String sz_msg = MSG.getText().toString();
            if (sz_e.matches("")) {
                tw.setText("Number E is empty");
                return;
            } else if (sz_n.matches("")) {
                tw.setText("Number N is empty");
                return;
            } else if (sz_msg.matches("")){
                tw.setText("Message is EMPTY!!");
                return;
            }
            int e = Integer.parseInt(sz_e);
            int n = Integer.parseInt(sz_n);
            int[] textciphered = new int[sz_msg.length()];
            for (int i = 0; i < sz_msg.length(); i++) {
                char c = sz_msg.charAt(i);
                int i_c = (int) c; //to ASCII
                textciphered[i] = pow_mod(i_c, e, n);
            }
            File myDir = context.getFilesDir();
            try {
                FileWriter save = new FileWriter(path.toString()+"/textciphered.txt");
                for (int i = 0; i < textciphered.length; i++) {
                    save.write(textciphered[i] + " ");
                }
                save.close();
            }
            catch (IOException ioe) {}
            finish();
            Intent intent = new Intent(context, RSAVIEWCIPHERED.class);
            startActivity(intent);
            return;
        }

        private int pow_mod(int a, int w, int n) {
            int pot, wyn, q;
            pot = a;
            wyn = 1;
            for (q = w; q > 0; q /= 2) {
                if (q % 2 == 1) {
                    wyn = (wyn * pot) % n;
                }
                pot = (pot * pot) % n;
            }
            return wyn;
    }
}

