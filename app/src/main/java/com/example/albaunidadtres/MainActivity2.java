package com.example.albaunidadtres;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
   private MyRecyclerViewAdapter adapter;
    private  RepositorioPaises repositorioPaises;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        repositorioPaises=new RepositorioPaises();
      Button miBotonDetalle=findViewById(R.id.buttonDetalle);



        RecyclerView recyclerView = findViewById(R.id.ListadoRecicle);



        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyRecyclerViewAdapter(this,repositorioPaises.getMiListaPaises());
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        miBotonDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(),MainActivity3.class);

                startActivity(intent);
            }
        });




    }




    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "Has seleccionado: " + adapter.getItem(position), Toast.LENGTH_LONG).show();
    }



    public void cambioPantalla(Button boton){
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);

            }
        });

    }



}