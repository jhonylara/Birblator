package primeirobimestre.prova;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class calculadora extends AppCompatActivity {

    private TextView tvVisor;
    private EditText tvConta;

    private LinearLayout linearMain;
    private ImageView imageBk;

    private RadioGroup rgConversores;
    private RadioButton rbHex;
    private RadioButton rbOctal;
    private RadioButton rbDec;
    private RadioButton rbBin;

    private ImageButton bt1;
    private ImageButton bt2;
    private ImageButton bt3;
    private ImageButton bt4;
    private ImageButton bt5;
    private ImageButton bt6;
    private ImageButton bt7;
    private ImageButton bt8;
    private ImageButton bt9;
    private ImageButton bt0;
    private ImageButton btMais;
    private ImageButton btMenos;
    private ImageButton btMultiplicar;
    private ImageButton btDividir;
    private ImageButton btTrocar;
    private ImageButton btIgual;
    private ImageButton btPonto;
    private ImageButton btRaiz;
    private ImageButton btExp;
    private ImageButton btClear;

    private String ultimaOperacao;
    private String corpoUltimaOperacao;
    private Double ultimoValor;
    private Double valorAtual;
    private Double resultado;
    private Boolean ehUmResultado = false;
    private Boolean ehUmaConversao = false;

    private static ArrayList<Calculo> calculo = new ArrayList<Calculo>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        tvVisor = (TextView)findViewById(R.id.etVisor);
        tvConta = (EditText)findViewById(R.id.etConta);

        rgConversores = (RadioGroup)findViewById(R.id.rgConversores);
        RadioButton rbBin = (RadioButton)findViewById(R.id.rbBin);
        final RadioButton rbDec = (RadioButton)findViewById(R.id.rbDec);
        RadioButton rbHex = (RadioButton)findViewById(R.id.rbHex);
        RadioButton rbOctal = (RadioButton)findViewById(R.id.rbOctal);

        ImageButton bt1 = (ImageButton)findViewById(R.id.bt1);
        ImageButton bt2 = (ImageButton)findViewById(R.id.bt2);
        ImageButton bt3 = (ImageButton)findViewById(R.id.bt3);
        ImageButton bt4 = (ImageButton)findViewById(R.id.bt4);
        ImageButton bt5 = (ImageButton)findViewById(R.id.bt5);
        ImageButton bt6 = (ImageButton)findViewById(R.id.bt6);
        ImageButton bt7 = (ImageButton)findViewById(R.id.bt7);
        ImageButton bt8 = (ImageButton)findViewById(R.id.bt8);
        ImageButton bt9 = (ImageButton)findViewById(R.id.bt9);
        ImageButton bt0 = (ImageButton)findViewById(R.id.bt0);
        ImageButton btMais = (ImageButton)findViewById(R.id.btMais);
        ImageButton btMenos = (ImageButton)findViewById(R.id.btMenos);
        ImageButton btMultiplicar = (ImageButton)findViewById(R.id.btMultiplicar);
        ImageButton btDividir = (ImageButton)findViewById(R.id.btDividir);
        ImageButton btTrocar = (ImageButton)findViewById(R.id.btTrocar);
        final ImageButton btIgual = (ImageButton)findViewById(R.id.btIgual);
        ImageButton btPonto = (ImageButton)findViewById(R.id.btPonto);
        ImageButton btRaiz = (ImageButton)findViewById(R.id.btRaiz);
        ImageButton btExp = (ImageButton)findViewById(R.id.btExp);
        ImageButton btClear = (ImageButton)findViewById(R.id.btClear);
        final MediaPlayer mediaAaa = MediaPlayer.create(calculadora.this, R.raw.aaaa);
        final MediaPlayer mediaHi = MediaPlayer.create(calculadora.this, R.raw.hi);
        final MediaPlayer mediaPekaboo = MediaPlayer.create(calculadora.this, R.raw.pekaboo);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BIRBLATOR");

        rgConversores.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int op = rgConversores.getCheckedRadioButtonId();
                if (!rbDec.isChecked()){
                    if (!tvVisor.getText().toString().matches("") && ehUmaConversao == false ){
                        ultimoValor = Double.parseDouble(tvVisor.getText().toString());
                    }
                }

                String texto="";
                switch(op){
                    case R.id.rbBin:
                        if (!tvVisor.getText().toString().matches("")) {
                            String textoUltimoValor = ultimoValor.toString();
                            int valor = (int) Double.parseDouble(textoUltimoValor);
                            tvVisor.setText(Integer.toBinaryString(valor).toString());
                            ehUmaConversao = true;
                        }
                        break;
                    case R.id.rbDec:
                        if (!tvVisor.getText().toString().matches("")) {
                            if (ehUmaConversao){
                                tvVisor.setText(formataResultado(ultimoValor));
                                ehUmaConversao = false;
                            }
                        }
                        break;
                    case R.id.rbHex:
                        if (!tvVisor.getText().toString().matches("")) {
                            String textoUltimoValor = ultimoValor.toString();
                            int valor = (int) Double.parseDouble(textoUltimoValor);
                            tvVisor.setText(Integer.toHexString(valor).toString());
                            ehUmaConversao = true;
                        }
                        break;
                    case R.id.rbOctal:
                        if (!tvVisor.getText().toString().matches("")) {
                            String textoUltimoValor = ultimoValor.toString();
                            int valor = (int) Double.parseDouble(textoUltimoValor);
                            tvVisor.setText(Integer.toOctalString(valor).toString());
                            ehUmaConversao = true;
                        }
                        break;
                }
            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("1");

                }else {
                    tvVisor.setText(tvVisor.getText() + "1");
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("2");

                }else {
                    tvVisor.setText(tvVisor.getText() + "2");
                }
            }
        });

        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("3");

                }else {
                    tvVisor.setText(tvVisor.getText() + "3");
                }
            }
        });

        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("4");

                }else {
                    tvVisor.setText(tvVisor.getText() + "4");
                }
            }
        });

        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("5");

                }else {
                    tvVisor.setText(tvVisor.getText() + "5");
                }
            }
        });

        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("6");

                }else {
                    tvVisor.setText(tvVisor.getText() + "6");
                }
            }
        });

        bt7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("7");

                }else {
                    tvVisor.setText(tvVisor.getText() + "7");
                }
            }
        });
        bt8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("8");

                }else {
                    tvVisor.setText(tvVisor.getText() + "8");
                }
            }
        });

        bt9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("9");

                }else {
                    tvVisor.setText(tvVisor.getText() + "9");
                }
            }
        });

        bt0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    tvVisor.setText("0");

                }else {
                    tvVisor.setText(tvVisor.getText() + "0");
                }
            }
        });


        btPonto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ehUmResultado || ehUmaConversao){
                    resetarValores();
                    if(!verificarDouble())
                    {
                        tvVisor.setText(tvVisor.getText() + ".");
                    }

                }else {
                    if(!verificarDouble())
                    {
                        tvVisor.setText(tvVisor.getText() + ".");
                    }
                }


            }
        });

        btMais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoClicado("+");
            }
        });

        btMenos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                botaoClicado("-");
            }
        });

        btMultiplicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoClicado("*");
            }
        });

        btExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoClicado("exp");
            }
        });

        btRaiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ultimaOperacao = "Raiz";
                efetuaUltimaOperacao();
            }
        });

        btDividir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                botaoClicado("/");
                //ultimaOperacao = "/";
                //efetuaUltimaOperacao();
            }
        });

        btTrocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvVisor = findViewById(R.id.etVisor);
                if (!tvVisor.getText().toString().matches("") && !ehUmaConversao ) {
                    Double valor = Double.parseDouble(tvVisor.getText().toString());
                    valor = valor * -1;
                    tvVisor.setText(formataResultado(valor));
                }
            }
        });

        btClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaAaa.start();
                resetarValores();
            }
        });


        btIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPekaboo.start();
                if (ehUmaConversao){
                    rbDec.setChecked(true);
                }
                if (resultado == null){
                    efetuaUltimaOperacao();
                }
                if (valorAtual != null){
                    resultado();
                }
            }
        });


        linearMain = (LinearLayout) findViewById(R.id.linearMain);



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
                //Intent intent = new Intent(calculadora.this, calculadora.class);
                //startActivity(intent);
                break;

            case R.id.menuAluno:
                Intent intent2 = new Intent(calculadora.this, aluno.class);
                intent2.putParcelableArrayListExtra("calculo", calculo);
                startActivity(intent2);
                break;

            case R.id.menuHistorico:
                Intent intent3 = new Intent(calculadora.this, historico.class);
                intent3.putParcelableArrayListExtra("calculo", calculo);
                startActivityForResult(intent3,1);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean verificarDouble(){
        tvVisor = findViewById(R.id.etVisor);
        String texto = tvVisor.getText().toString();
        if (!tvVisor.getText().toString().matches(""))
        {
            if(texto.indexOf(".") > 0)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return true;
        }
    }

    public void somar(){
        tvVisor = findViewById(R.id.etVisor);
        if (!tvVisor.getText().toString().matches("")) {
            valorAtual = Double.parseDouble(tvVisor.getText().toString());
            resultado = (ultimoValor + valorAtual);
            registro();
        }
    }

    public void subtrair(){
        tvVisor = findViewById(R.id.etVisor);
        if (!tvVisor.getText().toString().matches("")) {
            valorAtual = Double.parseDouble(tvVisor.getText().toString());
            resultado = (ultimoValor - valorAtual);
            registro();
        }
    }

    public void multiplicar(){
        tvVisor = findViewById(R.id.etVisor);
        if (!tvVisor.getText().toString().matches("") && ultimoValor != null) {
            valorAtual = Double.parseDouble(tvVisor.getText().toString());
            resultado = (ultimoValor * valorAtual);
            registro();
        }
    }

    public void dividir(){
        tvVisor = findViewById(R.id.etVisor);
        if (!tvVisor.getText().toString().matches("")) {
            valorAtual = Double.parseDouble(tvVisor.getText().toString());
            resultado = (ultimoValor / valorAtual);
            registro();
        }
    }

    public void registro() {
        tvVisor = findViewById(R.id.etVisor);
        if (!tvVisor.getText().toString().matches(""))
        {
            if (corpoUltimaOperacao == null)
            {
                if (ultimaOperacao == "exp")
                {
                    corpoUltimaOperacao = ultimaOperacao + "( " + formataResultado(ultimoValor);
                    tvConta.setText(corpoUltimaOperacao);
                }
                else
                {
                    if (ultimaOperacao == "Raiz")
                    {
                        corpoUltimaOperacao = ultimaOperacao + "(" + formataResultado(ultimoValor) + ")";
                        tvConta.setText(corpoUltimaOperacao);
                    }
                    else
                    {
                        corpoUltimaOperacao = formataResultado(ultimoValor) + " " + ultimaOperacao + " ";
                        tvConta.setText(corpoUltimaOperacao);
                    }
                }
            }
            else
            {
                if (ultimaOperacao == "Raiz"){
                    corpoUltimaOperacao = ultimaOperacao + "(" + formataResultado(ultimoValor) + ")";
                    tvConta.setText(corpoUltimaOperacao);
                }
                else
                {
                    if (ultimaOperacao == "exp")
                    {
                        corpoUltimaOperacao = corpoUltimaOperacao + ", " + formataResultado(valorAtual) + ")";
                        tvConta.setText(corpoUltimaOperacao);
                    }
                    else
                    {
                        corpoUltimaOperacao = corpoUltimaOperacao + formataResultado(valorAtual) + " " + ultimaOperacao + " ";
                        tvConta.setText(corpoUltimaOperacao);
                    }
                }

            }
        }
        else
        {
            if (corpoUltimaOperacao != null)
            {
                if (ultimaOperacao == "exp")
                {
                    corpoUltimaOperacao = ultimaOperacao + "( " + formataResultado(ultimoValor);
                    tvConta.setText(corpoUltimaOperacao);
                }
                else
                {
                    corpoUltimaOperacao = formataResultado(ultimoValor) + " " + ultimaOperacao + " ";
                    tvConta.setText(corpoUltimaOperacao);
                }
            }
        }
    }

    public void raiz(){
        tvVisor = findViewById(R.id.etVisor);
        rbDec = findViewById(R.id.rbDec);
        rbDec.setChecked(true);
        if (!tvVisor.getText().toString().matches("")) {
            if (ehUmaConversao){
                valorAtual = ultimoValor;
            }
            else
            {
                valorAtual = Double.parseDouble(tvVisor.getText().toString());
            }
            resultado = Math.sqrt(valorAtual);
            ultimoValor = valorAtual;
            ehUmResultado = true;
            tvVisor.setText(formataResultado(resultado));
            registro();
            Calculo item = new Calculo("Raiz (" + formataResultado(ultimoValor) + ") = " + formataResultado(resultado), formataResultado(resultado));
            calculo.add(item);

        }
    }
    public void exp(){
        tvVisor = findViewById(R.id.etVisor);
        rbDec = findViewById(R.id.rbDec);
        rbDec.setChecked(true);
        if (!tvVisor.getText().toString().matches("")) {
            if (ehUmaConversao){
                valorAtual = ultimoValor;
            }
            else
            {
                valorAtual = Double.parseDouble(tvVisor.getText().toString());
            }
            resultado = (Math.pow(ultimoValor, valorAtual));
            registro();
            Calculo item = new Calculo("Exp (" + formataResultado(ultimoValor) + ", "+ formataResultado(valorAtual) + ") = " + formataResultado(resultado), formataResultado(resultado));
            calculo.add(item);
        }
    }

    public void trocar(){

    }

    private void resultado(){

        if (corpoUltimaOperacao != null)
        {
            if (ultimaOperacao == "exp")
            {
                corpoUltimaOperacao = corpoUltimaOperacao + " = " + formataResultado(resultado);
                tvConta.setText(corpoUltimaOperacao);
            }
            else
            {
                if (ultimaOperacao == "Raiz")
                {
                    corpoUltimaOperacao = corpoUltimaOperacao + " = " + formataResultado(resultado);
                    tvConta.setText(corpoUltimaOperacao);
                }
                else
                {
                    corpoUltimaOperacao = corpoUltimaOperacao.substring(0, corpoUltimaOperacao.length() - 3);
                    corpoUltimaOperacao = corpoUltimaOperacao + " = " + formataResultado(resultado);

                    Calculo item = new Calculo(corpoUltimaOperacao, formataResultado(resultado));
                    calculo.add(item);
                    tvConta.setText(corpoUltimaOperacao);
                }
            }

        }
        tvVisor.setText(formataResultado(resultado));

        ultimoValor = resultado;
        valorAtual = null;
        corpoUltimaOperacao = null;
        ehUmResultado = true;

    }

    public void efetuaUltimaOperacao()
    {
        if (ultimaOperacao != null)
        {
            switch (ultimaOperacao)
            {
                case "+":
                    somar();
                    break;

                case "-":
                    subtrair();
                    break;

                case "/":
                    dividir();
                    break;

                case "*":
                    multiplicar();
                    break;

                case "+/-":
                    trocar();
                    break;

                case "Raiz":
                    raiz();
                    break;

                case "exp":
                    exp();
                    break;
            }
        }
    }

    public void fazerOperacao(){
        if(ultimoValor == null){
            ultimoValor = Double.parseDouble(tvVisor.getText().toString());
            tvVisor.setText(null);

        }else{
            efetuaUltimaOperacao();
        }
    }

    private void resetarValores()
    {
        rbDec = findViewById(R.id.rbDec);
        tvVisor = findViewById(R.id.etVisor);
        tvVisor.setText(null);
        tvConta.setText(null);
        ultimaOperacao = null;
        corpoUltimaOperacao = null;
        ultimoValor = null;
        valorAtual = null;
        resultado = null;
        ehUmResultado = false;
        ehUmaConversao = false;
        rbDec.setChecked(true);
    }

    public void botaoClicado(String operacao)
    {
        if (ehUmResultado)
        {
            rbDec = findViewById(R.id.rbDec);
            tvConta.setText(null);
            ultimaOperacao = null;
            corpoUltimaOperacao = null;
            valorAtual = null;
            resultado = null;
            ehUmResultado = false;
            ultimoValor = resultado;
            ehUmaConversao = false;
            rbDec.setChecked(true);
        }
        if (ehUmaConversao)
        {
            rbDec = findViewById(R.id.rbDec);
            tvConta.setText(null);
            ultimaOperacao = null;
            corpoUltimaOperacao = null;
            valorAtual = null;
            resultado = null;
            ehUmResultado = false;
            ultimaOperacao = operacao;
            registro();
            tvVisor.setText(null);
            ehUmaConversao = false;
            rbDec.setChecked(true);
        }
        if (valorAtual == null)
        {
            if (!tvVisor.getText().toString().matches(""))
            {
                if (ultimoValor != null)
                {
                    if (operacao == ultimaOperacao){
                        ultimaOperacao = operacao;
                        valorAtual = Double.parseDouble(tvVisor.getText().toString());
                    }
                }
                else
                {
                    ultimoValor = Double.parseDouble(tvVisor.getText().toString());
                    ultimaOperacao = operacao;
                    registro();
                    tvVisor.setText(null);
                }
            }
            else
            {
                ultimaOperacao = operacao;
                registro();
            }
        }
    }

    public String formataResultado(Double valor)
    {
        String numero = valor.toString();
        if (numero.substring(numero.length() - 2, numero.length()).matches(".0"))
        {
            String resultado = numero.substring(0, numero.length() - 2);
            return resultado;
        }
        else
        {
            return valor.toString();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
        {
            String teste2 = data.getStringExtra("resultado");
            resetarValores();
            ehUmResultado = true;
            tvVisor.setText(teste2);
        }

    }
}
