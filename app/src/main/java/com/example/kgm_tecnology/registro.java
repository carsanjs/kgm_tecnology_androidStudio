package com.example.kgm_tecnology;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class registro  extends AppCompatActivity  {

    TextView singupname, singupemail,singuppassword, codigo;
    Button btnSignup, btnBackLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        this.codigo = findViewById(R.id.codigo);
        this.singupname = findViewById(R.id.signupName);
        this.singupemail = findViewById(R.id.signupEmail);
        this.singuppassword = findViewById(R.id.signupPassword);
//        registrarse
        this.btnSignup = (Button) findViewById(R.id.btnSignup);
        this.btnBackLogin =  (Button) findViewById(R.id.btnLoginBack);


        this.btnBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), iniciarsession.class);
                startActivity(intent);
            }
        });

        btnSignup.setOnClickListener(view ->{

            try {
                String codigoid,name, correo, pass;
                codigoid = codigo.getText().toString();
                name = singupname.getText().toString();
                correo = singupemail.getText().toString();
                pass = singuppassword.getText().toString();



                if (!codigoid.isEmpty() && !name.isEmpty() && !correo.isEmpty() && !pass.isEmpty()){

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("Usuarios");

                    Map<String, Object> map = new HashMap<>();
                    map.put("Codigo",codigo.getText().toString());
                    map.put("Nombre", singupname.getText().toString());
                    map.put("Correo", singupemail.getText().toString());
                    map.put("Pass", singuppassword.getText().toString());
                    myRef.child(codigo.getText().toString()).updateChildren(map);


                    AlertDialog.Builder confirm = new AlertDialog.Builder(this);
                    confirm.setTitle("Aprobación");
                    confirm.setMessage("¿Desea crear cuenta de usuario?");

                    confirm.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            CharSequence text = "Se ha completado el proceso de registro del usuario con éxito.";
                            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                            try {
                                TimeUnit.SECONDS.sleep(1);
                            }
                            catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            Intent intent = new Intent(getApplicationContext(), iniciarsession.class);
                            startActivity(intent);
                        }
                    });
                    confirm.setNegativeButton("Suspender", null);
                    AlertDialog ventana = confirm.create();
                    ventana.show();
                    limpiar();

                }else{
                    CharSequence text = "Existen algunos espacios en blanco o información incorrecta en ciertos campos.";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                Log.d("Mensaje de registro", "Error-----> "+e.getMessage());
            }

        });

    }
    private void limpiar(){
        codigo.setText("");
        singupname.setText("");
        singupemail.setText("");
        singuppassword.setText("");
    }
}
