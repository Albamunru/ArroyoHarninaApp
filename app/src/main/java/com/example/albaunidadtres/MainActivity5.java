package com.example.albaunidadtres;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.DAY_OF_YEAR, -1);

        long inicio = calendario.getTimeInMillis();
        long fin = System.currentTimeMillis();

        List<UsageStats> miListaUso = tiempoUso.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, inicio, fin);


        long totalMiliSeg = 0;
        for (UsageStats miUso : miListaUso) {
            totalMiliSeg = totalMiliSeg + miUso.getTotalTimeInForeground();
        }




        long horas = TimeUnit.MILLISECONDS.toHours(totalMiliSeg);
        long minutos = TimeUnit.MILLISECONDS.toMinutes(totalMiliSeg) %60;
        long segundos = TimeUnit.MILLISECONDS.toSeconds(totalMiliSeg) %60;


        String tiempoDeUso = "Hoy has usado el movil "+horas+" h "+ minutos+" min "+ segundos+" seg ";

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


        uso.setText(tiempoDeUso);









    }
}