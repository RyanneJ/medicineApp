package dialogs;

import java.util.Calendar;
import com.example.lucasmedicine.SettingActivity;

import models.Person;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentResolver;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.TextView;

public class ChangeBDayDialog extends DialogFragment implements DatePickerDialog.OnDateSetListener {

	TextView tvDisplayDate;
	ContentResolver resolver;
	
    public ChangeBDayDialog(String title, TextView tvDisplayDate2, ContentResolver contentResolver) {
		resolver = contentResolver;
		this.tvDisplayDate = tvDisplayDate2;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		String date = dayOfMonth + "-" + monthOfYear + "-" + year;
		Person.setBDay(date);
		
		
		String birthdate = Person.getChangedBDay();
		tvDisplayDate.setText(birthdate);
		
		
		SettingActivity.saveTheDate(date, resolver);
		
	}
}
