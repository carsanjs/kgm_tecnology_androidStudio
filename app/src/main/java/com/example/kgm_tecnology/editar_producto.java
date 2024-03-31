package com.example.kgm_tecnology;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;
import java.util.Map;

public class editar_producto extends AppCompatActivity {

    EditText uptCategory, uptCode,uptName, uptAmount, uptPrice, uptValue;
    Button uptCancelBtn, uptUpdateBtn;
    private FirebaseAuth mAuth;
    private StorageReference storageReference;
    private FirebaseFirestore databasefirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actualizar_activity);

        databasefirestore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();


        this.uptCategory = findViewById(R.id.uptCategory);
        this.uptCode = findViewById(R.id.uptCode);
        this.uptName = findViewById(R.id.uptName);
        this.uptAmount = findViewById(R.id.uptAmount);
        this.uptPrice = findViewById(R.id.uptPrice);
        this.uptValue = findViewById(R.id.uptValue);


        this.uptUpdateBtn = (Button) findViewById(R.id.uptUpdateBtn);
        this.uptCancelBtn = (Button) findViewById(R.id.bntcancel);

        this.uptCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        uptUpdateBtn.setOnClickListener(view ->  {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Productos");
            myRef.child(uptCode.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("Codigo",uptCode.getText().toString());
                    map.put("Nombre Producto",uptName.getText().toString());
                    map.put("Valor Unitario", uptValue.getText().toString());
                    map.put("Cantidad Disponible", uptAmount.getText().toString());
                    map.put("Precio Estimado",uptPrice.getText().toString());
                    map.put("categoria",uptCategory.getText().toString());
                    myRef.child(uptCode.getText().toString()).updateChildren(map);

                    Context context = getApplicationContext();
                    String mensaje = "Su producto se actualizc con exito.";
                    Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    Limpiar();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    String mensaje = "Cancelar, Busqueda de datos. Error: " + error.getMessage();
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
    private void Limpiar(){
        uptCode.setText("");
        uptName.setText("");
        uptValue.setText("");
        uptAmount.setText("");
        uptPrice.setText("");
        uptCategory.setText("");


    }
}
