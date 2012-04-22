package com.android.salesapp.utils;

import java.util.Calendar;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


public class DateTimePicker 
{	
	
//	======================== Private Variable ==================================
    private int _Year;
    private int _Month;
    private int _Day;
    private int _Hour;
    private int _Minute;

    private TextView _txtView;
    
    private Context _Context;
    
    private DialogType _objDialogType;
    
    
//	======================== Class Constructor ==================================
	public DateTimePicker(Context objContext, TextView txtView)
	{
		_Context = objContext;
		_txtView = txtView;
		
		Calendar objCalendar = Calendar.getInstance();
        
        setDate(objCalendar.get(Calendar.DAY_OF_MONTH), objCalendar.get(Calendar.MONTH), objCalendar.get(Calendar.YEAR));
		setTime(objCalendar.get(Calendar.HOUR_OF_DAY), objCalendar.get(Calendar.MINUTE));
	}
	
//	======================== Dialog Life Cycle ==================================
	protected Dialog onCreateDialog() 
	{
        switch (_objDialogType) 
        {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(_Context, mTimeSetListener, _Hour, _Minute, false);
            case DATE_DIALOG_ID:
                return new DatePickerDialog(_Context, mDateSetListener, _Year, _Month, _Day);
        }
        return null;
    }

    protected void onPrepareDialog(Dialog dialog) 
    {
        switch (_objDialogType) 
        {
            case TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(_Hour, _Minute);
                break;
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(_Year, _Month, _Day);
                break;
        }
    }
    
//	======================== Dialog Listener ==================================
    private DatePickerDialog.OnDateSetListener mDateSetListener = 
            new DatePickerDialog.OnDateSetListener() 
    		{
                public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) 
                {
                    _Year = year;
                    _Month = monthOfYear;
                    _Day = dayOfMonth;
                    updateDisplay();
                }
            };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener = 
            new TimePickerDialog.OnTimeSetListener() 
    		{
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) 
                {
                    _Hour = hourOfDay;
                    _Minute = minute;
                    updateDisplay();
                }
            };
            
//  ======================== Public Helper Method ===================================
    public static enum DialogType
    {
    	TIME_DIALOG_ID,
    	DATE_DIALOG_ID 
    };
    
    public Dialog showDateTimePicker(DialogType objDialogType)
	{
		_objDialogType = objDialogType;
		return onCreateDialog();
	}
    
    public void setDate(int Day, int Month, int Year)
    {
    	_Day = Day;
    	_Month = Month;
    	_Year = Year;
    }
    
    public void setTime(int Hour, int Minute)
    {
    	_Hour = Hour;
    	_Minute = Minute;
    }
    
//  ======================== Private Helper Method ==================================
    private void updateDisplay() 
    {	
    	switch (_objDialogType) 
        {
            case TIME_DIALOG_ID:

            	_txtView.setText(
                        new StringBuilder()
                                .append(pad(_Hour)).append(":")
                                .append(pad(_Minute)));	
                break;
            case DATE_DIALOG_ID:
				String date =new StringBuilder()
								.append(pad(_Day))
								.append("/")
								.append(pad(_Month + 1))
								.append("/")
								.append(_Year).toString();

    			_txtView.setText(Utils.convertDateStringToString(date, "dd/MM/yyyy", "dd/MM/yyyy"));
                break;
        }

    }
    
    private static String pad(int c) 
    {
        return c >= 10 ? String.valueOf(c) : "0" + String.valueOf(c);
    }

}
