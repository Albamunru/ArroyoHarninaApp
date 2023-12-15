package com.example.albaunidadtres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity4 extends AppCompatActivity {

    private CalendarView miCalendario;
    private EditText miMultilinea;
    private String nombreDocumento;
    private Button botonGuardar;
    private Button botonRecuperar;
    private Button escribir;

private TextView info;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

         miCalendario= findViewById(R.id.calendarView);
         miMultilinea= findViewById(R.id.editTextMultiLine);
         botonGuardar= findViewById(R.id.buttonGuardar);
         botonRecuperar= findViewById(R.id.buttonRecuperar);
         escribir=findViewById(R.id.buttonVer);


         info= findViewById(R.id.textViewInfo);

        Date fechaHora = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        String fechaHoraActual = dateFormat.format(fechaHora);




        //String escribir= String.valueOf(miMultilinea.getText());



         miCalendario.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
             @Override
             public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                 nombreDocumento= dayOfMonth+""+month+""+year+".txt";


                // Toast.makeText(MainActivity4.this,nombreDocumento,Toast.LENGTH_SHORT).show();


             }
         });


escribir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        miMultilinea.setEnabled(true);
    }
});
         botonGuardar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

               escribirDocumento(nombreDocumento, miMultilinea,fechaHoraActual);

                miMultilinea.setText("");
             }
         });


        botonRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombreDocumento == null || nombreDocumento.isEmpty()) {

                    Toast.makeText(getApplicationContext(), "Selecciona una fecha primero", Toast.LENGTH_SHORT).show();
                } else {
                    miMultilinea.setEnabled(false);
                    InputStream in = null;
                    try {
                        in = openFileInput(nombreDocumento);
                        if (in != null) {
                            InputStreamReader tem = new InputStreamReader(in);
                            BufferedReader leer = new BufferedReader(tem);
                            String str;
                            StringBuilder buf = new StringBuilder();
                            while ((str = leer.readLine()) != null) {
                                buf.append(str).append("\n");
                            }
                            miMultilinea.setText("");
                            miMultilinea.setText(buf.toString());
                        }
                    } catch (FileNotFoundException e) {
                        info.setText("No tienes nada en esta fecha");
                        miMultilinea.setText("");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                info.setText("");
                            }
                        }, 2000);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        try {
                            if (in != null) {
                                in.close();
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        });






    }


    private void escribirDocumento(String nombreDocumento, EditText miMultilinea, String fechaHoraActual){
        try {
            OutputStreamWriter archivo= new OutputStreamWriter(openFileOutput(nombreDocumento,Context.MODE_PRIVATE));


            archivo.write(miMultilinea.getText().toString() + "\n" + fechaHoraActual + "\n ---------------------------------------------- \n");

            archivo.flush();
            archivo.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


}