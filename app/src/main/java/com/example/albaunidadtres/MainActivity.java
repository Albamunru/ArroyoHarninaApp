package com.example.albaunidadtres;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Button miBotonWeb;
    private Button botonContextual;
    private Button miBotonMoodle;
    private Button miBotonDiversidad;
    private Spinner miSpinner;
    private Context context=this;
  private RepositorioOpciones repositorioOpciones;




   // private View item;




   
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        miSpinner=findViewById(R.id.spinnerElegir);

        repositorioOpciones= new RepositorioOpciones();

        darClick();




        botonContextual = findViewById(R.id.buttonContextual);
        miBotonWeb = findViewById(R.id.buttonAceptar);
        miBotonMoodle=findViewById(R.id.buttonMoodle);
        miBotonDiversidad=findViewById(R.id.buttonDiversidad);

        registerForContextMenu(botonContextual);













    miBotonDiversidad.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent= new Intent(v.getContext(),MainActivity2.class);
            startActivity(intent);

        }
    });




        miBotonWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                paginaWebInstituto();


            }
        });


        miBotonMoodle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paginaWebMoodle();
            }
        });




    }

    private void paginaWebInstituto() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://iesarroyoharnina.educarex.es/"));
        startActivity(intent);
    }private void paginaWebRayuela() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://rayuela.educarex.es/modulo_acceso/controlador.rayuela"));
        startActivity(intent);
    }

    private void paginaWebMoodle() {
        Intent intent= new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.iesarroyoharnina.es/moodle/login/index.php"));
        startActivity(intent);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menudetalles, menu);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Object texto= adapterView.getSelectedItem();
        String cadena=String.valueOf(texto);


        switch (cadena){

            case "Rayuela":

               paginaWebRayuela();



                break;
            case "Enviar e-mail":

            enviarEmailConGmail();



                break;
            case "Llamar al centro":
                llamarAlCentro();

                break;
            case "Calendario":
                Intent intent= new Intent(view.getContext(), MainActivity4.class);
                startActivity(intent);

                break;


            case "Info":
                informacionMaps();

                break;
            default:
               // Toast.makeText(this,"Elija una opción válida",Toast.LENGTH_LONG).show();
                break;



        }






    }





    public void enviarEmailConGmail() {
        String[] direccionesEmail = {"ies.arroyoharnina@edu.juntaex.es"};
        String asunto = "Diploma ";
        String cuerpoMensaje = "Buenos días me pongo en contacto con ustedes para recoger el título de 2º DAM";

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, direccionesEmail);
        intent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        intent.putExtra(Intent.EXTRA_TEXT, cuerpoMensaje);


        intent.setPackage("com.google.android.gm");

        try {
            startActivity(intent);
        } catch (android.content.ActivityNotFoundException ex) {

            Toast.makeText(this, "La aplicación Gmail no está instalada.", Toast.LENGTH_SHORT).show();
        }
    }







    private void informacionMaps() {
        Intent intento= new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.google.es/maps/place/IES+Arroyo+Harnina/@38.677343,-6.4139673,17z/data=!3m1!4b1!4m6!3m5!1s0xd16a787ccd22bab:0x72d00fd1ceca9ea3!8m2!3d38.6773388!4d-6.4113924!16s%2Fg%2F1hc2x1qqm?entry=ttu"));
        startActivity(intento);
    }

    private void llamarAlCentro() {
        Intent intento= new Intent(Intent.ACTION_VIEW,Uri.parse("tel:924017778"));
        startActivity(intento);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    private void darClick(){





        miSpinner.setOnItemSelectedListener(this);
        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,repositorioOpciones.getRepositorioOpciones());
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        miSpinner.setAdapter(adaptador);

    }









}