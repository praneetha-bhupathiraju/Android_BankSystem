package com.example.lenovo.bank_application;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity implements android.view.View.OnClickListener {
    SQLiteDatabase db;
    EditText editsearchname,editempname, AccountNo, ContactNo, Address, Branch;
    Button Add, Delete, Modify, View ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        db=openOrCreateDatabase("Bank_Systm", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS updation(Id INTEGER PRIMARY KEY AUTOINCREMENT,Name VARCHAR,AccountNumber VARCHAR,Contact VARCHAR, Address VARCHAR, Branch VARCHAR);");
        Branch = (EditText) findViewById(R.id.Branch);
        Address = (EditText) findViewById(R.id.Address);
        editsearchname = (EditText) findViewById(R.id.esearch);
        editempname = (EditText) findViewById(R.id.fullName);
        AccountNo = (EditText) findViewById(R.id.AccountNumber);
        ContactNo = (EditText) findViewById(R.id.Contact);
        Add = (Button) findViewById(R.id.btnsave);
        Delete= (Button) findViewById(R.id.btndel);
        Modify= (Button) findViewById(R.id.btnupdate);
        View= (Button) findViewById(R.id. btnselect);
        Add.setOnClickListener(this);
        Modify.setOnClickListener(this);
        View.setOnClickListener(this);
    }

    public void msg(Context context,String str)
    {
        Toast.makeText(this,str,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnsave)
        {

            if(editempname.getText().toString().trim().length()==0||
                    AccountNo.getText().toString().trim().length()==0||
                    ContactNo.getText().toString().trim().length()==0||
                    Address.getText().toString().trim().length()==0||
                    Branch.getText().toString().trim().length()==0)
            {
                msg(this, "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO updation(Name,AccountNumber,Contact,Address,Branch)VALUES('"+ editempname.getText()+"','"+ AccountNo.getText()+ "','"+    ContactNo.getText()+ "','" + Address.getText()+"','"+ Branch.getText()+"');");
            msg(this, "Record added");
        }

        else if(v.getId()==R.id.btnupdate)
        {

            if(editsearchname.getText().toString().trim().length()==0)
            {
                msg(this, "Enter Name");
                return;
            }
            Cursor c=db.rawQuery("SELECT * FROM updation WHERE Name='"+ editsearchname.getText()+"'", null);
            if(c.moveToFirst()) {
                db.execSQL("UPDATE updation  SET Name ='"+ editempname.getText()+"', AccountNumber='"+ AccountNo.getText()+"',Contact='"+  ContactNo.getText()+"',Address='" + Address.getText()+"',Branch='" +Branch.getText()+"' WHERE Name ='"+editsearchname.getText()+"'");
                msg(this, "Record Modified");
            }
            else
            {
                msg(this, "Invalid Name");
            }
        }

        else if (v.getId() == R.id.btnselect)
        {
            Cursor c=db.rawQuery("SELECT * FROM updation", null);
            if(c.getCount()==0)
            {
                msg(this, "No records found");
                return;
            }
            StringBuffer buffer=new StringBuffer();
            while(c.moveToNext())
            {
                buffer.append(" Name: "+c.getString(1)+"\n");
                buffer.append("AccountNo: "+c.getString(2)+"\n\n");
                buffer.append("Contact: "+c.getString(3)+"\n\n");
                buffer.append("Address: "+c.getString(4)+"\n\n");
                buffer.append("Branch: "+c.getString(5)+"\n\n");
            }
            msg(this, buffer.toString());
        }

    }
}