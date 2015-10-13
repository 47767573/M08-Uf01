package com.primapp.conversor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Definimos las variables de entrada
    private EditText preuInm;
    private EditText estalvis;
    private SeekBar plazo;
    private EditText euribor;
    private EditText diferencial;

    //Definimos las variables de salida
    private TextView mes, total;

    //Definimos boton de inicio del calculo
    private Button Calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        preuInm = (EditText)findViewById(R.id.etPreuInm);
        preuInm.addTextChangedListener(onWatch);

        estalvis = (EditText)findViewById(R.id.etEstalvis);
        estalvis.addTextChangedListener(onWatch);

        plazo = (SeekBar)findViewById(R.id.sbPlazos);
        plazo.setOnSeekBarChangeListener((SeekBar.OnSeekBarChangeListener) onWatch);

        euribor = (EditText)findViewById(R.id.etEuribor);
        euribor.addTextChangedListener(onWatch);

        diferencial = (EditText)findViewById(R.id.etDiferencial);
        diferencial.addTextChangedListener(onWatch);


        mes = (TextView)findViewById(R.id.tvMes);
        total = (TextView)findViewById(R.id.tvTotal);

        //ocultamos los resultados inicialmente ya que no hay valores que calcular
        mes.setVisibility (View.GONE);
        total.setVisibility(View.GONE);

        //referenciamos el botón
        Calc = (Button)findViewById(R.id.btnCal);

        //ajustamos los valores a 0 al crearse
        setValues ("0","0");
    }

    public final TextWatcher onWatch = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {    }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            total.setVisibility(View.VISIBLE);
            mes.setVisibility(View.VISIBLE);
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() == 0){
                total.setVisibility(View.GONE);
                mes.setVisibility(View.GONE);
            }else{
                calcHipoteca();
            }
        }
    };


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

    public void onCalcular(View view) {
        calcHipoteca();
    }

    public void calcHipoteca (){

        //Parseamos a double
        double preuInmDb = Double.valueOf(preuInm.getText().toString());
        double estalvisDb = Double.valueOf(estalvis.getText().toString());
        double plazoDb = Double.valueOf(plazo.getProgress());
        double euriborDb = Double.valueOf(euribor.getText().toString());
        double diferencialDb = Double.valueOf(diferencial.getText().toString());


        double mes = preuInmDb+estalvisDb-(plazoDb*euriborDb);

        double total = (mes*12)-diferencialDb;


        String mesStr = String.valueOf(mes);
        String totalStr = String.valueOf(total);

        setValues(mesStr, totalStr);
    }


    public void setValues (String mesCal, String totalCal) {
        mes.setText("Mes: "+mesCal);
        total.setText("Total: "+totalCal);
    }

}
