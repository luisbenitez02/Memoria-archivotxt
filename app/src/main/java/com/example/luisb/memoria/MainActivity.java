package com.example.luisb.memoria;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /* Se ejecuta al pulsarse el boton, crea un archivo o agrega el nuevo texto que se ha escrito en el textview nombre*/
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

    /*Vamos a crear una preferencia compartida (se ejecuta al pulsar boton - onclick asignado)*/
    public void guardarPreferencia(View v){
        //(nombre de nuestro archivo, modo de acceso)
        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales",Context.MODE_PRIVATE);
        //declaramos un editor
        SharedPreferences.Editor editor = miPreferenciaCompartida.edit();
        EditText edtNombre = (EditText) findViewById(R.id.edtNombre);
        EditText edtCorreo = (EditText) findViewById(R.id.edtCorreo);

        //Vamos a traer el contenido de los elementos
        String nombre = edtNombre.getText().toString();
        String correo = edtCorreo.getText().toString();

        //vamos a empezar a insertar los datos a nuestro archivo
        //(clave,valor)
        editor.putString("nombre", nombre);
        editor.putString("correo", correo);

        editor.commit();

        edtNombre.setText("");
        edtCorreo.setText("");

        Toast.makeText(this, "Se ha creado la preferencia compartida", Toast.LENGTH_SHORT).show();

    }

    /*Vamos a mostrar las preferencias almacenadas*/
    public void mostrarPreferencia(View v){
        //(nombre de nuestro archivo, modo de acceso)
        SharedPreferences miPrefenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        //private siempre esta sobre escribiendo los datos

        //vamos a recuperar los valores almacenados (nombre de la clave,-Si no encuentra la clave que devuelvo-)
        String nombre = miPrefenciaCompartida.getString("nombre", "variable inexistente");
        String correo = miPrefenciaCompartida.getString("correo", "variable inexistente");

        //Mostramos en textview
        TextView tvPreferenciaCompartida = (TextView) findViewById(R.id.tvPrefenciaCompartida);

        String preferencia = "Nombre: " + nombre + "\nCorreo: " + correo;
        tvPreferenciaCompartida.setText(preferencia);
    }
}
