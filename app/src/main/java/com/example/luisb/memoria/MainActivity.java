package com.example.luisb.memoria;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Se ejecuta al pulsarse el boton*/
    public void generarArchivo(View v){
        //siempre que manejes entradas y salidas es importante usar excepciones
        try {

            EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
            String nombre = edtNombre.getText().toString();

            FileOutputStream outputStream = null;
            //(nombre de archivo, modo de creacion o apertura)
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_APPEND);
            outputStream.write(nombre.getBytes());//getBytes() hace que el archivo se se escriba mucho mas rapido
            outputStream.close();

            Toast.makeText(MainActivity.this, "El archivo ha sido creado", Toast.LENGTH_SHORT).show();

            edtNombre.setText("");//se vacia el campo de nombre
        }catch (Exception e){
            e.printStackTrace();//imprime el problema
            Toast.makeText(MainActivity.this, "Hubo un error en la escritura del archivo", Toast.LENGTH_SHORT).show();
        }

    }
}
