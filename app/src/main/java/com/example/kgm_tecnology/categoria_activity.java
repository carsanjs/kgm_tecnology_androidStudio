package com.example.kgm_tecnology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class categoria_activity extends AppCompatActivity {
    Button bnt_agg_cate;
    ImageView imageView10;
    TextView textname,textfecha,descrip;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria_activity);

        textname = findViewById(R.id.textname);
        textfecha = findViewById(R.id.textfecha);
        descrip = findViewById(R.id.descrip);

        bnt_agg_cate = findViewById(R.id.bnt_agg_cate);
        imageView10 = findViewById(R.id.imageView10);

        Intent intent = getIntent();
        String name = intent.getStringExtra("valorExtra1");
        String descripcion = intent.getStringExtra("valorExtra2");
        String fecha = intent.getStringExtra("valorExtra3");

        textname.setText(String.valueOf(name));
        textfecha.setText(String.valueOf(fecha));
        descrip.setText(String.valueOf(descripcion));

        bnt_agg_cate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(categoria_activity.this, bodega.class));
            }
        });

        imageView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(categoria_activity.this, Menu.class));
            }
        });
    }
}
