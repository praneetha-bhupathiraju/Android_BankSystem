package com.example.lenovo.bank_application;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



public class HomeActivity extends AppCompatActivity {
    Button caluculateBalance;
    Button fundTransfer;
    Button update;
    Button feedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        caluculateBalance = (Button) findViewById(R.id.btnCaluculateBalance);
        fundTransfer = (Button) findViewById(R.id.btnFundMoney);
        update = (Button)findViewById(R.id.btnUpdate);
        feedback = (Button) findViewById(R.id.btnFeedback);

        caluculateBalance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent caluculateBalance = new Intent(HomeActivity.this, CaluculateBalance.class);
                startActivity(caluculateBalance);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateDetails = new Intent(HomeActivity.this, UpdateActivity.class);
                startActivity(updateDetails);
            }
        });
        fundTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transfer = new Intent(HomeActivity.this, FundTransfer.class);
                startActivity(transfer);
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent feedback = new Intent(HomeActivity.this, Feedback.class);
                startActivity(feedback);
            }
        });

    }
    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You want to logout?");
                alertDialogBuilder.setPositiveButton("No",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                Toast.makeText(HomeActivity.this,"You clicked No button",Toast.LENGTH_LONG).show();
                            }
                        });

        alertDialogBuilder.setNegativeButton("Yes",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(HomeActivity.this, "Logout successfull", Toast.LENGTH_LONG).show();
                Intent logout = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(logout);
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
