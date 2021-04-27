package com.medianet.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.medianet.miniproject.model.User;

public class InscriptionActivity extends AppCompatActivity {


    FirebaseAuth mAuth;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);


        mAuth = FirebaseAuth.getInstance();

        TextInputEditText editEmail = findViewById(R.id.editEmail);
        TextInputEditText editPwd = findViewById(R.id.editPwd);
        TextInputEditText editName = findViewById(R.id.editName);

        findViewById(R.id.btnSingUp).setOnClickListener(view -> {
            try {


                Log.e("ssssss",editEmail.getText().toString());
                mAuth.createUserWithEmailAndPassword(editEmail.getText().toString(), editPwd.getText().toString())
                        .addOnCompleteListener(this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser userFb = mAuth.getCurrentUser();

                                User user = new User();
                                user.setId(userFb.getUid());
                                user.setEmail(editEmail.getText().toString());
                                user.setPassword(editPwd.getText().toString());
                                user.setName(editName.getText().toString());

                                reference
                                        .child("users")
                                        .child(userFb.getUid())
                                        .setValue(user);

                                onBackPressed();
                            } else {
                                Log.e("tttttt",task.getException().getMessage());
                                try {
                                    task.getException().getStackTrace();
                                }catch (Exception e){ }
                            // If sign in fails, display a message to the user.
                                Toast.makeText(this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }
                        });
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(view -> {
            onBackPressed();
        });
    }
}