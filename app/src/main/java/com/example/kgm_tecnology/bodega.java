package com.example.kgm_tecnology;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class bodega extends AppCompatActivity {

EditText aditCodeProduct,addNameProduct,addValueProduct,addAmountProduct;

Button addBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_categorias);

        aditCodeProduct = findViewById(R.id.aditCodeProduct);
        addNameProduct = findViewById(R.id.addNameProduct);
        addValueProduct = findViewById(R.id.addValueProduct);
        addAmountProduct = findViewById(R.id.addAmountProduct);
        addBtn = findViewById(R.id.addBtn);


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String code, nombre, descripcion, fecha;
                    code =aditCodeProduct.getText().toString();
                    nombre = addNameProduct.getText().toString();
                    descripcion = addValueProduct.getText().toString();
                    fecha = addAmountProduct.getText().toString();

                    if (!code.isEmpty() && !nombre.isEmpty() && !descripcion.isEmpty() && !fecha.isEmpty()){

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        DatabaseReference myRef = database.getReference("Categorias");

                        Map<String, Object> map = new HashMap<>();
                        map.put("Id",aditCodeProduct.getText().toString());
                        map.put("Nombre", addNameProduct.getText().toString());
                        map.put("Descripcion", addValueProduct.getText().toString());
                        map.put("fecha", addAmountProduct.getText().toString());
                        myRef.child(aditCodeProduct.getText().toString()).updateChildren(map);


                        AlertDialog.Builder confirm = new AlertDialog.Builder(bodega.this);
                        confirm.setTitle("Confirmación");
                        confirm.setMessage("¿Desea registrar el producto?");

                        confirm.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                CharSequence text = "Producto registrado con éxito";
                                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                                try {
                                    TimeUnit.SECONDS.sleep(1);
                                }
                                catch (InterruptedException e) {
                                    throw new RuntimeException(e);
                                }



//                            #me lleva a inicio index
                                Intent intent = new Intent(bodega.this, categoria_activity.class);
                                intent.putExtra("valorExtra1", nombre);
                                intent.putExtra("valorExtra2",descripcion);
                                intent.putExtra("valorExtra3", fecha);
//                                Intent intent = new Intent(getApplicationContext(), Menu.class);
                                startActivity(intent);
                            }
                        });
                        confirm.setNegativeButton("Cancelar", null);
                        AlertDialog ventana = confirm.create();
                        ventana.show();
                        limpiar();

                    }else{
                        CharSequence text = "Algunos campos están vacíos o incorrectos.";
                        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){
                    Log.d("Register Message", "Error-> "+e.getMessage());
                }
            }
        });

    }
    private void limpiar(){
        aditCodeProduct.setText("");
        addNameProduct.setText("");
        addValueProduct.setText("");
        addAmountProduct.setText("");
    }
}

