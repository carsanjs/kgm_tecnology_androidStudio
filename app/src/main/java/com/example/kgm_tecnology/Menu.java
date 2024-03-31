package com.example.kgm_tecnology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    Button bntmenuregister,bntbodega, btnedit, btndelete,bntcamera, bntgps;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);


        this.bntmenuregister = (Button)findViewById(R.id.btnMenuRegister);
        this.btnedit = (Button)findViewById(R.id.btnedit);
        this.bntbodega = (Button)findViewById(R.id.bntbodega);
        this.btndelete = (Button)findViewById(R.id.btndelete);
        this.bntcamera = (Button)findViewById(R.id.btncamara);
        this.bntgps = (Button)findViewById(R.id.btngps);



        this.bntcamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewCamara.class);
                startActivity(intent);
            }
        });

        this.bntgps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ViewGps.class);
                startActivity(intent);
            }
        });


        this.bntmenuregister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), agregarproducto.class);
                        startActivity(intent);
                    }
        });


        this.btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), editar_producto.class);
                startActivity(intent);
            }
        });


        this.bntbodega.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), bodega.class);
                startActivity(intent);
            }
        });


        this.btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), eliminar_producto.class);
                startActivity(intent);
            }
        });
    }


}
