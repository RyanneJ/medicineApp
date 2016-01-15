package dialogs;

import java.util.Calendar;
import com.example.lucasmedicine.SettingActivity;

import models.Alarm;
import models.Person;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class ChangeAlarmDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener  {

	TextView alarmText;
	ContentResolver resolver;
	Context context;
	
    public ChangeAlarmDialog(String title, TextView alarmText, Context context, ContentResolver resolver) {
		this.resolver = resolver;
		this.context = context;
		this.alarmText = alarmText;
	}

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
		final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute, DateFormat.is24HourFormat(getActivity()));
    }

	@Override
	 public void onTimeSet(TimePicker view, int hour, int minute) {
		String time = hour + " : " + minute;
		Alarm.setTimeMinute(minute);
		Alarm.setTimeHour(hour);

		alarmText.setText(time);
		
		
		SettingActivity.saveTheTime(time, hour, minute, context, resolver);
		
	}
}
