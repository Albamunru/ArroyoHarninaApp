package com.example.albaunidadtres;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity5 extends AppCompatActivity {

    private TextView uso;
    private ImageView contento;
    private ImageView normal;
    private ImageView triste;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        uso= findViewById(R.id.textViewUso);
        ImageView temporizador= findViewById(R.id.imageView2);
        Glide.with(this).asGif().load(R.drawable.tiempo).into(temporizador);
        contento=findViewById(R.id.imageViewContento);
        normal=findViewById(R.id.imageViewnormal);
        triste=findViewById(R.id.imageViewenfadado);

        //Creo el hilo

        Handler hilo= new Handler();
        Runnable runnable;



        // Este codigo te lo comento porque he intentado dar permisos desde la app pero esta super
        // restringida salvo a las app que te vienen ahi por defevto no he conseguido
        //obtener permisos de otras app instaladas externamente.
        /*Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);*/

        Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
        startActivity(intent);


        UsageStatsManager tiempoUso = (UsageStatsManager) getSystemService(Context.USAGE_STATS_SERVICE);
        SharedPreferences preferences = getSharedPreferences("Tiempo Uso Preferencess", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        long tiempoInicioDiaAnterior = preferences.getLong("Tiempo del dia Antes", -1);
        long tiempoUsoHoy = preferences.getLong("Tiempo de hoy", 0);


        //Aqui es a la hora que seteo los valores que estaban antes guardado para poner el comtador a 0 a las 00:00:00h

        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(System.currentTimeMillis());

        calendario.set(Calendar.HOUR_OF_DAY, 0);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);
        calendario.set(Calendar.MILLISECOND, 0);

        long inicioHoy = calendario.getTimeInMillis();

        //Aqui reinicio el  contador para un nuevo día era la parte que me faltaba
        if (tiempoInicioDiaAnterior < inicioHoy) {

            editor.putLong("Tiempo Inicio DiaAnterior", inicioHoy);
            editor.putLong("TiempoUsoHoy", 0);
            editor.apply();
        }

        long fin = System.currentTimeMillis();

        List<UsageStats> miListaUso = tiempoUso.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, inicioHoy, fin);

        //Me pide que lo convierta a un array
        final long[] totalMiliSeg = {0};
        for (UsageStats miUso : miListaUso) {
            totalMiliSeg[0] =  totalMiliSeg[0] + miUso.getTotalTimeInForeground();
        }


        editor.putLong("Tiempo Uso Hoy", totalMiliSeg[0]);
        editor.apply();


        runnable=new Runnable() {
            @Override
            public void run() {
                //Meto el proceso del contador del temporizador dentro del hilo que lo que se irá actualizando cada segundo
                //por lo cual se me va a ir mostrando en tiempo real en el movil
                totalMiliSeg[0] = totalMiliSeg[0] +1000;
                long horas = TimeUnit.MILLISECONDS.toHours(totalMiliSeg[0]);
                long minutos = TimeUnit.MILLISECONDS.toMinutes(totalMiliSeg[0]) % 60;
                long segundos = TimeUnit.MILLISECONDS.toSeconds(totalMiliSeg[0]) % 60;

                String tiempoDeUso = "Hoy has usado el móvil " + horas + " h " + minutos + " min " + segundos + " seg";
                uso.setText(tiempoDeUso);

                if (horas <= 2) {
                    contento.setVisibility(View.VISIBLE);
                    normal.setVisibility(View.INVISIBLE);
                    triste.setVisibility(View.INVISIBLE);
                } else if (horas > 2 && horas < 3) {
                    contento.setVisibility(View.INVISIBLE);
                    normal.setVisibility(View.VISIBLE);
                    triste.setVisibility(View.INVISIBLE);
                } else {
                    contento.setVisibility(View.INVISIBLE);
                    normal.setVisibility(View.INVISIBLE);
                    triste.setVisibility(View.VISIBLE);
                }
                hilo.postDelayed(this,1000);
            }
        };
        //Este es el que actualiza el tiempo osea el temporizador que actualiza el tiempo le doy un seg y cada seg se actualiza
        //Por lo cual hace que se vaya cambiando en tiempo de ejecución
        hilo.postDelayed(runnable,1000);
















    }
}