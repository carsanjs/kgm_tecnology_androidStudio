package com.example.kgm_tecnology;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class eliminar_producto  extends AppCompatActivity {

    private EditText deletCodeProduct,deletNameProduct,deletValueProduct,deletAmountProduct,deletPriceProduct,deletecategory;
    private Button  btneliminar,bntcancel;
    private  ImageView btnbuscar;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        this.deletCodeProduct = findViewById(R.id.deletCodeProduct);
        this.deletNameProduct = findViewById(R.id.deletNameProduct);
        this.deletValueProduct = findViewById(R.id.deletValueProduct);
        this.deletAmountProduct = findViewById(R.id.deletAmountProduct);
        this.deletPriceProduct = findViewById(R.id.deletPriceProduct);
        this.deletecategory = findViewById(R.id.deletcategoryProduct);

        this.btnbuscar = (ImageView) findViewById(R.id.btndeletbuscar);

        this.bntcancel = (Button) findViewById(R.id.btncancel);
        this.btneliminar = findViewById(R.id.bntdeleteliminar);


        this.bntcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnbuscar.setOnClickListener(View ->{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Productos");
            myRef.child(deletCodeProduct.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (!deletCodeProduct.getText().toString().isEmpty() && snapshot.exists()) {
                        String Nombre_producto = snapshot.child("Nombre Producto").getValue().toString();
                        String Cantidad_Disponible = snapshot.child("Cantidad Disponible").getValue().toString();
                        String Precio_Estimado = snapshot.child("Precio Estimado").getValue().toString();
                        String Valor_Unitario = snapshot.child("Valor Unitario").getValue().toString();
                        String categoria = snapshot.child("categoria").getValue().toString();


                        deletNameProduct.setText(Nombre_producto);
                        deletValueProduct.setText(Valor_Unitario);
                        deletAmountProduct.setText(Cantidad_Disponible);
                        deletPriceProduct.setText(Precio_Estimado);
                        deletecategory.setText(categoria);

                    }

                    if(deletCodeProduct.getText().toString().isEmpty()){
                        Context context = getApplicationContext();
                        String mensaje = "Por favor, Introduzca datos";
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    }

                    if(!snapshot.exists()) {
                        limpiar();
                        Context context = getApplicationContext();
                        String mensaje = "Error, Producto No encontrado, Registralo";
                        Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    String mensaje = "Cancelada la busqueda de datos. Error: " + error.getMessage();
                    Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_SHORT).show();
                }
            });
        });

        this.btneliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = deletCodeProduct.getText().toString();

                if (code.isEmpty()){
                    CharSequence text = "Por favor ingresa los campos válido antes de continuar.";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                }else{
                    DatabaseReference db = FirebaseDatabase.getInstance().getReference("Productos");
                    db.child(code).removeValue();
                    CharSequence text = "Producto eliminado sastifactoriamente";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    limpiar();
                }
            }
        });
    }
    private void limpiar(){
        deletCodeProduct.setText("");
        deletNameProduct.setText("");
        deletValueProduct.setText("");
        deletAmountProduct.setText("");
        deletPriceProduct.setText("");
        deletecategory.setText("");
    }
}
