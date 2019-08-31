package primeirobimestre.prova;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class historico extends AppCompatActivity {

    private ArrayList<Calculo> calculo;
    private ArrayList<String> itens = new ArrayList<String>();
    private Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BIRBLATOR");

        //Bundle extras = getIntent().getExtras();
        //calculo  = extras.getParcelableArrayList("calculo");

        //Intent intent3 = getIntent();
        //calculo = intent3.getExtras().getParcelableArrayList("calculo");
        //calculo = intent3.getParcelableArrayListExtra("calculo");
        intent = getIntent();

        calculo = intent.getParcelableArrayListExtra("calculo");

        for(int i=0;i<calculo.size();i++){
            itens.add(calculo.get(i).getConta());
        }

        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itens);

        final ListView lvHistorico = findViewById(R.id.lvHistorico);

        lvHistorico.setAdapter(itemsAdapter);

        lvHistorico.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String resultado = calculo.get(position).getResultado();

                Intent intentNovo = new Intent();
                intentNovo.putExtra("resultado", resultado);
                setResult(Activity.RESULT_OK, intentNovo);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculadora, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()){
            case R.id.menuCalc:
                Intent intent = new Intent(historico.this, calculadora.class);
                startActivity(intent);
                break;

            case R.id.menuAluno:
                Intent intent2 = new Intent(historico.this, aluno.class);
                intent2.putParcelableArrayListExtra("calculo", calculo);
                startActivity(intent2);
                break;

            case R.id.menuHistorico:
                //Intent intent3 = new Intent(historico.this, historico.class);
                //intent3.putParcelableArrayListExtra("calculo", calculo);
                //startActivityForResult(intent3,1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
