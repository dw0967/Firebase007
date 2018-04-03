package com.example.dw0967.firebaseex;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.os.Bundle;
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

public class Regist extends Activity {

    private EditText emailentry;
    private EditText pass1;
    private Button registbtn2;

    private ProgressDialog reg_prog;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_regist);

        reg_prog = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();

        emailentry = (EditText) findViewById(R.id.Reg_email);
        pass1 = (EditText) findViewById(R.id.pw_1);
        registbtn2 = (Button) findViewById(R.id.reg_btn2);


        registbtn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                String email = emailentry.getText().toString().trim();
                String pswd1 = pass1.getText().toString().trim();


                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(pswd1)){
                   reg_prog.setTitle("Registering");
                   reg_prog.setMessage("Creating account");
                   reg_prog.setCanceledOnTouchOutside(false);
                   reg_prog.show();
                   Reg_User(email, pswd1);
                }
            }
        });
    }

    private void Reg_User(String email, String pswd1){

        mAuth.createUserWithEmailAndPassword(email,pswd1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                   reg_prog.dismiss();
                    Intent go_main2 = new Intent(Regist.this, MainActivity.class);
                    startActivity(go_main2);
                    finish();
                }else{
                   reg_prog.hide();
                    Toast.makeText(Regist.this, "error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
