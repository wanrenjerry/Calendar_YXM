package com.example.calendar;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Canvas;
import android.view.View;

public class Calendar_My extends CalendarParent{
	private ArrayList<CalendarElement> elements=new ArrayList<CalendarElement>();
	public Grid grid;

	public Calendar_My(Activity activity, View view) {
		super(activity, view);
		// TODO Auto-generated constructor stub
		grid=new Grid(activity,view);
		elements.add(new Border(activity,view));
		elements.add(new Week(activity,view));
		elements.add(grid);
		
	}
	public void draw(Canvas canvas){
		for(CalendarElement ce:elements){
			ce.draw(canvas);
		}
	}

}
