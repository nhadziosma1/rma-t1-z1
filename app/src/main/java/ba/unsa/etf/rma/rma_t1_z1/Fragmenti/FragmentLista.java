package ba.unsa.etf.rma.rma_t1_z1.Fragmenti;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ba.unsa.etf.rma.rma_t1_z1.Klase.MojAdapter;
import ba.unsa.etf.rma.rma_t1_z1.Klase.Muzicar;
import ba.unsa.etf.rma.rma_t1_z1.Klase.PretragaMuzicara;
import ba.unsa.etf.rma.rma_t1_z1.R;

public class FragmentLista extends Fragment implements PretragaMuzicara.OnMuzicarSearchDone
{
    //ATRIBUTI
    private Button dugmeDodaj;
    private ListView lvLista;
    private EditText editujMe;
    private MojAdapter adapter;

    private ArrayList<Muzicar> muzicari = new ArrayList<>();
    private OnItemClick oic;

    //interfejs se implementira u fragmetu roditeljske klase
    public interface OnItemClick
    {
        public void onItemClicked(int pos, ArrayList<Muzicar> muzicari);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return  inflater.inflate(R.layout.lista_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        // Ukoliko je kao argument prosljeđena vrijednost koja ima ključ "Alista"
        // možemo dohvatiti niz i povezati ga na adapter
        if(getArguments().containsKey("Alista"))
        {
            //Dohvatamo proslijeđeni niz muzicara iz argumenata fragmenta
            // muzicari je privatni atribut FragmentLista klase tipa ArrayList<Muzicar>
            muzicari = getArguments().getParcelableArrayList("Alista");

            //Dohvatamo referencu na listview u fragmentu
            //koristimo rview jer on sadrži sve ui elemente fragmenta
            lvLista = (ListView)getView().findViewById(R.id.lista);
            dugmeDodaj = (Button) getView().findViewById(R.id.dugmeDodaj);
            editujMe = (EditText) getView().findViewById(R.id.editujMe);

            //Kreiramo instancu adaptera koji sadrži niz muzičara
            //Možete koristiti isti adapter kao i ranije
            adapter = new MojAdapter(getActivity(), muzicari, getResources());

            //Povezujemo adapter na listview
            lvLista.setAdapter(adapter);

            try
            {
                //-"oic" definišite kao privatni atribut klase FragmentLista
                //-u sljedećoj liniji dohvatamo referencu na roditeljsku aktivnost
                //-kako ona implementira interfejs OnItemClick moguće ju je castati u taj interfejs
                oic = (OnItemClick) getActivity();
            }
            catch (ClassCastException e)
            {
                //u slučaju da se u roditeljskoj aktivnosti nije implementirao interfejs, baca se izuzetak
                throw new ClassCastException(getActivity().toString() + "Treba implementirati OnItemClick");
            }

            //ukoliko je aktivnost uspješno cast-ana u interfejs, tada njoj prosljeđujemo event
            lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    //poziva se implementirana metoda početne aktivnosti iz interfejsa OnItemClick
                    //kao parametar se prosljeđuje pozicija u ListView-u na koju je korisnik kliknuo
                    oic.onItemClicked(position, muzicari);
                }
            });

            dugmeDodaj.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v)
                {
                    if(editujMe.getText().toString().trim().equals("") == false)
                    {
                        new PretragaMuzicara((PretragaMuzicara.OnMuzicarSearchDone) FragmentLista.this, getContext()).execute(editujMe.getText().toString().trim());
                        adapter.notifyDataSetChanged();
                    }

                }
                });
        }
    }

    @Override
    public void onDone(ArrayList<Muzicar> rezultatMuzicari)
    {
        muzicari.addAll(rezultatMuzicari);
        //muzicari.get(0).getIme().setText(muzicar.getIme());
    }
}
