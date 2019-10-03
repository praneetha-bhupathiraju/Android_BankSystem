package com.example.lenovo.bank_application;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mTextUsername;
    EditText mTextPassword;
    Button btnRegister;
    TextView textViewLogin;
    EditText mTextcnfPassword;
    DatabaseHelper db;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
         mTextUsername = (EditText)findViewById(R.id.Edittext_username);
         mTextPassword = (EditText)findViewById(R.id.Edittext_password);
         mTextcnfPassword = (EditText)findViewById(R.id.Edittext_cnfpassword);
         btnRegister = (Button)findViewById(R.id.button_register);
         textViewLogin = (TextView)findViewById(R.id.textviwe_login);
         textViewLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                 startActivity(intent);
             }
         });
         btnRegister.setOnClickListener(this);

    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @Override
    public void onClick(View view) {
        String user = mTextUsername.getText().toString().trim();
        String password = mTextPassword.getText().toString().trim();
        String cnfPassword = mTextcnfPassword.getText().toString().trim();



        if(password.equals(cnfPassword)) {
            long val = db.addUser(user, password);
            if(val > 0) {
                if (user.length() == 0||password.length() == 0|| cnfPassword.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Please eneter all the details", Toast.LENGTH_LONG).show();
                }else if (!isValidEmail(user)) {
                    Toast.makeText(RegisterActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Registration is successfull", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                }
            } else {
                Toast.makeText(RegisterActivity.this, "Registration error", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(RegisterActivity.this, "Passwords does not match", Toast.LENGTH_SHORT).show();
        }

    }
}
