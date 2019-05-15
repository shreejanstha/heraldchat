package com.example.heraldchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.w3c.dom.Text;

import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity  {

    private FirebaseAuth mAuth;
    private TextView logintext;
    private Button Rbutton;
    private EditText Remail,Rpword,Rrpword;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logintext =findViewById(R.id.login);
        logintext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

        Remail = findViewById(R.id.registeremail);
        Rpword= findViewById(R.id.registerpassword);
        Rrpword= findViewById(R.id.registerpassword2);
        Rbutton= findViewById(R.id.registerbutton);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        ////////////////////
        FirebaseMessaging fm = FirebaseMessaging.getInstance();
        fm.send(new RemoteMessage.Builder(0 + "@fcm.googleapis.com")
                .setMessageId(Integer.toString(1))
                .addData("my_message", "Hello World")
                .addData("my_action","SAY_HELLO")
                .build());
    }

    private void createAccount(final String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(MainActivity.this, "Sign up successful",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),Login.class));
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                           // updateUI(null);
                        }

                        // ...
                    }
                });

    }
    public void Signup(View view) {

        String email = Remail.getText().toString().trim();
        String pass=  Rpword.getText().toString().trim();
        String confirm_password= Rrpword.getText().toString().trim();

        //check for password confirmation

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"Please enter Email!",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass)){

            Toast.makeText(this,"Please enter Password!",Toast.LENGTH_SHORT).show();
            return;
        }
        if(pass.length()<6){

            Toast.makeText(this,"Password too short",Toast.LENGTH_SHORT).show();


        }
        if(TextUtils.isEmpty(confirm_password)){

            Toast.makeText(this,"Please Re-enter password !",Toast.LENGTH_SHORT).show();
            return;
        }

        //check if password and re-password is same or not

        if(pass.equals(confirm_password)){

            createAccount(email,pass);

        }
        else {
            Toast.makeText(this,"Password donot match !!",Toast.LENGTH_SHORT).show();

        }
    }


    public void signin(View view) {

        Intent intent = new Intent(this,Login.class);
        }
    }

