package ba.unsa.etf.rma.rma_t1_z1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private Button dugmeDodaj;
    private TextView prikazTeksta;
    private ListView lvLista;

    private ArrayAdapter ulancaniAdapter;
    private ArrayList<Muzicar> unosiKorisnika = new ArrayList<>();
    private EditText editujMe;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //predlaze id-eve svih elemenata sa prozaora kao upises "R.id. "
        dugmeDodaj = (Button) findViewById(R.id.dugmeDodaj);

        lvLista = (ListView) findViewById(R.id.lista);
        editujMe = (EditText) findViewById(R.id.editujMe);

        //Kada se u konstuktoru zeli koristiti neki od vec integrisanih layouta kao sto je "simple_list_item_1", mora se ispred "R", koji predtsvlja
        // oznaku za resource, stajati i "android"
        //ulancaniAdapter = new ArrayAdapter(getBaseContext(), android.R.layout.simple_list_item_1, unosiKorisnika);

        //treci parametar konstruktora: The id of the TextView within the layout resource to be populated
        ulancaniAdapter = new ArrayAdapter(getBaseContext(), R.layout.moj_izgled, unosiKorisnika);

        lvLista.setAdapter(ulancaniAdapter);

        final Muzicar HimzoPolovina = new Muzicar("Himzo", "Polovina", "folk", "ja sam taj");
        final Muzicar HalidBeslic = new Muzicar ("Halid", "Beslic", "folk", "kralj sevdaha");
        final Muzicar MichaelJackson = new Muzicar("Michael", "Jackson", "pop", "kralj popa");
        final Muzicar Shakira = new Muzicar("Shakira", "Ciganka", "pop", "waka naka");
        final Muzicar BonJovi = new Muzicar("Bon", "Jovi", "rock", "its my life");
        final Muzicar SejoSekson = new Muzicar("Sejo", "Sekson", "rock", "lutka s naslovne");

        dugmeDodaj.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                unosiKorisnika.add(HimzoPolovina);
                unosiKorisnika.add(HalidBeslic);
                unosiKorisnika.add(MichaelJackson);
                unosiKorisnika.add(Shakira);
                unosiKorisnika.add(BonJovi);
                unosiKorisnika.add(SejoSekson);

                ulancaniAdapter.notifyDataSetChanged();
            }
        });
    }
}
