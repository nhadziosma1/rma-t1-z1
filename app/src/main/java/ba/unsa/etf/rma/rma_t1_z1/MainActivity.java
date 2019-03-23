package ba.unsa.etf.rma.rma_t1_z1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

        //predlaze id-eve svih elemenata sa prozaora kao upises "R.id. "
        dugmeDodaj = (Button) findViewById(R.id.dugmeDodaj);
        lvLista = (ListView) findViewById(R.id.lista);
        editujMe = (EditText) findViewById(R.id.editujMe);

        //mAct = this;
        napuniListuPodacima();


        mojAdapter = new MojAdapter(this, unosiKorisnika , getResources());
        lvLista.setAdapter( mojAdapter );

        dugmeDodaj.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mojAdapter.notifyDataSetChanged();
            }
        });
    }

    public void onItemClick(int mPosition)
    {
        Muzicar muzicar = ( Muzicar ) unosiKorisnika.get(mPosition);

        // SHOW ALERT
        Toast.makeText(this, ""+muzicar.getIme() +" Image:"+muzicar.getZanr(), Toast.LENGTH_LONG).show();
    }

    //samo za punjenjen liste pocetnim informacijama
    public void napuniListuPodacima()
    {
        final Muzicar HimzoPolovina = new Muzicar("Himzo", "Polovina", Muzicar.Zanr.FOLK, "ja sam taj");
        final Muzicar HalidBeslic = new Muzicar ("Halid", "Beslic", Muzicar.Zanr.FOLK, "kralj sevdaha");
        final Muzicar MichaelJackson = new Muzicar("Michael", "Jackson", Muzicar.Zanr.POP, "kralj popa");
        final Muzicar Shakira = new Muzicar("Shakira", "Ciganka", Muzicar.Zanr.POP, "waka naka");
        final Muzicar BonJovi = new Muzicar("Bon", "Jovi", Muzicar.Zanr.ROCK, "its my life");
        final Muzicar SejoSekson = new Muzicar("Sejo", "Sekson", Muzicar.Zanr.ROCK, "lutka s naslovne");
        final Muzicar SlimaShady = new Muzicar("Slim", "Shady", Muzicar.Zanr.RAP, "eminem");
        final Muzicar EdoMajka = new Muzicar("Edo", "Majka", Muzicar.Zanr.RAP, "vratite nam Elvisa");
        final Muzicar NekiPjevac = new Muzicar("Neki", "Pjevac", Muzicar.Zanr.JAZZ, "mambo no 5");

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
}
