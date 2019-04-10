package ba.unsa.etf.rma.rma_t1_z1.Klase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

public class MojReciever extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        if(Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()))
        {
            Toast.makeText(context, "zavrseno pokretanje", Toast.LENGTH_SHORT).show();
        }
        else if(ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction()))
        {
            //VELIKI BROJ "intent_filtera", KAO STO JE "connectivity_action" SU UKINUTI U VERZIJAMA IZNAS 23 ANDROIDA IZ RAZLOGA STO
            // JE VELIKI BROJ APLIKACIJA IH KORISTIO I TIME PEZPOTREBNO KORISTIO BATERIJU I MEMORIJSKE RESURSE. IAKO VISE NECE RADITI
            // AKO BUDU POZVANI KROZ "Manifest" FAJL, ONI SE IPAK MOGU KORSITI, ALI SE MORAJU DIREKTNO KODIRATI U AKTIVITIJE/PROZORE

            //drugi parametar je defaultna vrijednost ako prvi parametar, koji predstavlja predefinisanu konstantu, ne
            // vrati nista
            boolean konstantaNemaInterneta = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);

            if(konstantaNemaInterneta == true)
            Toast.makeText(context, "uredjaj iskljucen sa interneta", Toast.LENGTH_SHORT).show();
        }
    }
}
