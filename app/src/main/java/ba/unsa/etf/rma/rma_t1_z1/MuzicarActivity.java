package ba.unsa.etf.rma.rma_t1_z1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MuzicarActivity extends AppCompatActivity
{

    //ATRIBUTI
    private ImageView mprSlika;
    private TextView mprIme;
    private TextView mprPrezime;
    private TextView mprZanr;
    private TextView urlPjevaceveStranice;
    private ArrayList<String> mprListaPjesama = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzicar);

        mprIme = (TextView) findViewById(R.id.mprIme);
        mprPrezime = (TextView) findViewById(R.id.mprPrezime);
        mprZanr = (TextView) findViewById(R.id.mprZanr);
        mprSlika = (ImageView) findViewById(R.id.mprSlika);

        mprSlika.setImageResource(R.drawable.pop);

        //ovdje se navodi kljuc stringa koji je proslijedjen kao
        mprIme.setText(getIntent().getStringExtra("mprIme"));
        mprPrezime.setText(getIntent().getStringExtra("kljucPrezime"));
        mprZanr.setText(getIntent().getStringExtra("kljucZanr"));

        urlPjevaceveStranice = (TextView) findViewById(R.id.mprUrl);
        this.nadjiURL(mprIme.getText().toString(), mprPrezime.getText().toString());

        if(urlPjevaceveStranice.getText().toString().trim().length() > 0)
        {
            urlPjevaceveStranice.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v)
                {
                    Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
                    intentYoutube.setData(Uri.parse(urlPjevaceveStranice.getText().toString()));
                    startActivity(intentYoutube);

                }
            });
        }
        else
        {
            AlertDialog upozori = new AlertDialog.Builder(this)
                    .setTitle("Nema odgovarajuce aplikacije")
                    .setMessage("Ne postoji aplikacija koja moze otvoriti ovaj sadrzaj")

                    // Specifying a listener allows you to take an action before dismissing the dialog.
                    // The dialog is automatically dismissed when a dialog button is clicked.
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which)
                        {
                            // Continue with delete operation
                        }
                    })

                    // A null listener allows the button to dismiss the dialog and take no further action.
                    .setNegativeButton(android.R.string.no, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

    }

    //ova funkcionalnost bi se puno ljepse obavila da imamo bazu iz koje dohvacamo ove informacije
    private void nadjiURL(String ime, String prezime)
    {
        if(ime.equals("Himzo") && prezime.equals("Polovina"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=l8-5gjXm8K4");
        else if(ime.equals("Halid") && prezime.equals("Beslic"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=IGQvPDOgQDE");
        else if(ime.equals("Michael") && prezime.equals("Jackson"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=PivWY9wn5ps");
        else if(ime.equals("Shakira") && prezime.equals("Ciganka"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=_3-GiVIE8gc");
        else if(ime.equals("Bon") && prezime.equals("Jovi"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=lDK9QqIzhwk");
        else if(ime.equals("Sejo") && prezime.equals("Sekson"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=lFT3S3-bV70");
        else if(ime.equals("Slim") && prezime.equals("Shady"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=eJO5HU_7_1w");
        else if(ime.equals("Edo") && prezime.equals("Majka"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=jmg1JN_hB9g");
        else if(ime.equals("Neki") && prezime.equals("Pjevac"))
            urlPjevaceveStranice.setText("https://www.youtube.com/watch?v=vmDDOFXSgAs");
    }



}
