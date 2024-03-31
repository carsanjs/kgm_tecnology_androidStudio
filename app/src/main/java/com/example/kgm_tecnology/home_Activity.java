package com.example.kgm_tecnology;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class home_Activity  extends AppCompatActivity {

    Button homeLogin,homesingup;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);



       this.homeLogin = (Button)findViewById(R.id.homeLogin);
       this.homesingup = (Button)findViewById(R.id.homeSignup);

       this.homeLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), iniciarsession.class);
               startActivity(intent);
           }
       });

       this.homesingup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(getApplicationContext(), registro.class);
               startActivity(intent);
           }
       });
    }




}
