package com.medianet.miniproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.medianet.miniproject.model.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextInputEditText editEmail = findViewById(R.id.editEmail);
        TextInputEditText editPwd = findViewById(R.id.editPwd);

        findViewById(R.id.btnSingUp).setOnClickListener(view -> {
            startActivity(new Intent(this , InscriptionActivity.class));

        });
        findViewById(R.id.btnAuth).setOnClickListener(view -> {
            try {

                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        editEmail.getText().toString(),
                        editPwd.getText().toString()
                ).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        AuthResult result = task.getResult();
                        startActivity(new Intent(this , HomeActivity.class).putExtra("userId",result.getUser().getUid()));
                        finish();
                    } else {
                       Toast.makeText(this,"Error signing in with email link",Toast.LENGTH_LONG).show();
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}