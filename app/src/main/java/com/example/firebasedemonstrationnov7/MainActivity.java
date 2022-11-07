package com.example.firebasedemonstrationnov7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name, age, phone, height;
    TextView nameT, ageT, phoneT, heightT;
    Button save, fetch;
    DatabaseReference reff;
    Member member  ;

    long maxid = 0;

    ListView lv;
    ArrayList<String> arrayList = new ArrayList<>();
    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.name);
        age= findViewById(R.id.age);
        phone= findViewById(R.id.phone);
        height= findViewById(R.id.height);

        nameT= findViewById(R.id.name);
        ageT= findViewById(R.id.age);
        phoneT= findViewById(R.id.phone);
        heightT= findViewById(R.id.height);

        save= findViewById(R.id.save);
        fetch= findViewById(R.id.fetch);
        member= new Member();

        lv= findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(arrayAdapter);


        reff= FirebaseDatabase.getInstance().getReference().child("Member");

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid= (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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
                //reff.push().setValue(member);
                reff.child(String.valueOf(maxid + 1)).setValue(member);
                Toast.makeText(getApplicationContext(), "Data is inserted", Toast.LENGTH_LONG).show();

            }
        });

        fetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference("Member");
                reff.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                        String value = snapshot.getValue(Member.class).toString();
                        arrayList.add(value);
                        arrayAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
//                reff = FirebaseDatabase.getInstance().getReference().child("Member").child("3");
//                reff.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        String name = snapshot.child("name").getValue().toString();
//                        String age = snapshot.child("age").getValue().toString();
//                        String phone = snapshot.child("phone").getValue().toString();
//                        String height = snapshot.child("height").getValue().toString();
//
//                        nameT.setText(name);
//                        ageT.setText(age);
//                        phoneT.setText(phone);
//                        heightT.setText(height);
//
//
//                    }
                });
            }
        });
    }
}