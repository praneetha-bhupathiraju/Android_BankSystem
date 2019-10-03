package com.example.lenovo.bank_application;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CaluculateBalance extends Activity {
    EditText t1, t2, t3, t4;
    TextView tv;
    Switch s1;
    ToggleButton tb;
    Button b;
    Button back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caluculate);
        t1 = (EditText)findViewById(R.id.t1);
        t2 = (EditText)findViewById(R.id.t2);
        t3 = (EditText)findViewById(R.id.t3);
        t4 = (EditText)findViewById(R.id.t4);
        tv = (TextView)findViewById(R.id.tv);
        s1 = (Switch)findViewById(R.id.s1);
        tb = (ToggleButton)findViewById(R.id.tb);
        b = (Button)findViewById(R.id.b);
        back = (Button)findViewById(R.id.btnback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(CaluculateBalance.this, HomeActivity.class);
                startActivity(back);
            }
        });
    }
    public void DoHandle(View v) {
        try {
            if (!s1.isChecked()) {
                setTitle("Bank Closed");
            }
            int acc = Integer.parseInt("" + t2.getText());
            Integer bal = Integer.parseInt("" + t3.getText());
            Integer amm = Integer.parseInt("" + t4.getText());
            if (s1.isChecked() && tb.isChecked()) {
                setTitle("Amount Deposit");
                bal = bal+amm-5;
                tv.setText("Updated Balance=" + bal);
            } else if (s1.isChecked() && !tb.isChecked() && bal>amm+5 ) {
                setTitle("Amount Withdraw");
                bal = bal-amm-5;
                tv.setText("Updated Balance=" + bal);
            }
            else
            if(s1.isChecked() && !tb.isChecked() && bal<=amm+5){
                setTitle("Amount Withdraw");
                tv.setText("Insufficient Balance");
            }
        } catch (Exception ex) {
            System.out.println(ex);
            tv.setText(ex.getMessage());

        }
    }
}
