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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private Button button;
    private EditText Lemail, Lpword;
    private FirebaseAuth mAuth;


    private static final String TAG = "Login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Lemail = findViewById(R.id.Lusername);
        Lpword = findViewById(R.id.Lpword);
        button = findViewById(R.id.Lbutton);

//        button.setOnClickListener(this);
        mAuth = FirebaseAuth.getInstance();
    }

    private void SigninAccount(String email, String pass) {



        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(Login.this, "Sign up successful",Toast.LENGTH_SHORT).show();
                            sharepref();
                            startActivity(new Intent(getApplicationContext(),Afterlogin.class));
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }

                        // ...
                    }
                });
    }

    private void sharepref() {

    }



    public void login(View view) {

        String emailadd = Lemail.getText().toString().trim();
        String passWord=  Lpword.getText().toString().trim();
        if(TextUtils.isEmpty(emailadd)){

            Toast.makeText(this,"Please Enter Email",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(passWord)){
            Toast.makeText(this,"Please Enter Password",Toast.LENGTH_SHORT).show();
        }
        else{

            SigninAccount(emailadd,passWord);
        }


    }
}



