package com.example.andronmaping;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class pingActivity extends AppCompatActivity {
    ApiManager apiManager;
    String url = "TO BE REPLACED WITH THE API PUBLIC IP";
    EditText editText;
    EditText output;
    Button btn;
    String ipAddr;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);
        apiManager = new ApiManager(url);
        editText = findViewById(R.id.enter);
        output = findViewById(R.id.outp);
        output.setEnabled(false);
        btn = findViewById(R.id.button);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                output.setText("Loading ...");
                ipAddr = editText.getText().toString();
                apiManager.post(ipAddr,"/ping",new ApiCallback(){
                    @Override
                    public void onSuccess(String response){
                        output.setText(response);
                    }
                    @Override
                    public void onError(String errorMessage){
                        output.setText(errorMessage);
                    }
                });
            }
        });
    }
}
