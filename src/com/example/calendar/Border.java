package com.example.calendar;

import com.example.learntest.R;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class Border extends CalendarParent{

	public Border(Activity activity, View view) {
		super(activity, view);
		// TODO Auto-generated constructor stub
		paint.setColor(activity.getResources().getColor(R.color.border_color));
	}
	@Override
	public void draw(Canvas canvas){
		//获得日历边框的位置的大小数据
		float left=borderMargin_left;
		float top=borderMargin_top;
		float right=view.getMeasuredWidth()-left;
		float bottom=view.getMeasuredHeight()-12;
		
		canvas.drawLine(left,top, right,top, paint);
		canvas.drawLine(right, top, right, bottom, paint);
		canvas.drawLine(right, bottom, left, bottom, paint);
		canvas.drawLine(left, bottom, left, top, paint); 
	}

}
