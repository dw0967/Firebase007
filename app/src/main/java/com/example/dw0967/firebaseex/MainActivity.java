package com.example.dw0967.firebaseex;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends Activity {

    private FirebaseAuth mAuth;

    private Button loginBtn;
    private Button RegBtn;

    private EditText loginName;
    private EditText loginPw;

    private ProgressDialog log_prog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        loginBtn = (Button) findViewById(R.id.Firebase_btn);
        RegBtn = (Button) findViewById(R.id.Reg_btn);

        log_prog = new ProgressDialog( this);

        loginName = (EditText) findViewById(R.id.db_user);
        loginPw = (EditText) findViewById(R.id.db_pw);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = loginName.getText().toString().trim();
                String pw = loginPw.getText().toString().trim();

                if(!TextUtils.isEmpty(name) || !TextUtils.isEmpty(pw)){
                    log_prog.setTitle("Logging In");
                    log_prog.setMessage("Checking Information");
                    log_prog.setCanceledOnTouchOutside(false);
                    log_prog.show();
                    log_User(name, pw);
                }

            }
        });

        RegBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goregist = new Intent(MainActivity.this, Regist.class);
                startActivity(goregist);

            }
        });

    }

    private void log_User(String name, String pw) {
            mAuth.signInWithEmailAndPassword(name, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Intent gologin = new Intent(MainActivity.this, Login.class);
                        startActivity(gologin);
                        finish();
                    } else {
                        log_prog.hide();
                        Toast.makeText(MainActivity.this, "error", Toast.LENGTH_LONG).show();
                    }
                }
            });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if(currentUser != null){
            Intent startIntent = new Intent(MainActivity.this, Login.class);
            startActivity(startIntent);
            finish();
        }
    }
}
