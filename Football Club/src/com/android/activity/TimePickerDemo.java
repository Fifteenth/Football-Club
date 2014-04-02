package com.android.activity;

import java.util.Calendar;

import com.android.club.R;

import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

public class TimePickerDemo extends Activity {

	private TextView mtextView;

	private Button mbutton;
	private int mHour, mMinute;
	static final int TIME_DIALOG = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.timepicker);

		final Calendar calendar = Calendar.getInstance();

		this.mHour = calendar.get(Calendar.HOUR_OF_DAY);

		this.mMinute = calendar.get(Calendar.MINUTE);

		mtextView = (TextView) findViewById(R.id.timeDisplay);
		mbutton = (Button) findViewById(R.id.pickTime);

		mbutton.setOnClickListener(clickListener);

	}

	private OnClickListener clickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			showDialog(TIME_DIALOG);
		}
	};

	private void updateDisplay() {
		// TODO Auto-generated method stub
		mtextView.setText(new StringBuilder().append(pad(mHour)).append(":")
				.append(pad(mMinute)));
	}

	private String pad(int c) {
		// TODO Auto-generated method stub
		if (c >= 10) {
			return String.valueOf(c);
		} else {
			return "0" + String.valueOf(c);
		}
	}

	@Override
	protected Dialog onCreateDialog(int id) {

		switch (id) {
		case TIME_DIALOG:
			return new TimePickerDialog(this, mTimeSetListener, mHour, mMinute,
					false);
		}
		return null;
	}

	private OnTimeSetListener mTimeSetListener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			mHour = hourOfDay;
			mMinute = minute;
			updateDisplay();
		}
	};
}