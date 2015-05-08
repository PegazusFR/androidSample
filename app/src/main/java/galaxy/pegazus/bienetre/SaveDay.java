package galaxy.pegazus.bienetre;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;


public class SaveDay extends ActionBarActivity {

    public final static String SAVED_DAY = "galaxy.pegazus.bienetre.SAVED";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_day);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * Called when the user clicks the ave button
     */
    public void saveDay(View view) {
        // TODO : faire une page d'aceuil agréable qui ouvre cette activity
        Intent intent = new Intent(this, Welcome.class);
        // récupération de l'etat du reveil
        SeekBar bar = (SeekBar) findViewById(R.id.seekBar_sleeping);
        int valeurEtat = bar.getProgress();
        // date de l'évaluation
        DatePicker dateEvaluation = (DatePicker) findViewById(R.id.date);

        // récupération du commentaire
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();

        // TODO : enregistrer dans un fichier le resultat .. dans base sqllite ?

        // dire à la vue qu'un enregistrement est effectué
        intent.putExtra(SAVED_DAY, R.string.log_saved);


        //Intent intent = getIntent();
        //String feedback = intent.getStringExtra(SaveDay.SAVED_DAY);
        // if ok or not,we need to inform the user for the succes/fail of the actions.
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, getString(R.string.log_saved), duration);
        toast.show();
        startActivity(intent);



    }


}
