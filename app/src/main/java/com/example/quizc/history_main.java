package com.example.quizc;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class history_main extends AppCompatActivity {


    private ArrayList<score> users;
    private FirebaseAuth mauth;
    private DatabaseReference mdref;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dropdown);
        getSupportActionBar().hide();
        mdref= FirebaseDatabase.getInstance().getReference();
        RecyclerView chatrecycler=findViewById(R.id.recyclerviewHA);
        chatrecycler.setLayoutManager(new LinearLayoutManager(this));
        users=new ArrayList<>();
        adapter a=new adapter(this,users);
        chatrecycler.setAdapter(a);
        Singleton b=Singleton.getInstance();
        String name=b.getData();
        mdref.child("HISTORY").child(name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                users.clear();
                for(DataSnapshot postsnapshot :snapshot.getChildren()){
                   score m=postsnapshot.getValue(score.class);
                    Log.d(TAG, postsnapshot.toString());
                    Log.d(TAG, m.s+" "+m.genre);

                    users.add(m);
                }

            }
                a.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}