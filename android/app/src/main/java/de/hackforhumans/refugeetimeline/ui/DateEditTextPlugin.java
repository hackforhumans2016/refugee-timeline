package de.hackforhumans.refugeetimeline.ui;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jan-niklas on 21.02.16.
 */
public class DateEditTextPlugin {

    private static final int TAG_DATE = 873534;

    private DatePickerDialog dialog;
    private TextView textView;
    private DateFormat dateFormat;

    private Calendar cal;

    private DateSetCallback callback;

    public DateEditTextPlugin(TextView textView, Date initial, DateFormat dateFormat, DateSetCallback callback) {

        this.textView = textView;
        this.dateFormat = dateFormat;
        this.callback = callback;

        cal = Calendar.getInstance();
        cal.setTime(initial);
        this.dialog = new DatePickerDialog(textView.getContext(), new OnDateSelectedListener(),
                cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        this.dialog.setCancelable(true);

        this.textView.setOnClickListener(new OnTextViewClickListener());
        this.textView.setTag(TAG_DATE, initial);
    }

    public void reset() {
        Date taggedDate = (Date) textView.getTag(TAG_DATE);
        if (taggedDate != null) {
            textView.setText(dateFormat.format(taggedDate));
        }
    }


    private class OnTextViewClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            if (v != textView || !v.isClickable()) return;

            try {
                cal.setTime(dateFormat.parse(((TextView) v).getText().toString()));
                dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

            } catch (ParseException e) {

                Date taggedDate = (Date) textView.getTag(TAG_DATE);
                if (taggedDate != null) {
                    cal.setTime(taggedDate);
                }
            }
            dialog.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));

            dialog.show();
        }
    }

    private class OnDateSelectedListener implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

            if (view != dialog.getDatePicker()) return;

            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, monthOfYear);
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            Date result = cal.getTime();

            textView.setTag(TAG_DATE, result);
            textView.setText(dateFormat.format(result));
            fireDateSet(result);
        }
    }

    public void fireDateSet(Date date) {
        if(callback != null) {
            callback.onDateSet(textView, date);
        }
    }

    public interface DateSetCallback {
        public void onDateSet(TextView view, Date date);
    }

}
