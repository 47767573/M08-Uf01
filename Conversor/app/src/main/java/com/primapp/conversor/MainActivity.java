package com.primapp.conversor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.math.*;


public class MainActivity extends AppCompatActivity {

    //Definimos las variables de entrada
    private EditText preuInm;
    private EditText estalvis;
    private EditText plazo;
    private EditText euribor;
    private EditText diferencial;

    //Definimos las variables de salida
    private TextView mes;
    private TextView total;

    //Definimos boton de inicio del evento
    private Button Calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preuInm = (EditText)findViewById(R.id.etPreuInm);
        estalvis = (EditText)findViewById(R.id.etEstalvis);
        plazo = (EditText)findViewById(R.id.etPlazo);
        euribor = (EditText)findViewById(R.id.etEuribor);
        diferencial = (EditText)findViewById(R.id.etDiferencial);

        mes = (TextView)findViewById(R.id.tvMes);
        total = (TextView)findViewById(R.id.tvTotal);

        Calc = (Button)findViewById(R.id.btnCal);








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calcHipoteca (){

        //Pasamos a double
        double preuInmDb = Double.valueOf(preuInm.getText().toString());
        double estalvisDb = Double.valueOf(estalvis.getText().toString());
        double plazoDb = Double.valueOf(plazo.getText().toString());
        double euriborDb = Double.valueOf(euribor.getText().toString());
        double diferencialDb = Double.valueOf(diferencial.getText().toString());


        double capital = preuInmDb - estalvisDb;
        double interes = (euriborDb+diferencialDb)/12;

        double total = capital + interes;
        double potencia = Math.pow(((1+interes)/100),(-plazoDb));
        double mes = (capital*(interes))/(100*potencia);

        String mesStr = String.valueOf(mes);
        String totalStr = String.valueOf(total);

        setValues(mesStr, totalStr);
    }

    public void onCalcular(View view) {
        calcHipoteca();
    }

    public void setValues (String mesCal, String totalCal) {

        mes.setText("Mes: "+mesCal);
        total.setText("Total: "+totalCal);
    }

}
