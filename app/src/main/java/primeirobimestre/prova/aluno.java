package primeirobimestre.prova;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class aluno extends AppCompatActivity {

    private ImageView img;
    private TextView msg;
    private ArrayList<Calculo> calculo;
    private Intent intent;
    private ImageButton btInfo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aluno);

        ImageButton btInfo = (ImageButton)findViewById(R.id.btInfo);


        final MediaPlayer mediaHi = MediaPlayer.create(aluno.this, R.raw.hi);
        mediaHi.start();


        intent = getIntent();

        calculo = intent.getParcelableArrayListExtra("calculo");


        ImageView mimageView = (ImageView) findViewById(R.id.imagem);

        Bitmap mbitmap = ((BitmapDrawable) getResources().getDrawable(R.drawable.pp)).getBitmap();
        Bitmap imageRounded = Bitmap.createBitmap(mbitmap.getWidth(), mbitmap.getHeight(), mbitmap.getConfig());
        Canvas canvas = new Canvas(imageRounded);
        Paint mpaint = new Paint();
        mpaint.setAntiAlias(true);
        mpaint.setShader(new BitmapShader(mbitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
        canvas.drawRoundRect((new RectF(0, 0, mbitmap.getWidth(), mbitmap.getHeight())), 500, 500, mpaint);// Round Image Corner 100 100 100 100
        mimageView.setImageBitmap(imageRounded);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("BIRBLATOR");


        btInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog d = new AlertDialog.Builder(aluno.this)
                        .setPositiveButton(android.R.string.ok, null)
                        .setMessage(Html.fromHtml("Developed by:<br/>" +
                                "Jhony Lara<br/>" +
                                "<a href=\"https://www.linkedin.com/in/jhonylara/\">in/jhonylara</a><br/>" +
                                "<br/>" +
                                "Images by:<br/>" +
                                "Jen Budrock<br/>" +
                                "<a href=\"https://birdhism.com\">birdhism.com</a><br/>" +
                                "<br/>" +
                                "Thanks to:<br/>" +
                                "Maicon Andraski<br/>" +
                                "Prof: Charles W. H. Fung<br/>"))
                        .setNeutralButton(
                                "Profile",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.linkedin.com/in/jhonylara/")));
                                    }
                                })
                        .setPositiveButton("Confirm",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                })
                        .create();
                d.show();
                // Make the textview clickable. Must be called after show()
                ((TextView)d.findViewById(android.R.id.message)).setMovementMethod(LinkMovementMethod.getInstance());





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
                Intent intent = new Intent(aluno.this, calculadora.class);
                startActivity(intent);
                break;

            case R.id.menuAluno:
                //Intent intent2 = new Intent(aluno.this, aluno.class);
                //startActivity(intent2);
                break;

            case R.id.menuHistorico:
                Intent intent3 = new Intent(aluno.this, historico.class);
                intent3.putParcelableArrayListExtra("calculo", calculo);
                startActivity(intent3);
                break;

        }
        return super.onOptionsItemSelected(item);
    }


}
