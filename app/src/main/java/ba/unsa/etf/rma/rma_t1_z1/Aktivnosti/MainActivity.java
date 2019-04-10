package ba.unsa.etf.rma.rma_t1_z1.Aktivnosti;

import android.provider.SyncStateContract;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ba.unsa.etf.rma.rma_t1_z1.Fragmenti.FragmentDetaljiOMuzicaru;
import ba.unsa.etf.rma.rma_t1_z1.Fragmenti.FragmentLista;
import ba.unsa.etf.rma.rma_t1_z1.Klase.MojAdapter;
import ba.unsa.etf.rma.rma_t1_z1.Klase.Muzicar;
import ba.unsa.etf.rma.rma_t1_z1.R;

public class MainActivity extends AppCompatActivity implements FragmentLista.OnItemClick
{
    private Button dugmeDodaj;
    private TextView prikazTeksta;
    private ListView lvLista;

    //private ArrayAdapter ulancaniAdapter;
    private ArrayList<Muzicar> unosiKorisnika = new ArrayList<>();
    private EditText editujMe;

    //private MainActivity mAct = null;
    private MojAdapter mojAdapter;

    // "siriL" je privatni atribut klase MainActivity koji je tipa Boolean.
    // Ovu variablu ćemo koristiti da znamo o kojem layoutu se radi,
    // ako je "siriL" true tada se radi o širem layoutu (dva fragmenta), a
    // ako je "siriL" false tada se radi o početnom layoutu (jedan fragment)
    Boolean siriL = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //dohvatanje FragmentManager-a
        FragmentManager fm = getFragmentManager();
        FrameLayout lDetalji = (FrameLayout) findViewById(R.id.frejmZaDrugiFragment);

        napuniListuPodacima();

        //slucaj layouta za široke ekrane
        if(lDetalji != null)
        {
            siriL = true;
            FragmentDetaljiOMuzicaru fd;

            fd = (FragmentDetaljiOMuzicaru) fm.findFragmentById(R.id.frejmZaDrugiFragment);

            //provjerimo da li je fragment detalji već kreiran
            if(fd == null)
            {
                //kreiramo novi fragment FragmentDetalji ukoliko već nije kreiran
                fd = new FragmentDetaljiOMuzicaru();
                fm.beginTransaction().replace(R.id.frejmZaDrugiFragment, fd).commit();
            }
        }

        //Dodjeljivanje fragmenta FragmentLista
        FragmentLista fragmentLista = (FragmentLista) fm.findFragmentByTag("Lista");

        //provjerimo da li je već kreiran navedeni fragment
        if(fragmentLista == null)
        {
            //ukoliko nije, kreiramo
            fragmentLista = new FragmentLista();

            Bundle argumenti = new Bundle();
            argumenti.putParcelableArrayList("Alista", unosiKorisnika);

            fragmentLista.setArguments(argumenti);

            fm.beginTransaction().replace(R.id.frejmZaPrviFregment, fragmentLista, "Lista").commit();
        }
        else
        {
            //slučaj kada mijenjamo orijentaciju uređaja
            //iz portrait (uspravna) u landscape (vodoravna)
            //a u aktivnosti je bio otvoren fragment FragmentDetalji
            //tada je potrebno skinuti FragmentDetalji sa steka
            //kako ne bi bio dodan na mjesto fragmenta FragmentLista
            fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }

        //predlaze id-eve svih elemenata sa prozora kad upises "R.id. "
        /*dugmeDodaj = (Button) findViewById(R.id.dugmeDodaj);
        lvLista = (ListView) findViewById(R.id.lista);
        editujMe = (EditText) findViewById(R.id.editujMe);

        napuniListuPodacima();

        mojAdapter = new MojAdapter(this, unosiKorisnika , getResources());
        lvLista.setAdapter( mojAdapter );

        // u onClickListener-u na ListView parametar id=-1 za header i footer bude -1, a position=0 za header, a
        // za footer postition = vel_liste ili position = vel_liste+1 ako u ima iznad liste vec header
        /*View headerView = getLayoutInflater().inflate(R.layout.header, null);
        lvLista.addHeaderView(headerView);
        View footerView = getLayoutInflater().inflate(R.layout.footer, null);
        lvLista.addFooterView(footerView);
        footerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                v.setBackgroundColor(Color.parseColor("#F0FF00"));
            }
        });/


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

    @Override
    public void onItemClicked(int pos)
    {
        //Priprema novog fragmenta FragmentDetalji
        Bundle arguments = new Bundle();
        arguments.putParcelable("muzicar", unosiKorisnika.get(pos));
        FragmentDetaljiOMuzicaru fd = new FragmentDetaljiOMuzicaru();
        fd.setArguments(arguments);

        if(siriL)
        {
            //Slučaj za ekrane sa širom dijagonalom
            getFragmentManager().beginTransaction().replace(R.id.frejmZaDrugiFragment, fd).commit();
        }
        else {
            //Slučaj za ekrane sa početno zadanom širinom
            getFragmentManager().beginTransaction().replace(R.id.frejmZaPrviFregment, fd).addToBackStack(null).commit();
            //Primijetite liniju .addToBackStack(null)
        }
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
        final Muzicar HimzoPolovina = new Muzicar("Himzo", "Polovina", "folk", "ja sam taj");
        dodajNajpoznatijePjesme(HimzoPolovina);
        final Muzicar HalidBeslic = new Muzicar ("Halid", "Beslic", "folk", "kralj sevdaha");
        dodajNajpoznatijePjesme(HalidBeslic);
        final Muzicar MichaelJackson = new Muzicar("Michael", "Jackson", "pop", "kralj popa");
        dodajNajpoznatijePjesme(MichaelJackson);
        final Muzicar Shakira = new Muzicar("Shakira", "Ciganka", "pop", "waka naka");
        dodajNajpoznatijePjesme(Shakira);
        final Muzicar BonJovi = new Muzicar("Bon", "Jovi", "rock", "its my life");
        dodajNajpoznatijePjesme(BonJovi);
        final Muzicar SejoSekson = new Muzicar("Sejo", "Sekson", "rock", "lutka s naslovne");
        dodajNajpoznatijePjesme(SejoSekson);
        final Muzicar SlimaShady = new Muzicar("Slim", "Shady", "rap", "eminem");
        dodajNajpoznatijePjesme(SlimaShady);
        final Muzicar EdoMajka = new Muzicar("Edo", "Majka", "rap", "vratite nam Elvisa");
        dodajNajpoznatijePjesme(EdoMajka);
        final Muzicar NekiPjevac = new Muzicar("Neki", "Pjevac", "jazz", "mambo no 5");
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
