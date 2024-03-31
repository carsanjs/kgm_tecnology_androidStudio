package com.example.kgm_tecnology;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

public class agregarproducto extends AppCompatActivity {
    EditText addcategoryproduct,addpriceproduct,addamountproduct,addvalueproduct,addCodeProduct,addNameProduct;
    Button addbtn, bntcancel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_activity);

        this.addcategoryproduct = findViewById(R.id.addCategoryProduct);
        this.addpriceproduct = findViewById(R.id.addPriceProduct);
        this.addamountproduct = findViewById(R.id.addAmountProduct);
        this.addvalueproduct = findViewById(R.id.addValueProduct);
        this.addCodeProduct = findViewById(R.id.aditCodeProduct);
        this.addNameProduct = findViewById(R.id.addNameProduct);

        this.addbtn = (Button) findViewById(R.id.addBtn);
        this.bntcancel = (Button) findViewById(R.id.addCancelBtn);

//        #botones de navegacion
        this.bntcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




        addbtn.setOnClickListener(view ->{
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Productos");

            if(addCodeProduct.getText().toString().isEmpty() && addNameProduct.getText().toString().isEmpty() && addcategoryproduct.getText().toString().isEmpty() && addpriceproduct.getText().toString().isEmpty() && addamountproduct.getText().toString().isEmpty()  && addvalueproduct.getText().toString().isEmpty()){

                CharSequence text = "Error!, no pudo guardar el producto.";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();

            }
            else{

                Map<String, Object> map = new HashMap<>();
                map.put("Codigo",addCodeProduct.getText().toString());
                map.put("Nombre Producto",addNameProduct.getText().toString());
                map.put("Valor Unitario", addvalueproduct.getText().toString());
                map.put("Cantidad Disponible", addamountproduct.getText().toString());
                map.put("Precio Estimado",addpriceproduct.getText().toString());
                map.put("categoria",addcategoryproduct.getText().toString());
                myRef.child(addCodeProduct.getText().toString()).updateChildren(map);

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
//                            #me lleva a al menu
                        Intent intent = new Intent(getApplicationContext(), Menu.class);
                        startActivity(intent);
                    }
                });
                confirm.setNegativeButton("Suspender", null);
                AlertDialog ventana = confirm.create();
                ventana.show();
                limpiar();


            }
        });

    }
    private void limpiar(){
        addCodeProduct.setText("");
        addNameProduct.setText("");
        addvalueproduct.setText("");
        addamountproduct.setText("");
        addpriceproduct.setText("");
        addcategoryproduct.setText("");
    }

}
