package com.example.dw0967.firebaseex;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends Activity {


    private Button logoutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);

        logoutbtn = (Button) findViewById(R.id.logout_btn);
        logoutbtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent go_main = new Intent(Login.this, MainActivity.class);
                startActivity(go_main);

            }
        });
    }

}
