package com.example.andronmaping;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class nmapActivity extends AppCompatActivity {
    ApiManager apiManager;
    String url = "TO BE REPLACED WITH THE API PUBLIC IP";
    EditText editText;
    EditText output;
    Button btn;
    DateFormat df;
    String ipAddr,pattern,timeStamp;
    FirebaseAuth auth;
    Date today;
    FirebaseUser user;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nmap);
        apiManager = new ApiManager(url);
        editText = findViewById(R.id.enter);
        output = findViewById(R.id.outp);
        output.setEnabled(false);
        ipAddr = "";
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
                if(ipAddr.length() == 0){
                    Toast.makeText(nmapActivity.this, "Empty field : @IP", Toast.LENGTH_SHORT).show();
                    return;
                }
                apiManager.post(ipAddr,"/scan",new ApiCallback(){
                    @Override
                    public void onSuccess(String response){
                        output.setText(response);
                        pattern = "MM/dd/yyyy HH:mm:ss";
                        df = new SimpleDateFormat(pattern);
                        today = Calendar.getInstance().getTime();
                        timeStamp = df.format(today);
                        firebaseDatabase = FirebaseDatabase.getInstance("TO BE REPLACED WITH THE FIREBASE BATABASE LINK");
                        reference = firebaseDatabase.getReference().child("history");
                        History history = new History(user.getEmail(),timeStamp,response);
                        reference.child(user.getUid()).child(timeStamp.replaceAll("/","")).setValue(history);
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

