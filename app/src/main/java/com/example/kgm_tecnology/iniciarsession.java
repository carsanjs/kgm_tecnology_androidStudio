package com.example.kgm_tecnology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class iniciarsession  extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button btnLogin, btncambiopass;
    ImageView btnfacebook;



    private DatabaseReference database;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sigin_activity);

        mAuth = FirebaseAuth.getInstance();
        this.loginEmail = (EditText) findViewById(R.id.loginEmail);
        this.loginPassword = (EditText) findViewById(R.id.loginPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btncambiopass = (Button) findViewById(R.id.btncambiopass);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myusers = database.getReference("Usuarios");

                try {
                    //String email = loginEmail.getText().toString().trim();
                    //String password = loginPassword.getText().toString().trim();

                    if (loginEmail.getText().toString().isEmpty() && loginPassword.getText().toString().isEmpty()) {

                        CharSequence text = "CAMPOS VACIOS.";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                    }else{

                        myusers.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                                    if (loginEmail.getText().toString().equals(snapshot.child("Correo").getValue()) && loginPassword.getText().toString().equals(snapshot.child("Pass").getValue())) {

                                        CharSequence text = "INICIO CORRECTAMENTE!!";
                                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), Menu.class);
                                        startActivity(intent);
                                        limpiaredittext();
                                        return;
                                    }

                                }
                                CharSequence text = "Usuario o contraseña incorrecta.";
                                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });

                    }
                }
                catch (Exception e){
                    System.out.print(e.getMessage());
                }
            }
        });


        btncambiopass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(iniciarsession.this, cambiopass.class));
                }
            });
        }

        private void limpiaredittext() {
            loginEmail.setText("");
            loginPassword.setText("");
        }
    }