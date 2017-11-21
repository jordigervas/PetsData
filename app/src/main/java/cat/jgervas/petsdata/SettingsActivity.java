package cat.jgervas.petsdata;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

public class SettingsActivity extends AppCompatActivity {


    private SharedPreferences Prefs;
    private SharedPreferences.Editor Editor;

    EditText name, surname, age;

    public static final String NAME = "NAME";
    public static final String SURNAME = "SURNAME";
    public static final String AGE = "AGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init()
    {
        // Get SharedPreferences Editor
        Prefs= getSharedPreferences("PEOPLE_PREFERENCES", Context.MODE_PRIVATE);
        Editor = Prefs.edit();

        // Init View fields
        name = (EditText) findViewById(R.id.editTextName);
        surname = (EditText) findViewById(R.id.editTextSurName);
        age = (EditText) findViewById(R.id.editTextAge);
        age.setInputType(InputType.TYPE_CLASS_NUMBER);

        // Read person info from preferences.
        readPerson();

    }

    /*
	 * Read the data refer to saved person and visualize them into Edittexts
	 */
    private void readPerson() {

        name.setText(Prefs.getString(NAME, null));
        surname.setText(Prefs.getString(SURNAME, null));
        String agePref = ""	+ Prefs.getInt(AGE, 0);
        age.setText((agePref.equals("0")) ? null : agePref);
    }

    public void save(View view)
    {
        String nameText = name.getText().toString();
        String surnameText = surname.getText().toString();
        String ageText = age.getText().toString();

        if (nameText != null)
            Editor.putString(NAME, nameText).commit();

        if (surnameText != null)
            Editor.putString(SURNAME, surnameText).commit();

        if (ageText != null && !ageText.equals(""))
            Editor.putInt(AGE, Integer.parseInt(ageText)).commit();

    }

    public void reset(View view)
    {
        /* A better way to delete all is:
		 * Editor.clear().commit();
		 */
        Editor.remove(NAME).commit();
        Editor.remove(SURNAME).commit();
        Editor.remove(AGE).commit();
        readPerson();

    }
}