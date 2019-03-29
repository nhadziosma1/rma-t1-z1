package ba.unsa.etf.rma.rma_t1_z1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private Button dugmeDodaj;
    private TextView prikazTeksta;
    private ListView lvLista;

    //private ArrayAdapter ulancaniAdapter;
    private ArrayList<Muzicar> unosiKorisnika = new ArrayList<>();
    private EditText editujMe;

    //private MainActivity mAct = null;
    private MojAdapter mojAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //predlaze id-eve svih elemenata sa prozora kad upises "R.id. "
        dugmeDodaj = (Button) findViewById(R.id.dugmeDodaj);
        lvLista = (ListView) findViewById(R.id.lista);
        editujMe = (EditText) findViewById(R.id.editujMe);


        napuniListuPodacima();

        mojAdapter = new MojAdapter(this, unosiKorisnika , getResources());
        lvLista.setAdapter( mojAdapter );

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                //unutar Listenera moras koristit izraz "NazivActivitija.this" da bi dobio Context, ali
                // za dobijanje konteksta u van njih, moze se pisati samo "this"
                Intent mojIntent = new Intent(MainActivity.this, MuzicarActivity.class);

                // "mprIme" je samo kljuc, ne mora se zvati isto kao i atribut aktivnosti
                mojIntent.putExtra("mprIme", unosiKorisnika.get(position).getIme());
                mojIntent.putExtra("kljucPrezime", unosiKorisnika.get(position).getPrezime());
                mojIntent.putExtra("kljucZanr", unosiKorisnika.get(position).getZanr().getImeZanra());
                mojIntent.putExtra("kljucTopPjesme", unosiKorisnika.get(position).getNajpoznatijePjesem());
                mojIntent.putExtra("kljucCV", unosiKorisnika.get(position).getCv());

                MainActivity.this.startActivity(mojIntent);
            }
        });


        if( getIntent().getAction() != null && getIntent().getAction().equals(Intent.ACTION_SEND))
            editujMe.setText(getIntent().getData().toString());

        /*dugmeDodaj.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mojAdapter.notifyDataSetChanged();
            }
        });*/
    }


    /*public void onItemClick(int mPosition)
    {
        Muzicar muzicar = ( Muzicar ) unosiKorisnika.get(mPosition);

        // SHOW ALERT
        Toast.makeText(this, ""+muzicar.getIme() +" Image:"+muzicar.getZanr(), Toast.LENGTH_LONG).show();
    }*/

    //samo za punjenjen liste pocetnim informacijama
    public void napuniListuPodacima()
    {
        final Muzicar HimzoPolovina = new Muzicar("Himzo", "Polovina", Muzicar.Zanr.FOLK, "ja sam taj");
        dodajNajpoznatijePjesme(HimzoPolovina);
        final Muzicar HalidBeslic = new Muzicar ("Halid", "Beslic", Muzicar.Zanr.FOLK, "kralj sevdaha");
        dodajNajpoznatijePjesme(HalidBeslic);
        final Muzicar MichaelJackson = new Muzicar("Michael", "Jackson", Muzicar.Zanr.POP, "kralj popa");
        dodajNajpoznatijePjesme(MichaelJackson);
        final Muzicar Shakira = new Muzicar("Shakira", "Ciganka", Muzicar.Zanr.POP, "waka naka");
        dodajNajpoznatijePjesme(Shakira);
        final Muzicar BonJovi = new Muzicar("Bon", "Jovi", Muzicar.Zanr.ROCK, "its my life");
        dodajNajpoznatijePjesme(BonJovi);
        final Muzicar SejoSekson = new Muzicar("Sejo", "Sekson", Muzicar.Zanr.ROCK, "lutka s naslovne");
        dodajNajpoznatijePjesme(SejoSekson);
        final Muzicar SlimaShady = new Muzicar("Slim", "Shady", Muzicar.Zanr.RAP, "eminem");
        dodajNajpoznatijePjesme(SlimaShady);
        final Muzicar EdoMajka = new Muzicar("Edo", "Majka", Muzicar.Zanr.RAP, "vratite nam Elvisa");
        dodajNajpoznatijePjesme(EdoMajka);
        final Muzicar NekiPjevac = new Muzicar("Neki", "Pjevac", Muzicar.Zanr.JAZZ, "mambo no 5");
        dodajNajpoznatijePjesme(NekiPjevac);

        unosiKorisnika.add(HimzoPolovina);
        unosiKorisnika.add(HalidBeslic);
        unosiKorisnika.add(MichaelJackson);
        unosiKorisnika.add(Shakira);
        unosiKorisnika.add(BonJovi);
        unosiKorisnika.add(SejoSekson);
        unosiKorisnika.add(SlimaShady);
        unosiKorisnika.add(EdoMajka);
        unosiKorisnika.add(NekiPjevac);
    }

    private void dodajNajpoznatijePjesme(Muzicar muzicar)
    {
        for(int i=0; i<10; i++)
        {
            muzicar.getNajpoznatijePjesem().add( muzicar.getIme()+muzicar.getPrezime()+i);
        }
    }
}
