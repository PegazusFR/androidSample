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

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import galaxy.pegazus.bienetre.data.LogDay;
import galaxy.pegazus.bienetre.data.LogDayDBHelper;


public class SaveDay extends ActionBarActivity {

    public final static String SAVED_DAY = "galaxy.pegazus.bienetre.SAVED";
    private String id_ = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_day);

        // Get the message from the intent
        Intent intent = getIntent();
        id_ = intent.getStringExtra(DisplayLogs.EXTRA_MSG_ID);

        // si on edite les données
        if(id_ != null){

            Log.i("Dev", "affichage mode !!!"+ id_);

            Log.i("Dev", "affichage mode !!!");
            LogDayDBHelper db = new LogDayDBHelper(getApplicationContext());
            LogDay log = db.getLog(id_);

            SeekBar seekBar= (SeekBar) findViewById(R.id.seekBar_sleeping);
            DatePicker datePicker=(DatePicker)  findViewById(R.id.date);
            EditText editText = (EditText)  findViewById(R.id.editText);

            seekBar.setProgress(log.getAssessment());
            editText.setText(log.getComment());

            Log.i("Dev","      "+(log.getDate()*1000));
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(log.getDate()*1000);

            Log.i("Dev", "date from epoche =" + log.getDate() + "     deduction=" + calendar.toString());

             Log.i("Dev", calendar.get(Calendar.YEAR)+"   "+calendar.get(Calendar.MONTH)+"   "+calendar.get(Calendar.DAY_OF_MONTH));
            datePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                    0, 0, 0);
            log.setDate( calendar.getTimeInMillis());


        }

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

        // data retrievment
        SeekBar seekBar= (SeekBar) findViewById(R.id.seekBar_sleeping);
        DatePicker datePicker=(DatePicker)  findViewById(R.id.date);
        EditText editText = (EditText)  findViewById(R.id.editText);

        // TODO : enregistrer dans un fichier le resultat .. dans base sqllite ?
        LogDayDBHelper db = new LogDayDBHelper(getApplicationContext());
        LogDay log = new LogDay();
        log.setComment(editText.getText().toString());
        log.setAssessment(seekBar.getProgress());



        // process date
        Calendar calendar = Calendar.getInstance();
        calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());

        Log.i("DEVVVV", "date year " + datePicker.getYear() + " month" + datePicker.getMonth() + "  day " + datePicker.getDayOfMonth() + "   epoch = " + (int) calendar.getTimeInMillis());

        Log.i("DEVVVV", "  ... " + calendar.get(Calendar.MONTH));

        Long tmp = calendar.getTimeInMillis();

        log.setDate(tmp/1000 );

        long inserted = db.insertLog(log);

        // dire à la vue qu'un enregistrement est effectué
        if(inserted == -1){
            intent.putExtra(SAVED_DAY, R.string.log_not_saved);
        }else{
            intent.putExtra(SAVED_DAY, R.string.log_saved);
        }



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
