package com.roberto1.calculadoraedad;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.PeriodicSync;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    Button ano,hoy,calcula;
    TextView resultado;
    DatePickerDialog.OnDateSetListener dateSetListener1, dateSetListener2;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ano = findViewById(R.id.ano);
        hoy = findViewById(R.id.hoy);
        calcula = findViewById(R.id.calcula);
        resultado = findViewById(R.id.resultado);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(Calendar.getInstance().getTime());
        hoy.setText(date);

        ano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener1, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date  = day + "/" + month + "/" + year;
                ano.setText(date);
            }
        };

        hoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, dateSetListener2, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        dateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month+1;
                String date  = day + "/" + month + "/" + year;
                hoy.setText(date);
            }
        };
            calcula.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String sDate = ano.getText().toString();
                    String eDate = hoy.getText().toString();
                    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd/MM/yyyy");

                    try {
                        Date date1 = simpleDateFormat1.parse(sDate);
                        Date date2 = simpleDateFormat1.parse(eDate);

                        long startDate = date1.getTime();
                        long endDate = date2.getTime();

                        if(startDate <= endDate){
                             Period period = new Period(startDate, endDate, PeriodType.yearMonthDay());
                            int years = period.getYears();
                            int months = period.getMonths();
                            int days = period.getDays();

                            resultado.setText("Tu edad es "+years+ " años "+months+" Meses "+days+" dias");
                        }else{
                            Toast.makeText(getApplicationContext(),"La fecha no debe de ser mayor que hoy", Toast.LENGTH_SHORT).show();
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });


}}