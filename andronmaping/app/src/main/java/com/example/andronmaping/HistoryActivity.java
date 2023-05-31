package com.example.andronmaping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistoryActivity extends AppCompatActivity {

    TextView textView;
    FirebaseUser user;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        textView = findViewById(R.id.histText);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();
        }

        firebaseDatabase = FirebaseDatabase.getInstance("TO BE REPLACED WITH THE FIREBASE DATABASE LINK");
        firebaseDatabase.getReference().child("history/"+user.getUid()).
                addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String str = "-----------------------------------\n\n";
                        for (DataSnapshot dataSnap : dataSnapshot.getChildren()) {
                            History obj = dataSnap.getValue(History.class);
                            str = str + "User : " + obj.getUsername()+"\n\nDate & Time : "+obj.getTimeStamp()+"\n\n"+obj.getContent()+"\n-----------------------------------\n\n";
                        }
                        textView.setText(str);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(HistoryActivity.this, "Error occured!! \n"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

    }
}