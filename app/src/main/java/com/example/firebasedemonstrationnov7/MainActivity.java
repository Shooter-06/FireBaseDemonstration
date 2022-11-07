package com.example.firebasedemonstrationnov7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    EditText name, age, phone, height;

    Button save;
    DatabaseReference reff;
    Member member  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.name);
        age= findViewById(R.id.age);
        phone= findViewById(R.id.phone);
        height= findViewById(R.id.height);

        save= findViewById(R.id.save);

        member= new Member();

        reff= FirebaseDatabase.getInstance().getReference().child("Member");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int ageEmployee = Integer.parseInt(age.getText().toString().trim());
                float height= Float.parseFloat(age.getText().toString().trim());
                Long ph = Long.parseLong(phone.getText().toString().trim());

                member.setName(name.getText().toString());
                member.setAge(ageEmployee);
                member.setHeight(height);
                member.setPhone(ph);

                reff.push().setValue(member);
                Toast.makeText(getApplicationContext(), "Data is inserted", Toast.LENGTH_LONG).show();

            }
        });

    }
}