package ba.unsa.etf.rma.rma_t1_z1.Aktivnosti;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ba.unsa.etf.rma.rma_t1_z1.Klase.MojReciever;
import ba.unsa.etf.rma.rma_t1_z1.R;

public class MuzicarActivity extends AppCompatActivity
{
    //ATRIBUTI
    private ImageView mprSlika;
    private TextView mprIme;
    private TextView mprPrezime;
    private TextView mprZanr;
    private TextView urlPjevaceveStranice;
    private Button mprPodlijeliNaFb;

    private ArrayList<String> mprArrayPjesama = new ArrayList<>();
    private ListView listaPjesama;
    private ArrayAdapter<String> adapterPjesama;

    private int REQUEST_CAMERA = 1;
    private MojReciever mojRisiver = new MojReciever();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muzicar);

        mprIme = (TextView) findViewById(R.id.mprIme);
        mprPrezime = (TextView) findViewById(R.id.mprPrezime);
        mprZanr = (TextView) findViewById(R.id.mprZanr);
        mprSlika = (ImageView) findViewById(R.id.mprSlika);
        listaPjesama = (ListView) findViewById(R.id.mprLista);
        mprPodlijeliNaFb = (Button) findViewById(R.id.mprPodijeli);

        mprSlika.setImageResource(R.drawable.pop);

        //ovdje se navodi kljuc stringa koji je proslijedjen kao
        mprIme.setText(getIntent().getStringExtra("mprIme"));
        mprPrezime.setText(getIntent().getStringExtra("kljucPrezime"));
        mprZanr.setText(getIntent().getStringExtra("kljucZanr"));


       //proslijedjena je prozoru ArrayList iz Muzicar tipa "najpoznaitjePjesme", pa se ovom linijom dohvacaju te info
        mprArrayPjesama = getIntent().getStringArrayListExtra("kljucTopPjesme");
        //spajanje ArrayList i ListView-a
        adapterPjesama = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mprArrayPjesama);
        listaPjesama.setAdapter(adapterPjesama);

        urlPjevaceveStranice = (TextView) findViewById(R.id.mprUrl);
        this.nadjiURL(mprIme.getText().toString(), mprPrezime.getText().toString());

        // Here, thisActivity is the current activity
        /*if (ContextCompat.checkSelfPermission(MuzicarActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            // Permission is not granted
            // Should we show an explanation?
            if ( ActivityCompat.shouldShowRequestPermissionRationale(MuzicarActivity.this, Manifest.permission.CAMERA))
            {  //Build.VERSION.SDK_INT < 23

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                Toast.makeText(this, "Camera permission is needed to show the camera privew", Toast.LENGTH_SHORT).show();
            }
            else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(MuzicarActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        } else {
            // Permission has already been granted
            urlPjevaceveStranice.setText("cuna, vec odobren pristup kameri");
        }*/

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

        listaPjesama.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                searchYoutubeVideo(MuzicarActivity.this, mprArrayPjesama.get(position)+" "+
                        getIntent().getStringExtra("imeAutora"));
            }
        });

        mprPodlijeliNaFb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent otvoriFB = new Intent();
                otvoriFB.setAction(Intent.ACTION_SEND);
                otvoriFB.putExtra(Intent.ACTION_VIEW,"NESTOOOOO");
                otvoriFB.setType("text/plain");

                if(otvoriFB.resolveActivity(getPackageManager() ) != null)
                {
                    startActivity(otvoriFB.createChooser(otvoriFB, "Share"));
                }
            }
        });

    }

    private void searchYoutubeVideo(Context context, String id)
    {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/results?search_query=" + id));

        try
        {
            context.startActivity(appIntent);
        }
        catch (ActivityNotFoundException ex)
        {
            ex.printStackTrace();
            context.startActivity(webIntent);
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


    @Override
    public void onStart()
    {
        super.onStart();

        IntentFilter intent_filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mojRisiver, intent_filter);
    }

    @Override
    public void onStop()
    {
        super.onStop();
        unregisterReceiver(mojRisiver);
    }


}
