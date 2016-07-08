package com.example.klient2.rsa;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class RSADATA extends Activity
{
    Button button;
    EditText P, Q;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsadata);


    }
    public void button_submit(View v)
    {
        TextView tw = (TextView)findViewById(R.id.result);
        final Context context = this;

        P = (EditText) findViewById(R.id.P);
        Q = (EditText) findViewById(R.id.Q);
        String sz_p = P.getText().toString();
        String sz_q = Q.getText().toString();

                if (sz_p.matches("")) {
                    tw.setText("Number P is empty");
                    return;
                } else if (sz_q.matches("")) {
                    tw.setText("Number Q is empty");
                    return;
                }
                int p = Integer.parseInt(sz_p);
                int q = Integer.parseInt(sz_q);
                if (!isPrime(p)) {
                    tw.setText("Number P is not Prime");
                    return;
                } else if (!isPrime(q)) {
                    tw.setText("Number Q is not Prime");
                    return;
                } else if (p == q) {
                    tw.setText("P is equal to Q");
                    return;
                } else {
                    int e;
                    int phi = (p - 1) * (q - 1); //PHI number
                    int n = p * q; //moduł N
                    for (e = 3; ; e += 2) {
                        if (gcd(e, phi) == 1) {
                            break; //find number E - number condition Prime with number PHI
                        }
                    }
                    int d = inv_mod(e, phi);

                    File path = context.getFilesDir();
                    try {
                        FileWriter save = new FileWriter(path.toString()+"/public.txt");
                        save.write(e + " " + n);
                        save.close();
                        save = new FileWriter(path.toString()+"/private.txt");
                        save.write(d + " " + n);
                        save.close();
                    } catch (IOException ioe) {
                        ioe.printStackTrace();
                    }
                    finish();
                    Intent intent = new Intent(context, RSAVIEWKEYS.class);
                    startActivity(intent);
                }


            }

            private int gcd(int a, int b) //potrzebne do liczby E
            {
                int t;

                while (b != 0) {
                    t = b;
                    b = a % b;
                    a = t;
                }
                ;
                return a;
            }

            private int inv_mod(int a, int n) {
                int a0, n0, p0, p1, q, r, t;

                p0 = 0;
                p1 = 1;
                a0 = a;
                n0 = n;
                q = n0 / a0;
                r = n0 % a0;
                while (r > 0) {
                    t = p0 - q * p1;
                    if (t >= 0) {
                        t = t % n;
                    } else {
                        t = n - ((-t) % n);
                    }
                    p0 = p1;
                    p1 = t;
                    n0 = a0;
                    a0 = r;
                    q = n0 / a0;
                    r = n0 % a0;
                }
                return p1;
            }

            private boolean isPrime(int x) {
                boolean prime = true;
                for (int i = 2; i * i <= x; i++) {
                    if (x % i == 0) {
                        prime = false;
                    }
                }
                if (prime) {
                    return true;
                } else {
                    return false;
                }
            }



    }

