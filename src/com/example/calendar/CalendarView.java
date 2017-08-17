package com.example.calendar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class CalendarView extends View{
	public Calendar_My ce;
	
	public CalendarView(Activity activity){
		super(activity);
		ce=new Calendar_My(activity,this);
	}
	/*public CalendarView(Context context,AttributeSet set) {  
        super(context,set);  
    } */
	public void onDraw(Canvas canvas){
		ce.draw(canvas);
	}

}
