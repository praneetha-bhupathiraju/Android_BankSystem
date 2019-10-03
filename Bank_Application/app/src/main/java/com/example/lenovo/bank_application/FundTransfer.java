package com.example.lenovo.bank_application;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FundTransfer extends AppCompatActivity implements View.OnClickListener{
    EditText Account, Amount, Date;
    Button Ft, Vt;
    SQLiteDatabase db;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        Date = (EditText) findViewById(R.id.Date);
        Account = (EditText) findViewById(R.id.AccntNo);
        Amount = (EditText) findViewById(R.id.Amount);
        Ft = (Button) findViewById(R.id.btnDeposit);
        Vt = (Button) findViewById(R.id.btnVt);
        db=openOrCreateDatabase("Bank_Systm", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS transactions(Id INTEGER PRIMARY KEY AUTOINCREMENT, Account VARCHAR, Amount VARCHAR, Date VARCHAR);");
        Ft.setOnClickListener(this);
        Vt.setOnClickListener(this);

    }
    public void msg(Context context,String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }
    public void onClick(View v) {
        if (v.getId() == R.id.btnDeposit) {
            if (Account.getText().toString().trim().length() == 0 ||
                    Amount.getText().toString().trim().length() == 0 ||
                    Date.getText().toString().trim().length()==0) {
                msg(this, "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO transactions(Account, Amount, Date)VALUES('" + Account.getText() + "','" + Amount.getText() + "','" + Date.getText() + "');");
            msg(this, "Transaction Successfull!");
        }
        if (v.getId() == R.id.btnVt)
        {
            Cursor c=db.rawQuery("SELECT * FROM transactions", null);
            if(c.getCount()==0)
            {
                msg(this, "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext()) {
                buffer.append(" Account " + c.getString(1) + "\n");
                buffer.append("Amount: " + c.getString(2) + "\n\n");
                buffer.append("Date: " + c.getString(3)+"\n\n");
            }
            msg(this, buffer.toString());
        }

    }

}
